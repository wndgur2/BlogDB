---
category: Study
title: Java
date_started: 2024.07.09
tags: Java
---

# Collection 프레임워크

> 출처: TCP

## 컬렉션 프레임워크(Collection framework)란?

컬렉션 프레임워크란 다양한 구조의 데이터를 쉽고 효과적으로 처리할 수 있게 정의해놓은 클래스의 집합을 의미한다.

즉, 자료 구조와 알고리즘을 클래스에 구현해 놓은 것이다.

이러한 컬렉션 프레임워크는 자바의 인터페이스(interface)를 사용하여 구현된다.

## 주요 인터페이스

1. List 인터페이스

2. Set 인터페이스

3. Map 인터페이스

![collection 상속관계](https://www.tcpschool.com/lectures/img_java_collection_interface_diagram.png)

Collection을 상속받는 List, Set, Queue와 달리, 키-값 구조인 Map은 별도로 정의된다.

| 인터페이스 | 구현 클래스                                 |
| ---------- | ------------------------------------------- |
| List<E>    | Vector, ArrayList, LinkedList, Stack, Queue |
| Set<E>     | HashSet, TreeSet                            |
| Map<K, V>  | HashMap, TreeMap, Hashtable, Properties     |

## 컬렉션 클래스(Collection class)

컬렉션 프레임워크에 속하는 인터페이스를 구현한 클래스를 컬렉션 클래스라고 한다.

-   ~~Vector, Hashtable~~: 기존 코드와 호환을 위한 클래스
-   ArrayList, HashMap

## 컬렉션 인터페이스

Collection 인터페이스는 컬렉션을 다루는데 가장 기본적인 동작들을 정의하고, 그것을 메소드로 제공하고 있다.

따라서 List와 Set 인터페이스에서는, 공통된 많은 부분을 Collection 인터페이스에서 상속받는다.

| 메소드                     | 설명                                                          |
| -------------------------- | ------------------------------------------------------------- |
| boolean add(E e)           | 해당 컬렉션(collection)에 전달된 요소를 추가함. (선택적 기능) |
| void clear()               | 해당 컬렉션의 모든 요소를 제거함. (선택적 기능)               |
| boolean contains(Object o) | 해당 컬렉션이 전달된 객체를 포함하고 있는지를 확인함.         |
| boolean equals(Object o)   | 해당 컬렉션과 전달된 객체가 같은지를 확인함.                  |
| boolean isEmpty()          | 해당 컬렉션이 비어있는지를 확인함.                            |
| Iterator<E> iterator()     | 해당 컬렉션의 반복자(iterator)를 반환함.                      |
| boolean remove(Object o)   | 해당 컬렉션에서 전달된 객체를 제거함. (선택적 기능)           |
| int size()                 | 해당 컬렉션의 요소의 총 개수를 반환함.                        |
| Object[] toArray()         | 해당 컬렉션의 모든 요소를 Object 타입의 배열로 반환함.        |

## List 컬렉션 클래스

-   List 인터페이스의 구현

### 특징

1. 요소의 저장 순서 유지
2. 같은 요소의 중복 저장 허용

### 종류

1. ArrayList<E>  
   내부적으로 배열 이용 -> 수정 시간 오래걸림.
2. LinkedList<E>  
   내부적으로 더블 링크드 리스트 이용 -> 수정 시간 상수
3. Vector<E>
4. Stack<E>

## List 인터페이스 메소드

| 메소드                     | 설명                                                                            |
| -------------------------- | ------------------------------------------------------------------------------- |
| boolean add(E e)           | 해당 리스트(list)에 전달된 요소를 추가함. (선택적 기능)                         |
| void add(int index, E e)   | 해당 리스트의 특정 위치에 전달된 요소를 추가함. (선택적 기능)                   |
| void clear()               | 해당 리스트의 모든 요소를 제거함. (선택적 기능)                                 |
| boolean contains(Object o) | 해당 리스트가 전달된 객체를 포함하고 있는지를 확인함.                           |
| boolean equals(Object o)   | 해당 리스트와 전달된 객체가 같은지를 확인함.                                    |
| E get(int index)           | 해당 리스트의 특정 위치에 존재하는 요소를 반환함.                               |
| boolean isEmpty()          | 해당 리스트가 비어있는지를 확인함.                                              |
| Iterator<E> iterator()     | 해당 리스트의 반복자(iterator)를 반환함.                                        |
| boolean remove(Object o)   | 해당 리스트에서 전달된 객체를 제거함. (선택적 기능)                             |
| boolean remove(int index)  | 해당 리스트의 특정 위치에 존재하는 요소를 제거함. (선택적 기능)                 |
| E set(int index, E e)      | 해당 리스트의 특정 위치에 존재하는 요소를 전달받은 객체로 대체함. (선택적 기능) |
| int size()                 | 해당 리스트의 요소의 총 개수를 반환함.                                          |
