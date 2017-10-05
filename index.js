"use strict";

const parseClassName = file => {
  // TODO: add extends as edge case
  const classNameRegex = /public class (.*)\s\{/;
  const [, name] = classNameRegex.exec(file);
  return name;
};

const argumentRegex = /(final)? (\w*) (\w*)/;
const transformArgument = rawArguments => {
  const argumentParts = rawArguments
    .split(" ")
    .filter(part => part && part !== "\n");
  if (!argumentParts.length) {
    return null;
  }

  // TODO: check for other modifiers like final
  if (argumentParts[0] === "final") {
    const [, type, name] = argumentParts;
    return {
      final: true,
      type,
      name
    };
  }
  const [type, name] = argumentParts;
  return {
    final: false,
    type,
    name
  };
};

const parseMethods = file => {
  // TODO: multi line methods
  const methodRegex = /(public|private|protected) (static )?(\w*) (\w*)(?:\(((\w|\s|\,|\n)*)\))/g;
  const methods = [];
  let match;
  while ((match = methodRegex.exec(file))) {
    const [, visibility, modifier, returnType, name, rawArguments] = match;

    methods.push({
      public: visibility === "public",
      static: Boolean(modifier),
      returnType,
      name,
      args: rawArguments.split(",").map(transformArgument)
    });
  }

  return methods;
};

const parse = file => {
  return {
    name: parseClassName(file),
    methods: parseMethods(file)
  };
};

module.exports = parse;
