---
category: Study
title: Webpack_Vite
date_started: 2024.12.04
date_updated: 2024.12.06
tags: bundler, browser
---

<img src='https://github.com/user-attachments/assets/b6982d5e-cfd8-484d-a2ee-31019ff4088e' alt='webpack' width='200px' />

> webpack, vite 공식 문서를 정리한 내용입니다.

# Web Bundler: 웹 애플리케이션을 효율적으로 관리하기 위한 도구

웹 개발에서는 여러 파일을 번들링(bundle)하여 최적화된 형태로 웹 브라우저에 전달하는 작업이 중요합니다. 이를 돕는 도구가 바로 **웹 번들러**입니다. 웹 번들러는 코드의 모듈을 결합하여 하나의 파일로 만들어주고, 그 과정에서 파일 크기를 줄이는 작업도 수행합니다. 또한, 각 모듈 간의 의존성을 처리하고, 다양한 브라우저 환경을 고려한 최적화 작업을 해줍니다.

이 글에서는 대표적인 웹 번들러인 **Webpack**과 **Vite**를 비교하고, 최신 개발 트렌드에 적합한 트랜스파일러인 **Babel**과 **Esbuild**를 다룹니다.

---

## 요약

**Webpack**은 **모듈 번들링**을 위한 도구로, 모든 의존성을 처리하고 여러 파일을 하나로 묶어주는 역할을 합니다. 복잡한 설정을 통해 자바스크립트, CSS, 이미지 파일 등을 효율적으로 번들링할 수 있습니다. 그러나 Webpack은 **구성이 복잡**하고, **빌드 속도**가 느려질 수 있습니다.

반면, **Vite**는 Webpack의 단점을 보완하고자 만든 툴로, **빠른 개발 환경**을 제공하는 것이 특징입니다. Vite는 **ES 모듈(ESM)**을 기반으로 페이지를 빠르게 로드하고, 개발 중에는 **핫 모듈 리플레이스먼트(HMR)** 기능을 통해 페이지를 새로 고침하지 않고도 변경 사항을 즉시 반영할 수 있습니다. Vite는 **ESBuild**를 사용하여 트랜스파일링을 처리하는데, 이 덕분에 **빌드 속도**가 매우 빠릅니다.

## Webpack: 전통적이지만 강력한 모듈 번들러

<img src= 'https://github.com/user-attachments/assets/8c850ae2-cf4a-4975-b3b7-847f51043a8a' alt='webpack' />

### 개요

**Webpack**은 자바스크립트 애플리케이션을 위한 정적 모듈 번들러입니다. Webpack은 애플리케이션의 **엔트리 포인트(entry point)**를 기준으로 내부 **의존성 그래프(dependency graph)**를 생성한 후, 필요한 모듈을 번들로 묶습니다. 번들링된 결과는 배포를 위해 정적 자원으로 제공됩니다.

### 주요 개념

1. **Entry**: 번들링을 시작하는 진입점.
2. **Output**: 번들 파일의 이름과 저장 위치를 지정.
3. **Loaders**: CSS, 이미지, TypeScript 등 비(非)자바스크립트 파일을 변환.
4. **Plugins**: 번들 최적화, 환경 변수 설정 등 다양한 작업 지원.
5. **Mode**: 개발(development) 또는 프로덕션(production) 모드에 따른 최적화.

### 장점

- **유연한 설정**: 다양한 요구 사항에 맞게 구성 가능.
- **광범위한 생태계**: 수많은 로더와 플러그인 지원.

### 단점

- 설정의 복잡성: 많은 설정이 필요할 수 있음.
- 느린 빌드 속도: 대규모 애플리케이션에서는 초기 빌드와 HMR(Hot Module Replacement) 속도가 느려질 수 있음.

### Babel

Webpack은 보통 **Babel**과 함께 사용되어 최신 자바스크립트 문법을 구버전 브라우저에서도 실행 가능하도록 변환합니다. 예를 들어, 다음과 같은 설정을 통해 Babel을 로더로 추가할 수 있습니다:

```javascript
module.exports = {
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
        },
      },
    ],
  },
}
```

## Vite: 차세대 번들러

<img src= 'https://github.com/user-attachments/assets/d1d61b5e-cc58-4619-8c2a-c058c7e604e2'
alt='vite' />

### 개요

Vite는 Webpack의 한계를 극복하고자 만들어진 번들러로, 빠른 개발 환경을 제공하는 것이 특징입니다. Vite는 브라우저의 **ES 모듈(ESM)**을 적극 활용하며, Esbuild를 사용해 빌드 속도를 크게 향상시킵니다.

### 주요 차이점

1. **빠른 서버 시작**: Vite는 애플리케이션을 **의존성(dependencies)**과 **소스 코드(source code)**로 나누어, 의존성은 미리 번들링하고 소스 코드는 요청에 따라 처리합니다.

2. **효율적인 HMR**: 변경된 파일만 정확히 갱신하므로 애플리케이션 크기에 상관없이 빠르게 업데이트.
3. **HTTP 헤더 활용**: 브라우저 캐싱을 통해 재요청을 최소화.

### Esbuild

Vite는 **Esbuild를** 사용해 의존성을 미리 번들링합니다. Esbuild는 Go 언어로 작성되어 기존 JavaScript 기반 도구보다 **10~100배** 빠른 속도를 자랑합니다.

### 장점

- 즉각적인 개발 서버 시작.
- 최신 브라우저 환경 최적화.
- 간단한 설정.

### 단점

- 플러그인 생태계의 제한: Rollup 기반 플러그인을 사용해야 함.
- 복잡한 트랜스파일링이 필요한 경우 Babel 대비 기능 부족.

---

## Babel과 Esbuild: Transpiler 비교

**트랜스파일러(Transpiler)**는 자바스크립트 코드를 다른 형식으로 변환하는 도구로, **최신 문법**을 구버전 브라우저에서 실행할 수 있도록 변환하거나, TypeScript를 JavaScript로 변환하는 데 사용됩니다.

### Babel

**Babel**은 주로 **최신 ECMAScript** 문법을 구버전 자바스크립트로 변환하거나, **TypeScript**를 JavaScript로 변환하는 데 사용됩니다. Babel은 매우 **유연한 설정**을 제공하며, 다양한 플러그인과 설정을 통해 사용자가 원하는 형태로 코드를 변환할 수 있습니다. 하지만 Babel은 **빌드 속도**가 느리다는 단점이 있습니다.

### Esbuild

**Esbuild**는 **Go 언어로 작성된** 트랜스파일러로, **Babel보다 훨씬 빠른 성능**을 자랑합니다. Esbuild는 최신 ECMAScript 문법을 구버전 브라우저에 맞게 변환하거나, TypeScript를 자바스크립트로 트랜스파일하는 작업을 매우 빠르게 처리할 수 있습니다. 그러나 **기능이 Babel보다 간단**하므로, 고급 기능이 필요할 경우 Babel을 사용하는 것이 좋습니다.

> https://wndgur2.github.io/post/Babel_Esbuild

## 빌드 속도 개선을 위한 Vite와 Esbuild Migration

현재 빌드 속도를 향상시키기 위해 CRA에서 **Vite**와 **Esbuild** 환경으로 마이그레이션하는 방법을 고려하고 있습니다. Vite는 **빠른 개발 환경**을 제공하며, **Esbuild**는 빌드 속도가 빠르고 트랜스파일링 성능이 뛰어납니다. 이 두 가지 도구는 프로젝트의 빌드 속도를 획기적으로 개선할 수 있는 좋은 선택입니다.

또한, **CRA(Create React App)** 프로젝트에서는 Webpack을 기본 번들러로 사용하지만, 설정 변경이 제한적입니다. 이를 해결하기 위해 **CRACO(Create React App Configuration Override)**를 사용해 Webpack 설정을 오버라이드할 수 있습니다.
CRACO는 CRA 프로젝트의 **구성 파일을 쉽게 오버라이드**할 수 있도록 도와주는 도구입니다. 이를 통해 Webpack 설정을 수정할 수 있으며, **경로 별칭을 설정**하거나 추가적인 기능을 구현할 수 있습니다.

### 마이그레이션의 이점

1. **빠른 빌드 시간**: Esbuild와 Vite의 조합으로 빌드 속도가 크게 향상.
2. **간단한 설정**: Vite는 기본적으로 사전 구성된 최적화 설정 제공.

## 결론

Webpack과 Vite는 각각의 장단점이 뚜렷한 도구입니다. Webpack은 복잡한 애플리케이션에 적합하며, Vite는 빠른 개발 환경을 제공합니다. 프로젝트 요구사항에 따라 적절한 도구를 선택하거나, 트랜스파일러(Babel/Esbuild)를 조합해 최적의 개발 환경을 구축하는 것이 중요합니다.

---

### 참고

- https://webpack.js.org/
- https://vite.dev/
