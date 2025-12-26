let fs = require('fs')
let inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// let inputs = fs.readFileSync('inputs').toString().trim().split('\n')
let [N, M, R] = inputs[0].split(' ').map(Number)

let edges = Array(N + 1)
  .fill(0)
  .map((v) => [])

inputs.slice(1).forEach((s) => {
  const [a, b] = s.split(' ').map(Number)
  edges[a].push(b)
  edges[b].push(a)
})

edges.forEach((arr) => arr.sort((a, b) => a - b))

const res = Array(N + 1).fill(0)
let seq = 1

dfs(R)

function dfs(node) {
  if (res[node] > 0) return
  res[node] = seq++
  edges[node].forEach((nextNode) => {
    dfs(nextNode)
  })
}

console.log(res.slice(1).join('\n'))
