---
category: Study
title: JS로 힙(heap) 구현하기
date_started: 2025.12.02
date_updated: 2025.12.02
tags: heap, data-structure, javascript, priority-queue
github: "https://github.com/wndgur2/BlogDB/blob/main/posts/study/js_heap.md"
---

![heap](https://github.com/user-attachments/assets/d1f75775-8196-40ef-9c65-5998e1ee4b56)

## javascript로 왜 힙을 구현해야할까?

js로 알고리즘을 풀다 보니, 구현해야하는 자료구조들이 있었다.

특히 우선순위 큐가 그랬다.

이를 직접 구현하는 것에 익숙하지 않다보니 해당 자료구조를 사용하지 않고 문제를 풀고자 하게 되었다.

숙련도 때문에 문제 해결에 제약이 걸리는 것을 방지하고자 이를 구현하는 연습을 하고자 한다.

아마 다음엔 Linked List를 통한 dequeue를 구현해볼 것 같다.


> ### 목차
> 1. 힙이란?  
> 2. 힙 들여다보기  
> 3. javascript로 힙 구현하기  

## 1. 힙이란?

완전이진트리의 한 종류이다.
비교 연산을 통해 트리의 최고 요소를 `root`로 가진다는 특징이 있다.
예를 들어 비교 연산이 `<`(less than) 이라면 루트는 가장 작은 값을 가지게 되고 이를 `min-heap`이라 한다.

트리를 사용한 덕분에 삽입과 삭제 연산이 `O(logN)` 만에 수행된다. 트리의 `depth` 만큼만 수행하면 되기 때문이다.

주의해야할 점은, 모든 요소가 아니라 `부모-자식` 관계만 크기가 정렬되어있다는 점이다. 이 때문에 보통 `top`을 뽑아 쓰는 용도로만 사용한다.

## 2. 힙 들여다보기

그럼 이 완전이진트리인 힙은 어떻게 정렬을 유지하는지 삽입과 삭제 과정을 살펴보자.
여기서는 편의를 위해 `min-heap`을 예시로 적었다.

### 2-1 삽입 insert(Node)

<img alt="heap_insert" src="https://github.com/user-attachments/assets/0c051d79-0632-49bf-a6d7-33863f578b1c" />

맨 마지막에 `새로운 노드`를 추가하고, 이 `새로운 노드`를 알맞은 `depth`까지 끌어올린다.  
`min-heap`의 경우 `새로운 노드`의 부모 노드가 `새로운 노드`보다 큰 값을 가질 경우 자리를 바꾸는 과정을 반복한다.

트리의 `depth` 만큼 수행되므로 시간복잡도는 `O(logN)`가 된다.

### 2-2 삭제 pop()
  
> heap에서의 삭제는, 보통 `root`를 삭제하는 `pop()`을 의미한다.  
> `root`를 제외한 노드는 자료구조의 용도, 성능을 이유로 삭제를 구현하지는 않는 것 같다.

<img alt="heap_pop" src="https://github.com/user-attachments/assets/8bf4dd28-442e-4764-9fb5-7dcb6609bdb2" />

우선 `root` 자리를 마지막 노드로 대체한다. (삭제 후에도 완전 이진 트리를 유지하기 위한 좋은 방법 같다.) 이제 `root` 노드를 적당한 자리로 보내기 위해 아래 과정을 거친다.

대체할 우선순위가 높은 자식 노드를 찾는다. `min-heap`에서는 더 작은 자식 노드가 `root`와 자리를 바꾸게 된다. 

내려간 자리에서 같은 과정을 반복한다. 즉, 리프노드가 되거나 자식들보다 본인의 우선순위가 높을 때까지 우선순위가 높은 자식노드와 자리를 바꾼다.

트리의 `depth` 만큼 수행되므로 시간복잡도는 `O(logN)`가 된다.

### 2-3 조회 top()

트리의 `root`를 조회하여 값을 반환한다.

트리의 `root`는 배열의 0번 인덱스로 즉시 접근 가능하므로 시간복잡도는 `O(1)`가 된다.

## 3. javascript로 힙 구현하기

완전이진트리는 배열로 쉽게 구현 가능하다. 인덱스 `i`의 자식을 `i*2+1`, `i*2`로 접근 가능하다.

함수 `isPrior(a, b)`에 상황에 필요한 비교 연산을 넣을 수 있도록 했다.

```javascript


    class Heap {
      constructor(isPrior) {
        this.nodes = []
        this.isPrior = isPrior 
      }

      top () {
        return this.nodes[0]
      }

      insert (node) {
        this.nodes.push(node)
        let i = this.nodes.length - 1

        // sift up
        while (i > 0) {
          const pi = i - 1 >> 1
          if (this.isPrior(this.nodes[i], this.nodes[pi])) {
            ;[this.nodes[i], this.nodes[pi]] = [this.nodes[pi], this.nodes[i]]
            i = pi
          } else {
            break
          }
        }
      }

      pop () {
        if (this.nodes.length === 0) return

        const res = this.nodes[0]
        if (this.nodes.length === 1) {
          this.nodes.pop()
          return res
        }

        this.nodes[0] = this.nodes.pop()

        const size = this.nodes.length
        let i = 0

        // sift down
        while (true) {
          const l = i * 2 + 1
          const r = l + 1
          let best = i

          if (l < size && this.isPrior(this.nodes[l], this.nodes[best])) {
            best = l
          }
          if (r < size && this.isPrior(this.nodes[r], this.nodes[best])) {
            best = r
          }

          if (best === i) break

            ;[this.nodes[i], this.nodes[best]] = [this.nodes[best], this.nodes[i]]
          i = best
        }

        return res
      }
    }

```

> 여기서 sift up은 노드가 자기 자리를 찾을 때까지 부모와 자리를 바꾸는 과정을,  
> sift down은 노드가 자기 자리를 찾을 때까지 자식과 자리를 바꾸는 과정을 말한다.

생성 예

```javascript
const pq = new Heap((a, b)=> a < b) // min-heap
```