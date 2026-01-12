const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const [N, Q] = inputs[0].split(' ').map(Number)
const sets = inputs
  .slice(1, N + 1)
  .map((v) => v.split(' ').map(Number))
  .map((v) => new Set(v.slice(1)))
const cmds = inputs.slice(N + 1).map((v) => v.split(' ').map(Number))

const res = []
for (let i = 0; i < Q; i++) {
  if (cmds[i][0] === 1) {
    const a = sets[cmds[i][1] - 1]
    const b = sets[cmds[i][2] - 1]
    if (a.size > b.size) {
      for (v of b) {
        a.add(v)
      }
      b.clear()
    } else {
      sets[cmds[i][1] - 1] = b
      for (v of a) {
        b.add(v)
      }
      sets[cmds[i][2] - 1] = new Set()
    }
  } else {
    res.push(sets[cmds[i][1] - 1].size)
  }
}

console.log(res.join('\n'))
