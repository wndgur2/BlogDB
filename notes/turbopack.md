---
category: Study
title: Turbopack
date_started: 2024.12.14
date_updated: 2024.12.14
tags: bundler, browser
---

<img src='https://github.com/user-attachments/assets/bf424249-6c33-4964-a258-0c9b502fbcf3' alt='turbopack' />

# Turbopack: RSC와 TS를 위한 고성능 번들러

웹 개발에서 **번들러**(Bundler)는 현대적인 웹 애플리케이션을 만드는 데 필수적인 도구입니다. 오늘은 **Turbopack**이라는 Next.js에 최적화된 최신 번들러를 소개하려 합니다. 왜 Turbopack이 필요한지, 그리고 기존 번들러와 무엇이 다른지 알아보겠습니다.

> Next.js와 Turbopack 공식 문서를 번역한 내용입니다.

> Turbopack은 현재 Next.js 개발 서버에서만 사용할 수 있습니다.  
> 프로덕션 빌드는 아직 지원하지 않습니다.

---

## Turbopack이 등장한 배경

Next.js 팀은 성능 개선을 위해 수많은 JS 기반 도구를 교체해왔습니다. Babel과 Terser를 대체했고, 이제 목표는 Webpack을 대체하는 것이었습니다. 그러나 시장에 나와 있는 다른 번들러들을 검토한 끝에, 우리는 새로운 번들러를 직접 개발하기로 결정했습니다. **왜냐하면 Turbopack은 기존 번들러들이 해결하지 못했던 문제들을 혁신적으로 해결하기 때문입니다.**

---

## Turbopack의 주요 특징

### 1. 통합 그래프 (Unified Graph)

Next.js와 같은 프레임워크가 인기를 끌게 된 큰 이유 중 하나는, 현재 세대의 번들러로 SSR(서버 사이드 렌더링)이나 RSC(React Server Components)와 같은 기능을 구현하는 것이 간단하지 않기(non-trivial하기) 때문입니다. 각 출력 환경(브라우저, 서버 등)에 맞는 여러 컴파일러를 만들어야 하고, 이 컴파일러들이 생성한 번들이 올바르게 결합되도록 서로 간의 통신을 관리해야 합니다.

우리는 이러한 유지 관리의 부담을 Next.js와 Turbopack을 사용하는 모든 프레임워크에서 제거하고자 했습니다. 또한, 여러 환경을 위한 번들을 생성할 수 있는 단일 통합 그래프를 설계함으로써 더 깔끔하고 안정적인 구현을 만들 수 있었습니다.

### 2. 번들링 vs 네이티브 ESM

Vite와 같은 프레임워크는 개발 모드에서 애플리케이션 소스 코드를 번들링하지 않는 방식을 사용합니다. 대신 브라우저의 네이티브 ES 모듈(ES Modules) 시스템에 의존합니다. 이 방식은 단일 파일만 변환하면 되기 때문에 매우 빠른 업데이트를 제공할 수 있습니다.

우리도 이 방식을 실험해 보았지만, 많은 모듈로 구성된 대규모 애플리케이션에서는 확장성 문제를 겪었습니다. 브라우저에서 발생하는 수많은 네트워크 요청이 초기 실행 시간을 상대적으로 느리게 만들었기 때문입니다. 브라우저 입장에서는, 코드가 가능한 적은 네트워크 요청으로 전달될 때 실행 속도가 더 빨라집니다. 이는 로컬 서버에서도 마찬가지입니다.

이런 이유로, 우리는 webpack처럼 Turbopack이 개발 서버에서 코드를 번들링하도록 결정했습니다. 특히 Turbopack은 Rust로 작성되어 있으며, 프로덕션에서만 필요한 최적화 작업을 건너뛰기 때문에 대규모 애플리케이션에서도 훨씬 빠르게 동작할 수 있습니다.

### 3. 증분 계산 (Incremental Computation)

Turbopack은 두 가지 방식을 사용해 속도를 극대화합니다:
- **작업 최소화**: 불필요한 작업을 줄입니다.
- **병렬 처리**: 여러 코어에서 작업을 동시에 처리합니다.

Turbo 엔진은 작업 결과를 캐싱하여 동일한 작업을 반복하지 않도록 설계되었습니다. 이를 통해 최소한의 작업으로 최대 속도를 달성합니다.

### 4. 지연 번들링 (Lazy Bundling)

기존의 Next.js는 개발 모드에서 전체 웹 앱을 번들링했지만, 이는 비효율적이라는 결론에 도달했습니다. 최신 Next.js는 요청된 페이지와 해당 모듈만 번들링하는 방식으로 전환되었습니다. Turbopack은 이 접근법을 더욱 효율적으로 구현하며, 요청된 모듈만 번들링하여 빠른 개발 서버를 제공합니다.

---

## 시작하기

Turbopack은 JavaScript와 TypeScript에 최적화된 **증분 번들러**로, Rust로 작성되었습니다. Webpack과 Next.js를 개발한 Vercel 팀이 10년간의 경험과 최신 기술을 바탕으로 설계한 Turbopack은 미래의 컴퓨팅을 지원할 준비가 되어 있습니다.

**Turbopack의 성능 비결**은 두 가지입니다:
- **고도로 최적화된 머신 코드**
- **낮은 수준의 증분 계산 엔진**

한 번 작업한 결과는 캐싱되어 동일한 작업을 다시 수행하지 않습니다. 또한 Turbopack은 Turborepo와 Google의 Bazel에서 영감을 받은 혁신적인 증분 계산 방식을 사용합니다.

Turbopack은 현재 Next.js 개발 서버에서 사용할 수 있습니다. 아래 명령어를 사용해 Turbopack을 체험해보세요:

```
npm run dev -- --turbopack
```

**주의:** `next build` 명령어를 사용한 프로덕션 빌드는 아직 지원되지 않습니다.

이슈가 있다면 Next.js 리포지토리의 이슈 템플릿을 사용해 피드백을 보내주세요. 여러분의 의견은 Turbopack의 발전에 큰 도움이 됩니다.

---

## 요약: Turbopack을 선택한 이유

Turbopack은 다음과 같은 이유로 탄생했습니다:

1. **통합 그래프 지원**: 단일 컴파일러로 여러 환경을 타겟팅할 수 있습니다.
2. **번들러 성능 최적화**: 큰 애플리케이션에서도 Native ESM보다 효율적입니다.
3. **증분 계산 도입**: 작업량을 줄이고 속도를 극대화합니다.
4. **lazy asset graph**: 필요한 asset만 번들링하여 빠른 시작 속도를 제공합니다.

---

## 참고

- https://nextjs.org/docs/app/api-reference/turbopack
- https://turbo.build/pack/docs