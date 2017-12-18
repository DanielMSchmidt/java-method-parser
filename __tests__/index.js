const fs = require("fs");
const javaMethodParser = require("../");

function loadFile(name) {
  return fs.readFileSync(`./exampleFiles/${name}.java`, "utf8");
}

describe("java-method-parser", () => {
  describe("basic example", () => {
    const basic = loadFile("basic");
    it("should return name of basic example", () => {
      expect(javaMethodParser(basic).name).toBe("BasicName");
    });

    it("should return the package name", () => {
      expect(javaMethodParser(basic).package).toBe("com.wix.detox.espresso");
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

    it("should parse the arguments", () => {
      const [firstMethod, secondMethod] = javaMethodParser(basic).methods;
      expect(firstMethod.args.length).toBe(1);
      expect(firstMethod.args[0].type).toBe("int");
      expect(firstMethod.args[0].name).toBe("times");
      expect(firstMethod.args[0].final).toBe(false);

      expect(secondMethod.args.length).toBe(2);
      expect(secondMethod.args[0].type).toBe("String");
      expect(secondMethod.args[0].name).toBe("x");
      expect(secondMethod.args[0].final).toBe(true);

      expect(secondMethod.args[1].type).toBe("float");
      expect(secondMethod.args[1].name).toBe("y");
      expect(secondMethod.args[1].final).toBe(true);
    });
  });

  describe("advanced example", () => {
    const advanced = loadFile("advanced");

    it("should ignore inner anonymous functions", () => {
      expect(javaMethodParser(advanced).methods.length).toBe(7);
    });
  });

  describe("advanced2 example", () => {
    const advanced = loadFile("advanced2");

    it("should allow comment in the beginning", () => {
      expect(javaMethodParser(advanced).package).toBe(
        "android.support.test.espresso.action"
      );
    });

    it("should allow modifiers for class names", () => {
      expect(javaMethodParser(advanced).name).toBe("ViewActions");
    });
  });
});
