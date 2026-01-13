let fs = require('fs')
// let lines = fs.readFileSync('inputs').toString().trim().split('\n')
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const T = Number(lines.shift())
for (let t = 0; t < T; t++) {
  const N = Number(lines.shift())
  const vs = lines.splice(0, N - 1).map((v) => v.split(' ').map(Number))
  const [A, B] = lines
    .shift()
    .split(' ')
    .map((v) => Number(v) - 1)

  const ps = Array(N)
    .fill(0)
    .map((v, i) => i)

  for (let i = 0; i < N - 1; i++) {
    const [p, c] = vs[i]
    ps[c - 1] = p - 1
  }

  const aParents = new Set()
  let node = A
  aParents.add(node)
  while (ps[node] !== node) {
    aParents.add(ps[node])
    node = ps[node]
  }

  node = B
  while (!aParents.has(node)) {
    node = ps[node]
  }
  console.log(node + 1)
}
