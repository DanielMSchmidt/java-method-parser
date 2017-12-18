"use strict";

const parsePackageName = file => {
  const packageNameRegex = /package (.*)\;/;
  const [, pkg] = packageNameRegex.exec(file);
  return pkg;
};

const parseClassName = file => {
  // TODO: add extends as edge case
  const classNameRegex = /public (?:final |static | )*class (.*)\s\{/;
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

// Get the matching closing bracket after this point
const getClosingBracketPosition = (file, startIndex) => {
  let currentDepth = 1;
  let currentIndex = startIndex;

  while (currentDepth > 0) {
    const nextOpeningIndex =
      file.indexOf("{", currentIndex) !== -1
        ? file.indexOf("{", currentIndex)
        : Infinity;
    const nextClosingIndex =
      file.indexOf("}", currentIndex) !== -1
        ? file.indexOf("}", currentIndex)
        : Infinity;

    // Opening bracket comes next
    if (nextOpeningIndex < nextClosingIndex) {
      currentDepth += 1;
      currentIndex = nextOpeningIndex + 1;
    }

    // Closing bracket comes next
    if (nextClosingIndex < nextOpeningIndex) {
      currentDepth -= 1;
      currentIndex = nextClosingIndex + 1;
    }
  }

  return currentIndex;
};

const parseMethods = file => {
  // TODO: multi line methods
  const methodRegex = /(public|private|protected) (static )?(\w*) (\w*)(?:\(((\w|\s|\,|\n)*)\))/g;
  const methods = [];
  let match;

  while ((match = methodRegex.exec(file))) {
    const start = file.indexOf("{", match.index);
    const end = getClosingBracketPosition(file, start + 1);

    const [, visibility, modifier, returnType, name, rawArguments] = match;

    methods.push({
      start,
      end,
      content: {
        public: visibility === "public",
        static: Boolean(modifier),
        returnType,
        name,
        args: rawArguments.split(",").map(transformArgument)
      }
    });
  }

  return methods
    .filter(
      (filteredMethod, index, methods) =>
        !methods.some(
          comparingMethod =>
            comparingMethod.start < filteredMethod.start &&
            filteredMethod.start < comparingMethod.end
        )
    )
    .map(({ content }) => content);
};

const parse = file => {
  return {
    package: parsePackageName(file),
    name: parseClassName(file),
    methods: parseMethods(file)
  };
};

module.exports = parse;
