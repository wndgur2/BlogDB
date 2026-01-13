class Dequeue {
  head = null
  tail = null
  sum = 0
  size = 0

  pop() {
    if (this.size === 0) return null

    const res = this.head

    this.head = res.next
    if (this.head) {
      this.head.prev = null
    } else {
      this.tail = null
    }

    this.sum -= res.value
    this.size--

    return res
  }

  push(node) {
    if (this.size === 0) {
      this.head = node
      this.tail = node
    } else {
      this.tail.next = node
      node.prev = this.tail
      this.tail = node
    }
    this.sum += node.value
    this.size++
  }
}

class Node {
  prev = null
  next = null
  constructor(v) {
    this.value = v
  }
}

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

const q = new Dequeue()
q.push(new Node([0, 0]))

while (q.size > 0) {
  const [kan, turn] = q.pop().value
  if (turn < dp[kan]) dp[kan] = turn
  else continue
  for (let dice = 6; dice >= 1; dice--) {
    const nk = board[kan + dice] ? board[kan + dice] : kan + dice
    if (dp[nk] > turn + 1) q.push(new Node([nk, turn + 1]))
  }
}

console.log(dp[99])
