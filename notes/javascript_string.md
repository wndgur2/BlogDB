---
category: Study
title: javascript_string
date_started: 2024.12.20
date_updated: 2024.12.20
tags: javascript, ECMA
---


![string](https://github.com/user-attachments/assets/de863d94-0958-4417-9e4f-49180d36109d)


### Template Strings  
Template Strings은 ES6 (JavaScript 2016)에 나왔다.

Templates은 백틱으로 감싸인 문자열이다. (\`This is a template string\`)

template string 안에 single quote(')와 double quote(")를 사용할 수 있다.

```javascript
let text = `He's often called "Johnny"`;
```

### slice(i, j)
i에서 j-1까지의 문자열을 반환한다.

```javascript
let str = "Apple, Banana, Kiwi";
console.log(str.slice(7, 13));
// Banana
console.log(str.slice(-12, 13));
// Banana
console.log(str.slice(-12, -6));
// Banana
```

### substring(i, j)

slice와 비슷하지만 음수를 받을 수 없다.
받으면 0으로 처리한다.

```javascript
let str = "Apple, Banana, Kiwi";
console.log(1, str.substring(7, 13));
// 1 Banana
console.log(2, str.substring(-12, 13));
// 2 Apple, Banana
console.log(3, str.substring(-12, -6));
// 3 
```

### substr(i, length) ( deprecated )

i부터 length만큼의 문자열을 반환한다.

```javascript
let str = "Apple, Banana, Kiwi";
console.log(str.substr(7, 6));
// Banana
console.log(str.substr(-12, 6));
// Banana
console.log(str.substr(-12, -6));
// 
```