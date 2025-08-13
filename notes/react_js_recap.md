---
category: Study
title: 실용 React / JS 메모
date_started: 2025.08.13
date_updated: 2025.08.13
tags: javascript, react
---

![dependency tree](https://github.com/user-attachments/assets/5d99aa13-5d32-4ad2-9902-0bdd7427845e)

React.dev를 정독하며, React 프로젝트에 바로 적용할 만한 내용들과 더 나은 프로젝트를 위해 이해해야하는 개념을 메모함.

개념이나 기능이 좋으면 메모한다기 보다는, *프로젝트를 진행하면서 이런 개념 혹은 기능이 필요했는데, 이런 식으로 풀이하는구나.* 하는 부분을 적음. *(바로 적용할 만한 내용)*.

혹은 *이건 꼭 이해하고 있어야겠다*. 하는 내용을 적음

# React

## Props
컴포넌트의 Props는 읽기 전용 스냅샷으로, ***렌더링 할 때마다 새로운 버전의 props***를 받습니다.

## Fragment (<></>의 명시적 문법)
각 항목이 하나가 아닌 여러 개의 DOM 노드를 렌더링해야 하는 경우에는 어떻게 해야 할까요?

짧은 <> </> fragment 구문으로는 key를 전달할 수 없으므로 key를 단일 <div>로 그룹화하거나 약간 더 길고 명시적인 <Fragment> 문법을 사용해야 합니다.

```jsx

import { Fragment } from 'react';

// ...

const listItems = people.map(person =>
 <Fragment key={person.id}>
   <h1>{person.name}</h1>
   <p>{person.bio}</p>
 </Fragment>
);
```

Fragment는 DOM에서 사라지므로 `<h1>, <p>, <h1>, <p>`등의 평평한 리스트가 생성됩니다.

## 배열 랜더링에서 key

*주의하세요!*

배열에서 항목의 인덱스를 key로 사용하고 싶을 수도 있습니다. **실제로 key를 전혀 지정하지 않으면 React는 인덱스를 사용합니다.**

하지만 항목이 삽입되거나 삭제하거나 배열의 순서가 바뀌면 시간이 지남에 따라 항목을 렌더링하는 순서가 변경됩니다. 인덱스를 key로 사용하면 종종 미묘하고 혼란스러운 버그가 발생합니다.

마찬가지로 key={Math.random()}처럼 즉석에서 key를 생성하지 마세요. 이렇게 하면 렌더링 간에 key가 일치하지 않아 모든 컴포넌트와 DOM이 ***매번 다시 생성될 수 있습니다***. 속도가 느려질 뿐만 아니라 리스트 항목 내부의 모든 사용자의 입력도 손실됩니다. 대신 데이터 기반의 안정적인 ID를 사용하세요.


## 의존성 트리

![dependency tree](https://github.com/user-attachments/assets/5d99aa13-5d32-4ad2-9902-0bdd7427845e)

앱이 커짐에 따라 번들 크기도 커집니다. 번들 크기가 커지면 클라이언트가 다운로드하고 실행하는 데 드는 비용도 커집니다. 또한 UI가 그려지는 데 시간이 지체될 수 있습니다. 앱의 의존성 트리를 파악하면 이러한 문제를 디버깅하는 데 도움이 될 수 있습니다.

## State Queue

***React는 이벤트 핸들러가 실행을 마친 후 state 업데이트를 처리합니다. 이를 batching 이라고 합니다.***

이벤트 핸들러가 완료되면 React는 리렌더링을 실행합니다. 리렌더링하는 동안 React는 큐를 처리합니다. 업데이터 함수는 렌더링 중에 실행되므로, 업데이터 함수는 순수해야 하며 결과만 반환 해야 합니다.

업데이터 함수 내부에서 state를 변경하거나 다른 사이드 이팩트를 실행하려고 하지 마세요. Strict 모드에서 React는 각 업데이터 함수를 두 번 실행(두 번째 결과는 버림)하여 실수를 찾을 수 있도록 도와줍니다.

## Event handler

*중요합니다!*

이벤트 핸들러에 적절한 HTML 태그를 사용하고 있는지 확인하세요. 예를 들어 클릭을 처리하기 위해서는 `<div onClick={handleClick}>` 대신 `<button onClick={handleClick}>`을 사용하세요.

실제 브라우저에서 `<button>`은 키보드 내비게이션과 같은 빌트인 브라우저 동작을 활성화 합니다. 만일 버튼의 기본 브라우저 스타일링이 싫어서 링크나 다른 UI 요소처럼 보이도록 하고 싶다면 CSS를 통해 그 목적을 이룰 수 있습니다. 

## Propagation

부여된 JSX 태그 내에서만 실행되는 `onScroll`을 제외한 React 내의 모든 이벤트는 전파됩니다.

- 드물게 전파가 중단된 상황일지라도 자식 컴포넌트의 모든 이벤트를 캡처해 확인해야 할 수 있습니다. 일례로, 분석을 위해 전파 로직에 상관 없이 모든 클릭 이벤트를 기록하고 싶을 수 있습니다. 이를 위해서는 이벤트명 마지막에 Capture를 추가하면 됩니다.
  ``` jsx
  <div onClickCapture={() => { /* this runs first */ }}>
    <button onClick={e => e.stopPropagation()} />
    <button onClick={e => e.stopPropagation()} />
  </div>
  ```
  각각의 이벤트는 세 단계를 거쳐 전파됩니다.

  - 아래로 전달되면서 만나는 모든 onClickCapture 핸들러를 호출합니다.
  - 클릭된 요소의 onClick 핸들러를 실행합니다.
  - 위로 전달되면서 만나는 모든 onClick 핸들러를 호출합니다.
  - 이벤트 캡처는 라우터나 분석을 위한 코드에 유용할 수 있지만 일반 애플리케이션 코드에서는 사용하지 않을 가능성이 높습니다.

## useReducer

[State 로직을 reducer로 작성하기](https://ko.react.dev/learn/extracting-state-logic-into-a-reducer)

여러 이벤트 핸들러를 통해 많은 state 업데이트가 이루어지는 컴포넌트는 감당하기 힘들 수 있습니다. 이 때 컴포넌트 외부에서 “reducer”라는 단일 함수를 사용하여 모든 state 업데이트 로직을 통합할 수 있습니다. 이벤트 핸들러는 오로지 사용자의 “action”만을 명시하므로 간결해집니다.

각 action에 대한 state 업데이트 방법은 파일 맨 마지막 부분의 reducer 함수에 명시되어 있습니다.

## [Reducer와 Context로 앱 확장하기](https://ko.react.dev/learn/scaling-up-with-reducer-and-context)
Reducer를 사용하면 컴포넌트의 state 업데이트 로직을 통합할 수 있습니다. Context를 사용하면 다른 컴포넌트에 정보를 깊숙이 전달할 수 있습니다. Reducer와 Context를 함께 사용하여 복잡한 화면의 state를 관리할 수 있습니다.

이 접근 방식을 사용하면 상위 컴포넌트가 Reducer로 복잡한 state를 관리합니다. 트리 깊은 곳에 있는 다른 컴포넌트는 Context를 통해 상위 컴포넌트의 state를 읽을 수 있습니다. 또한 해당 state를 업데이트하기 위해 action을 dispatch 할 수도 있습니다.

## [Escape-Hatches](https://ko.react.dev/learn/escape-hatches)

``` js
// TOLEARN
```

# Javascript

## Hoisting
- var, let, const 모두 호이스팅되나, var만 undefined로 초기화된다. 나머지는 초기화되지 않으므로, initialization 전에 참조할 수 없다는 `Reference Error`가 발생한다.
- function은 선언 뿐만 아니라 정의도 맨 위로 올라간다.
- const fn = ()=>{} 문법은 const와 동일하게 작동한다.

## Array.filter(fn)
- fn이 true를 리턴하는 원소만 모은 배열을 리턴한다.

## Array.from(iterable || array-like object, mapFunction)

```js
console.log(Array.from("foo"));
// Expected output: Array ["f", "o", "o"]

console.log(Array.from([1, 2, 3], (x) => x + x));
// Expected output: Array [2, 4, 6]
```

# 참고자료
- [React.dev](https://ko.react.dev/learn)