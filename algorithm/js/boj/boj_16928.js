let fs = require('fs')
// let lines = fs.readFileSync('inputs').toString().trim().split('\n')
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const [L, S] = lines.shift().split(' ').map(Number)

const dp = Array(100).fill(Infinity)
const board = Array(100).fill(0)

lines = lines.map((v) => v.split(' ').map(Number))

for (let i = 0; i < lines.length; i++) {
  const [from, to] = lines[i]
  board[from - 1] = to - 1
}

const q = [[0, 0]]

while (q.length > 0) {
  const [kan, turn] = q.shift()
  if (turn < dp[kan]) dp[kan] = turn
  else continue
  for (let dice = 6; dice >= 1; dice--) {
    const nk = board[kan + dice] ? board[kan + dice] : kan + dice
    if (dp[nk] > turn + 1) q.push([nk, turn + 1])
  }
}

console.log(dp[99])
