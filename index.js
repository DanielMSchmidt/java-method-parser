"use strict";
const fs = require("fs");
const path = require("path");
const log = require("npmlog");
const peg = require("pegjs");

log.level = process.env.DEBUG ? "verbose" : "warn";

log.info("init", "Loading Grammar");
const grammar = fs.readFileSync(
	path.join(__dirname, "./grammar/java-1.7.pegjs"),
	"utf-8"
);
log.info("init", "Generating Parser");
const parser = peg.generate(grammar);
log.info("init", "Parser generated");

function getFullyQualifiedName(ast) {
	if (!ast.qualifier) {
		return ast.identifier;
	}

	return getFullyQualifiedName(ast.qualifier) + "." + ast.name.identifier;
}

function parsePackageName(ast) {
	return getFullyQualifiedName(ast.package.name);
}

function parseClassName(ast) {
	if (ast.types.length > 1) {
		log.warn("classname", "unclear which type to take");
	}

	return ast.types[0].name.identifier;
}

function getReturnType(ast) {
	return ast.returnType2.name
		? ast.returnType2.name.identifier
		: ast.returnType2.primitiveTypeCode;
}

function hasModifier(ast, name) {
	return Boolean(ast.modifiers.find(modifier => modifier.keyword === name));
}

function getArgumentType(ast) {
	switch (ast.node) {
		case "SimpleType":
			return ast.name.identifier;
		case "PrimitiveType":
			return ast.primitiveTypeCode;
		case "ParameterizedType":
			const baseType = ast.type.name.identifier;
			const typeArgs = ast.typeArguments.map(arg => getArgumentType(arg));

			return `${baseType}<${typeArgs.join(",")}>`;
		default:
			throw new Error("Unknown type :" + ast.node);
	}
}

function getArgument(ast) {
	return {
		name: ast.name.identifier,
		type: getArgumentType(ast.type),
		static: hasModifier(ast, "static"),
		final: hasModifier(ast, "final")
	};
}

function parseMethods(ast) {
	if (ast.types.length > 1) {
		log.warn("methods", "unclear which type to take");
	}

	return ast.types[0].bodyDeclarations
		.filter(method => method.node === "MethodDeclaration")
		.filter(method => method.constructor === false)
		.map(method => ({
			name: method.name.identifier,
			static: hasModifier(method, "static"),
			public: hasModifier(method, "public"),
			returnType: getReturnType(method),
			args: method.parameters.map(getArgument)
		}));
}

const parse = file => {
	log.info("p", "parsing");
	const ast = parser.parse(file);
	log.info("p", "parsed");

	return {
		package: parsePackageName(ast),
		name: parseClassName(ast),
		methods: parseMethods(ast)
	};
};

module.exports = parse;
