"use strict";

const parseClassName = file => {
  // TODO: add extends as edge case
  const classNameRegex = /public class (.*)\s\{/;
  const [, name] = classNameRegex.exec(file);
  return name;
};

const parseMethods = file => {};

const parse = file => {
  return {
    name: parseClassName(file),
    methods: parseMethods(file)
  };
};

module.exports = parse;
