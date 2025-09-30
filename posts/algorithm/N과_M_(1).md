---
category: Algorithm
title: N과 M (1)
site: BOJ
number: 15649
date_started: 2023.05.06
tags: cpp, DFS, backtracking
level: S3
---

# [문제](https://www.acmicpc.net/problem/15649)

1부터 N까지 수 중에 M개의 수를 포함하는 모든 순열을 사전순으로 출력하는 문제입니다.

# 문제 풀이

N이 최대 8개이므로 할만하다고 생각해서 완전탐색을 먼저 시도했다.  
BFS보다 DFS가 메모리를 덜 먹는다고 하여 recursion으로 dfs를 구현했다.

# 코드

```cpp
#include <stdio.h>
#include <vector>
#include <algorithm>
using namespace std; // N과 M

void dfs(int n, int m, vector<int> nums){
  if(m == 0){
    for(int i = 0; i < nums.size(); i++)
      printf("%d ", nums[i]);
    printf("\n");
    return;
  }
  for(int i = 1; i <= n; i++){
    if(find(nums.begin(), nums.end(), i) == nums.end()){
      nums.push_back(i);
      dfs(n, m - 1, nums);
      nums.pop_back();
    }
  }
}

int main(){
  int n, m;
  scanf("%d %d", &n, &m);
  vector<int> nums;
  dfs(n, m, nums);
  return 0;
}
```

감사합니다.
