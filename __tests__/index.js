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

    it("should return five methods for basic example", () => {
      expect(javaMethodParser(basic).methods.length).toBe(7);
    });

    it("should detect private and public methods", () => {
      expect(javaMethodParser(basic).methods.map(x => x.public)).toEqual([
        true,
        true,
        true,
        true,
        true,
        false,
        true
      ]);
    });

    it("should be able to detect static methods", () => {
      expect(javaMethodParser(basic).methods.map(x => x.static)).toEqual([
        true,
        true,
        true,
        true,
        false,
        true,
        false
      ]);
    });
  });
});
