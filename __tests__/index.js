const fs = require("fs");
const javaMethodParser = require("../");

function loadFile(name) {
  return fs.readFileSync(`./exampleFiles/${name}.java`, "utf8");
}

describe("objective-c-parser", () => {
  describe("basic example", () => {
    const basic = loadFile("basic");
    it("should return name of basic example", () => {
      expect(javaMethodParser(basic).name).toBe("BasicName");
    });

    it("should return three methods for basic example");

    it("should be able to detect static methods");
  });
});
