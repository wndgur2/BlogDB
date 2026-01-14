class PQ {
  q = []
  constructor(comparator) {
    this.comparator = comparator
  }
  size() {
    return this.q.length
  }
  push(v) {
    this.q.push(v)
    for (let i = this.q.length - 1; i > 0; ) {
      const p = Number.parseInt((i - 1) / 2)
      if (this.comparator(this.q[i], this.q[p])) {
        this.#swap(p, i)
      }
      i = p
    }
  }
  peek() {
    return this.q[0]
  }
  pop() {
    this.q[0] = this.q[this.q.length - 1]
    this.q.pop()
    for (let i = 0; i < this.q.length; ) {
      const l = i * 2 + 1
      const r = l + 1
      let best = i
      if (this.q[l] && this.comparator(this.q[l], this.q[best])) {
        best = l
      }
      if (this.q[r] && this.comparator(this.q[r], this.q[best])) {
        best = r
      }
      if (best === i) break
      this.#swap(best, i)
      i = best
    }
  }
  #swap(i1, i2) {
    const t = this.q[i1]
    this.q[i1] = this.q[i2]
    this.q[i2] = t
  }
}

const fs = require('fs')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
let lp = 0
const T = Number(lines[lp++])

const res = []

for (let t = 0; t < T; t++) {
  const N = Number(lines[lp++])
  let numbers = lines
    .slice(lp, lp + Number.parseInt(N / 10) + 1)
    .map((v) => v.split(' ').map(Number))
    .reduce((p, c) => p.concat(c), [])

  lp += Number.parseInt(N / 10) + 1
  res.push(Number.parseInt((N + 1) / 2))

  const tres = []
  const maxPQ = new PQ((a, b) => a > b)
  const minPQ = new PQ((a, b) => a < b)

  for (let i = 0; i < N; i++) {
    if (minPQ.size() && numbers[i] < minPQ.peek()) {
      maxPQ.push(numbers[i])
    } else {
      minPQ.push(numbers[i])
    }

    if (maxPQ.size() > minPQ.size() + 1) {
      minPQ.push(maxPQ.peek())
      maxPQ.pop()
    } else if (minPQ.size() > maxPQ.size() + 1) {
      maxPQ.push(minPQ.peek())
      minPQ.pop()
    }

    if (!(i & 1)) {
      tres.push(maxPQ.size() > minPQ.size() ? maxPQ.peek() : minPQ.peek())
    }
  }
  while (tres.length > 0) {
    res.push(tres.splice(0, 10).join(' '))
  }
}

console.log(res.join('\n'))
