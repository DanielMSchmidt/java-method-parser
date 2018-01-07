# java-method-parser [![Build Status](https://travis-ci.org/DanielMSchmidt/java-method-parser.svg?branch=master)](https://travis-ci.org/DanielMSchmidt/java-method-parser) [![Coverage Status](https://coveralls.io/repos/github/DanielMSchmidt/java-method-parser/badge.svg?branch=master)](https://coveralls.io/github/DanielMSchmidt/java-method-parser?branch=master) [![BCH compliance](https://bettercodehub.com/edge/badge/DanielMSchmidt/java-method-parser?branch=master)](https://bettercodehub.com/)

> Get an objective-c header file and translate it to equivalent javascript calls

## Install

```
$ npm install java-method-parser
```

## Usage

```js
const fs = require("fs");
const javaMethodParser = require("java-method-parser");
const content = fs.readFileSync("/path/to/java/Ponies.java");

const output = objectiveCParser(content);

fs.writeFileSync("/path/to/project/ponies.json", output);
```

## Example

```java
package com.foo.bar.baz;

public class BasicName {
    private BasicName() {}

    /**
     *   asserting a matcher
     */
    public static ViewInteraction assertMatcher(ViewInteraction i, Matcher<View> m) {}

    // This is for asserting invisibility
    public static ViewInteraction assertNotVisible(ViewInteraction i) {}
}
```

```json
{
	"name": "BasicName",
	"pcakage": "com.foo.bar",
	"methods": [
		{
			"args": [
				{
					"type": "ViewInteraction",
					"name": "i"
				},
				{
					"type": "Matcher<View>",
					"name": "m"
				}
			],
			"comment": "asserting a matcher",
			"name": "assertMatcher",
			"returnType": "ViewInteraction"
		},
		{
			"args": [
				{
					"type": "ViewInteraction",
					"name": "i"
				}
			],
			"comment": "This is for asserting invisibility",
			"name": "assertNotVisible",
			"returnType": "ViewInteraction"
		}
	]
}
```

## License

MIT © [Daniel Schmidt](http://danielmschmidt.de)
