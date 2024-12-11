---
category: Study
title: Javascript
date_started: 2024.12.10
date_updated: 2024.12.10
tags: javascript
---

> JS를 한 번 정리해보고자 시작했다. 모르거나 모호했던 내용을 적었다.

# javascript 정리

<img width="720" alt="image" src="https://github.com/user-attachments/assets/20885936-1b8c-48f4-b5f5-11bc362849b6">

> [w3schools - JS Tutorial](https://www.w3schools.com/js)

### var vs let

- scope: var는 함수 스코프, let은 블록 스코프
- hoisting: var는 선언 전에 사용 가능하다. let은 선언 전에 사용하면 ReferenceError가 발생한다.
- redeclaration: var는 재선언이 가능하다. let은 재선언이 불가능하다.
- global object: var는 window object의 property로 등록된다(binding). let은 window object의 property로 등록되지 않는다.

### Number

- bit: 64bit
- `Number.MAX_SAFE_INTEGER`: 2^53-1
- `Number.MIN_SAFE_INTEGER`: -(2^53-1)
- `Number.MAX_VALUE`: 1.7976931348623157e+308
- `Number.MIN_VALUE`: 5e-324
- `Number.EPSILON`: 2^-52

```javascript
console.log(Number.MAX_SAFE_INTEGER);
// 9007199254740991

console.log(Number.MIN_SAFE_INTEGER);
// -9007199254740991

console.log(Number.MAX_VALUE);
// 1.7976931348623157e+308

console.log(Number.MIN_VALUE);
// 5e-324

console.log(Number.EPSILON);
// 2.220446049250313e-16
```


### BigInt

- 2^53-1보다 큰 정수를 표현할 수 있다.
- `n`을 붙여서 표현한다.

```javascript
const bigInt1 = 1234567890123456789012345678901234567890n;
const bigInt2 = BigInt("1234567890123456789012345678901234567890");

```

### ?? operator

- nullish coalescing operator
- `null` 또는 `undefined`일 때만 우측의 값을 반환한다.

```javascript
const foo = null ?? "default string";
console.log(foo);
// "default string"

const bar = 0 ?? 42;
console.log(bar);
// 0
```

### ~ operator

- bitwise NOT operator
- `~n`은 `-n-1`을 반환한다.
- 소수점 제거에 활용 가능 (64bit -> 32bit)

```javascript
console.log(~2);
// -3

console.log(~~5.512);
// 5
```

## In JavaScript, Objects are King.
If you Understand Objects, you Understand JavaScript.
Objects are containers for Properties and Methods.

Properties are named Values.

Methods are Functions stored as Properties.

Properties can be primitive values, functions, or even other objects.

오브젝트 목록
- Objects
- Maths
- Functions
- Dates
- Arrays
- Maps
- Sets

All JavaScript values, except primitives, are objects.

생성자: 모든 자바스크립트 오브젝트는 생성자 property가 있다. 이는 생성자 함수라고 불린다.

```javascript
const x = {};
console.log(x.constructor);
// [Function: Object]

function Person(first, last, age, eyecolor) {
  this.firstName = first;
  this.lastName = last;
  this.age = age;
  this.eyeColor = eyecolor;
  this.nationality = "English";
}

const myFather = new Person("John", "Doe", 50, "blue");
console.log(myFather);
// Person{...}

```

Property 추가하기  

```javascript

function Person(first, last, age, eyecolor) {
  this.firstName = first;
  this.lastName = last;
  this.age = age;
  this.eyeColor = eyecolor;
  this.nationality = "English";
}

const myFather = new Person("John", "Doe", 50, "blue");
console.log(myFather);

Person.area = 'London'
console.log(myFather.area)
// undefined

Person.prototype.area = 'London'
console.log(myFather.area)
// London

```

instance를 만든 후에 Property를 추가해도 default value를 가지는 것이 신기했다.

원리:  
- `myFather`는 `Person`의 instance이다.
- `Person`의 prototype에 `area` property를 추가하면, `myFather`의 prototype chain에도 `area` property가 추가된다.
- `myFather`의 prototype chain에 `area` property가 없기 때문에 `Person`의 prototype chain을 타고 올라가 `area` property를 찾는다.

Built-in JavaScript 생성자들  
JavaScript has built-in 생성자들 for all native objects:  

```javascript
new Object()   // A new Object object
new Array()    // A new Array object
new Map()      // A new Map object
new Set()      // A new Set object
new Date()     // A new Date object
new RegExp()   // A new RegExp object
new Function() // A new Function object
```

Note:
The Math() object is not in the list. Math is a global object. The new keyword cannot be used on Math.

### 알고 있었니?
Use object literals {} instead of new Object().

Use array literals [] instead of new Array().

Use pattern literals /()/ instead of new RegExp().

Use function expressions () {} instead of new Function().

```javascript
"";           // primitive string
0;            // primitive number
false;        // primitive boolean

{};           // object object
[];           // array object
/()/          // regexp object
function(){}; // function
```

### Prototypes

All JavaScript objects inherit properties and methods from a prototype.

### Primitives

A primitive value is a value that has no properties or methods.

3.14 is a primitive value

A primitive data type is data that has a primitive value.

JavaScript defines 7 types of primitive data types:

string
number
boolean
null
undefined
symbol
bigint

They are immutable (cannot be changed).
Objects are mutable (can be changed).


### String

Template Strings  
Template Strings은 ES6 (JavaScript 2016)에 나왔다.

Templates은 백틱으로 감싸인 문자열이다. (\`This is a template string\`)

Templates allow single and double quotes inside a string:

```javascript
let text = `He's often called "Johnny"`;
```

---

### 참고자료

- [MDN web docs](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
- [w3schools](https://www.w3schools.com/js/default.asp)