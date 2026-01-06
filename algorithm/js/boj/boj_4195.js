const fs = require('fs')

const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')

class Node {
  constructor(name) {
    this.name = name
    this.parent = this
    this.cn = 1
  }

  cp(p) {
    const dc = this.cn
    // if (p.name == '1') {
    //   console.log('changing 1 cn', p.cn, 'to', p.cn + dc)
    //   console.log(dc)
    // }
    this.parent.cn -= dc
    this.parent = p
    p.cn += dc
  }
}

function find(a) {
  if (a.parent === a) {
    return a
  } else {
    a.cp(find(a.parent))
    return a.parent
  }
}

function union(a, b) {
  const ra = find(a)
  const rb = find(b)
  if (ra !== rb) {
    rb.cp(ra)
    find(b)
  }
}

const T = Number(inputs[0])
let line = 1
for (let tc = 0; tc < T; tc++) {
  const n = Number(inputs[line++])
  const nodes = new Map()
  for (let i = 0; i < n; i++) {
    const rel = inputs[line++].split(' ')
    // console.log(rel)
    if (!nodes.has(rel[0])) nodes.set(rel[0], new Node(rel[0]))
    if (!nodes.has(rel[1])) nodes.set(rel[1], new Node(rel[1]))
    const a = nodes.get(rel[0])
    const b = nodes.get(rel[1])
    union(a, b)
    console.log(a.parent.cn)
    // Array.from(nodes.keys()).map((k) => {
    //   const node = nodes.get(k)
    //   console.log(node.name, node.parent.name, node.cn)
    // })
  }
}
