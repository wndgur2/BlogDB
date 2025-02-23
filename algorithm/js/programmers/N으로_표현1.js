let dp = Array(32001).fill(9)
let unit = 0
let goal = 0

function solution(N, number) {
  unit = N
  goal = number
  dfs(0, 0)
  // console.log(dp)
  return dp[number]==9?-1:dp[number]
}

function dfs(cur, depth){
  // console.log('cur', cur, 'depth', depth, 'dp[cur]', dp[cur])
  if(cur<-32000 | cur>32000) return
  if(depth>8) return -1

  if(depth < dp[cur])
    dp[cur] = depth
  else return

  if(cur==goal) return depth

  dfs(cur+unit, depth+1)
  dfs(cur-unit, depth+1)
  dfs(cur*unit, depth+1)
  dfs(Number.parseInt(cur/unit), depth+1)

  if(depth>6) return
  dfs(cur+1, depth+2 - (unit==1?1:0))
  dfs(cur-1, depth+2 - (unit==1?1:0))
  dfs(cur+(unit*11), depth+2)
  dfs(cur-(unit*11), depth+2)
  dfs(cur*(unit*11), depth+2)
  dfs(Number.parseInt(cur/(unit*11)), depth+2)

  if(depth>5) return
  dfs(cur+11, depth+3 - (unit==1?1:0))
  dfs(cur-11, depth+3 - (unit==1?1:0))
  dfs(cur+(unit*111), depth+3)
  dfs(cur-(unit*111), depth+3)
  dfs(cur*(unit*111), depth+3)
  dfs(Number.parseInt(cur/(unit*111)), depth+3)

  if(depth>4) return
  dfs(cur+111, depth+4 - (unit==1?1:0))
  dfs(cur-111, depth+4 - (unit==1?1:0))
  dfs(cur+(unit*1111), depth+4)
  dfs(cur-(unit*1111), depth+4)
  dfs(cur*(unit*1111), depth+4)
  dfs(Number.parseInt(cur/(unit*1111)), depth+4)
}

console.log(solution(5, 12))