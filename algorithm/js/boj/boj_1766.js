let fs = require('fs')
// let inputs = fs.readFileSync('inputs').toString().trim().split('\n');
let inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

const [N, M] = inputs[0].split(' ').map(v=>Number.parseInt(v))
const compares = inputs.filter((_,i)=>i!=0).map(row=>Array.from(row.split(' ').map(v=>Number.parseInt(v))))

// 노드 (문제)

class Node {
  constructor(number){
    this.number = number
    this.afterNodes = []
    this.preNodesCount = 0
  }
}

class PriorityQueue{
  constructor(compare){
    this.queue = []
    this.compare = compare
  }

  top(){
    return this.queue[0]
  }

  pop(){
    if(this.queue.length===0) return
    
    const newTop = this.queue.pop()
    const length = this.queue.length
    if(length===0) return

    this.queue[0] = newTop

    for(let i=0; i<length;){
      const l = i*2+1
      const r = i*2+2
      let bestNodeIndex = i
      // 셋 중 가장 작은 값 찾기
      if(l<length){
        if(this.compare(this.queue[l], this.queue[bestNodeIndex])){
          bestNodeIndex = l
        }
      }
      if(r<length){
        if(this.compare(this.queue[r], this.queue[bestNodeIndex])){
          bestNodeIndex = r
        }
      }

      if(bestNodeIndex===i)
        break

      const temp = this.queue[i]
      this.queue[i] = this.queue[bestNodeIndex]
      this.queue[bestNodeIndex] = temp
      i = bestNodeIndex
    }
  }

  insert(node){
    this.queue.push(node)
    for(let i = this.queue.length-1; i>0;){
      const p = Number.parseInt((i-1)/2)
      if(this.compare(this.queue[i], this.queue[p])){
        const temp = this.queue[i]
        this.queue[i] = this.queue[p]
        this.queue[p] = temp
        i = p
      } else break
    }
  }
}

const nodes = new Map()
const solvableNodes = new PriorityQueue((a, b)=>a.number<b.number)

for(let i=1; i<=N; i++){
  nodes.set(i, new Node(i))
}

compares.forEach(([a, b])=>{
  nodes.get(a).afterNodes.push(nodes.get(b))
  nodes.get(b).preNodesCount++
})

nodes.forEach(node=>{
  if(node.preNodesCount===0){
    solvableNodes.insert(node)
  }
})

const res = []

while(solvableNodes.queue.length>0){
  const top = solvableNodes.top()
  solvableNodes.pop()
  top.afterNodes.forEach(node=>{
    if(--node.preNodesCount===0){
      solvableNodes.insert(node)
    }
  })
  res.push(top.number)
}

console.log(res.join(' '))