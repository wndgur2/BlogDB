// 단방향
// 음의 간선
// 최단거리

const fs = require('fs')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const [N, M] = lines.shift().split(' ').map(Number)
const costs = Array(N).fill(Infinity)

costs[0] = 0

const edges = []

for (let l = 0; l < M; l++) {
  const [a, b, c] = lines[l].split(' ').map(Number)
  edges.push([a - 1, b - 1, c])
}

let isCycle = false
bf()

function bf() {
  if (costs[0] > 0) costs[0] = 0
  for (let r = 0; r < N; r++)
    for (let i = 0; i < M; i++) {
      const [from, to, cost] = edges[i]
      const nc = costs[from] + cost
      if (nc < costs[to]) {
        costs[to] = nc
        if (r == N - 1) isCycle = true
      }
    }
}

for (let i = 0; i < N; i++) {
  if (costs[i] === Infinity) costs[i] = -1
}

console.log(isCycle ? -1 : costs.slice(1).join('\n').trim())
