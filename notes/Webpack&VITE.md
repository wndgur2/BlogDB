---
category: Study
title: Webpack&Vite
date_started: 2024.12.04
tags: Web
---

# Web Bundler: 웹 애플리케이션을 효율적으로 관리하기 위한 도구

웹 개발에서는 여러 파일을 번들링(bundle)하여 최적화된 형태로 웹 브라우저에 전달하는 작업이 중요합니다. 이를 돕는 도구가 바로 **웹 번들러**입니다. 웹 번들러는 코드의 모듈을 결합하여 하나의 파일로 만들어주고, 그 과정에서 파일 크기를 줄이는 작업도 수행합니다. 또한, 각 모듈 간의 의존성을 처리하고, 다양한 브라우저 환경을 고려한 최적화 작업을 해줍니다.

## Webpack과 Vite: 두 가지 주요 웹 번들러 비교

웹 번들러 중에서 가장 많이 사용되는 두 가지 도구는 **Webpack**과 **Vite**입니다. 이 두 도구는 비슷한 목적을 가지고 있지만, 각기 다른 방식으로 작업을 처리합니다.

### Webpack (https://webpack.js.org/)

![webpack](https://github.com/user-attachments/assets/f12830cc-dc8e-4c54-b2cf-9fae7fb69452)

**Webpack**은 **모듈 번들링**을 위한 도구로, 모든 의존성을 처리하고 여러 파일을 하나로 묶어주는 역할을 합니다. 복잡한 설정을 통해 자바스크립트, CSS, 이미지 파일 등을 효율적으로 번들링할 수 있습니다. 그러나 Webpack은 **구성이 복잡**하고, **빌드 속도**가 느려질 수 있습니다.

여기서 중요한 점은 **Babel**입니다. Webpack은 보통 **Babel**과 함께 사용되어, 최신 자바스크립트 문법을 구버전 브라우저에서 호환 가능한 코드로 변환합니다. Webpack의 설정 파일을 통해 Babel을 쉽게 통합할 수 있기 때문에, 최신 ECMAScript 문법을 사용하면서도 브라우저 호환성을 유지할 수 있습니다.

### Vite (https://vite.dev/)

![vite](https://github.com/user-attachments/assets/163a9103-eb0f-4f9a-9c4a-7c00eb2b7287)

반면, **Vite**는 Webpack의 단점을 보완하고자 만든 툴로, **빠른 개발 환경**을 제공하는 것이 특징입니다. Vite는 **ES 모듈(ESM)**을 기반으로 페이지를 빠르게 로드하고, 개발 중에는 **핫 모듈 리플레이스먼트(HMR)** 기능을 통해 페이지를 새로 고침하지 않고도 변경 사항을 즉시 반영할 수 있습니다. Vite는 **ESBuild**를 사용하여 트랜스파일링을 처리하는데, 이 덕분에 **빌드 속도**가 매우 빠릅니다.

따라서 Vite에서는 **ESBuild**가 Babel의 역할을 대신하면서, **속도** 면에서 훨씬 효율적이고 빠른 성능을 자랑합니다. 다만, Babel보다 **기능이 적을 수** 있기 때문에, 고급 트랜스파일링 기능이 필요하면 Babel을 사용할 수 있습니다.

## Babel과 Esbuild: Transpiler 비교

**트랜스파일러(Transpiler)**는 자바스크립트 코드를 다른 형식으로 변환하는 도구로, **최신 문법**을 구버전 브라우저에서 실행할 수 있도록 변환하거나, TypeScript를 JavaScript로 변환하는 데 사용됩니다.

### Babel

**Babel**은 주로 **최신 ECMAScript** 문법을 구버전 자바스크립트로 변환하거나, **TypeScript**를 JavaScript로 변환하는 데 사용됩니다. Babel은 매우 **유연한 설정**을 제공하며, 다양한 플러그인과 설정을 통해 사용자가 원하는 형태로 코드를 변환할 수 있습니다. 하지만 Babel은 **빌드 속도**가 느리다는 단점이 있습니다.

### Esbuild

**Esbuild**는 **Go 언어로 작성된** 트랜스파일러로, **Babel보다 훨씬 빠른 성능**을 자랑합니다. Esbuild는 최신 ECMAScript 문법을 구버전 브라우저에 맞게 변환하거나, TypeScript를 자바스크립트로 트랜스파일하는 작업을 매우 빠르게 처리할 수 있습니다. 그러나 **기능이 Babel보다 간단**하므로, 고급 기능이 필요할 경우 Babel을 사용하는 것이 좋습니다.

## CRA와 CRACO: 별도의 설정 파일 없이 해결하기

최근에 **CRA(Create React App)** 프로젝트에서 번들러 설정을 추가해야 할 필요가 있었습니다. CRA는 기본적으로 번들러 설정을 숨겨두기 때문에, 별도의 설정 파일을 수정할 수 없습니다. 예를 들어, **경로 별칭(path alias)**을 설정하려면 기본적으로 CRA에서는 이를 다룰 수 없었습니다.

이 문제를 해결하기 위해 **CRACO(Create React App Configuration Override)**를 사용했습니다. CRACO는 CRA 프로젝트의 **구성 파일을 쉽게 오버라이드**할 수 있도록 도와주는 도구입니다. 이를 통해 Webpack 설정을 수정할 수 있으며, **경로 별칭을 설정**하거나 추가적인 기능을 구현할 수 있습니다.

```json
// tsconfig.json
{
  "compilerOptions": {
    "baseUrl": ".",
    "paths": {
      "@/*": ["src/*"]
    }
  },
  "include": ["src"]
}
```

```javascript
// craco.config.js
const path = require('path')
const SpeedMeasurePlugin = require('speed-measure-webpack-plugin')
// const smp = new SpeedMeasurePlugin()

module.exports = {
  webpack: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
    },
    configure: (webpackConfig, { env, paths }) => {
      // Wrap the existing webpack configuration with SpeedMeasurePlugin
      // return smp.wrap(webpackConfig)
      return webpackConfig
    },
  },
}
```

## 빌드 속도 개선을 위한 Vite와 Esbuild Migration

현재 저는 빌드 속도를 향상시키기 위해 CRA에서 **Vite**와 **Esbuild** 환경으로 마이그레이션하는 방법을 고려하고 있습니다. Vite는 **빠른 개발 환경**을 제공하며, **Esbuild**는 빌드 속도가 빠르고 트랜스파일링 성능이 뛰어납니다. 이 두 가지 도구는 프로젝트의 빌드 속도를 획기적으로 개선할 수 있는 좋은 선택입니다.
