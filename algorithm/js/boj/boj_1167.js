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

const MAX_COST = 1_000_000_000

const fs = require('fs')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')

const N = Number(lines.shift())
const edges = Array(N).fill(0).map(v=>[])

for(let i=0; i<lines.length; i++){
  const data = lines[i].split(' ').map(Number)
  data.pop()
  const from = data[0]-1
  for(let j=1; j<data.length-1; j+=2)
    edges[from].push([data[j]-1, data[j+1]])
}

const [fn, _] = dijkstra(0)
const [__, res] = dijkstra(fn)

console.log(res)

function dijkstra(st){
  const costs = Array(N).fill(-1)
  const q = new PQ((a, b)=>a[1]<b[1])
  let fn
  let mc=0
  q.push([st, 0])
  while(q.size()>0){
    const [cn, cc] = q.peek()
    q.pop()
    
    if(costs[cn]!==-1) continue
    costs[cn] = cc
    if(cc>mc){
      fn = cn
      mc = cc
    }

    for(let i=0; i<edges[cn].length; i++){
      const [nn, nc] = edges[cn][i]
      if(costs[nn]!==-1) continue
      q.push([nn, cc + nc])
    }
  }

  return [fn, mc]
}