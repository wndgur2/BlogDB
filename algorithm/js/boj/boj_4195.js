const fs = require('fs')

const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')

const nodes = new Map()
let parents = []
let cns = []

function find(a) {
  if (parents[a] === a) {
    return a
  } else {
    parents[a] = find(parents[a])
    return parents[a]
  }
}

function union(a, b) {
  const ra = find(a)
  const rb = find(b)
  if (ra !== rb) {
    parents[rb] = ra
    cns[ra] += cns[rb]
    find(b)
  }
}

const T = Number(inputs[0])
let line = 1
for (let tc = 0; tc < T; tc++) {
  const n = Number(inputs[line++])
  let id = 0
  nodes.clear()
  parents = []
  cns = []
  res = []

  for (let i = 0; i < n; i++) {
    const rel = inputs[line++].split(' ')
    // console.log(rel)
    if (!nodes.has(rel[0])) {
      nodes.set(rel[0], id++)
      parents.push(nodes.get(rel[0]))
      cns.push(1)
    }
    if (!nodes.has(rel[1])) {
      nodes.set(rel[1], id++)
      parents.push(nodes.get(rel[1]))
      cns.push(1)
    }
    const a = nodes.get(rel[0])
    const b = nodes.get(rel[1])
    union(a, b)
    res.push(cns[parents[a]])
  }
  console.log(res.join('\n'))
}
