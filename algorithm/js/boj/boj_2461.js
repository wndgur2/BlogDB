class PQ{
  nodes = []

  constructor(compare){
    this.compare = compare
  }

  peek(){
    return this.nodes[0]
  }

  pop(){
    if(this.nodes.length==0) return
    const res = this.nodes[0]
  
    this.nodes[0] = this.nodes[this.nodes.length-1]
    this.nodes.pop()  

    let head=0
    while(head<this.nodes.length){
      const l = head*2+1
      const r = l+1
      let best = head

      const lNode = this.nodes[l]
      const rNode = this.nodes[r]

      if(l<this.nodes.length && this.compare(lNode, this.nodes[best])) best = l
      if(r<this.nodes.length && this.compare(rNode, this.nodes[best])) best = r

      if(best===head) break

      ;[this.nodes[best], this.nodes[head]] = [this.nodes[head], this.nodes[best]]
      head = best
    }

    return res
  }

  push(node){
    this.nodes.push(node)
    let index = this.nodes.length-1

    while(index>0){
      const p = Math.floor((index-1)/2)
      if(this.compare(this.nodes[index], this.nodes[p])){
        ;[this.nodes[index],this.nodes[p]] = [this.nodes[p],this.nodes[index]]
      }
      index = p
    }
  }
}

const MAX_VALUE = 1_000_000_000

const fs = require('fs')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const [N, M] = lines.shift().split(' ').map(Number)

const clses = lines.map(cls=>cls.split(' ').map(Number).sort((a,b)=>a-b))

const minHeap = new PQ((a, b)=> a.value<b.value)
let mx = 0

for(let c=0; c<N; c++){
  const value = clses[c][0]
  minHeap.push({cls:c, index:0, value})
  if(value>mx) mx=value
}    

let res = MAX_VALUE
while(true){
  const {cls, index, value} = minHeap.peek()

  const df = mx-value
  if(df<res) res=df

  if(index===M-1) break
  minHeap.pop()

  const ni = index+1
  const nv = clses[cls][ni]
  minHeap.push({cls, index:ni, value:nv})
  if(nv>mx) 
    mx = nv
}

console.log(res)