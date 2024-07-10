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
