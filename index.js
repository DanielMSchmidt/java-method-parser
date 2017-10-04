"use strict";

const parseClassName = file => {
  // TODO: add extends as edge case
  const classNameRegex = /public class (.*)\s\{/;
  const [, name] = classNameRegex.exec(file);
  return name;
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
      args: rawArguments // TODO: improve
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
