class Node{
  children = []

  constructor(index, parent){
    this.index = index
    this.parent = parent
  }
}

let fs = require('fs')
// let lines = fs.readFileSync('inputs').toString().trim().split('\n')
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')

const N = Number(lines[0])
const populations = lines[1].split(' ').map(Number)
const edges = Array(N).fill(0).map(v=>[])
for(let l=2; l<1+N; l++){
  const [a, b] = lines[l].split(' ').map(v=>Number(v)-1)
  edges[a].push(b)
  edges[b].push(a)
}

const nodes = Array(N).fill(0).map((_,i)=>new Node(i,i))

// make tree
let qi = 0
const q = [[0, 0]]
while(qi<q.length){
  const [cur, from] = q[qi++]
  const node = nodes[cur]
  node.parent = from
  node.children = edges[cur].filter((next)=>next!=from)
  for(let j=0; j<node.children.length; j++){
    const next = node.children[j]
    q.push([next, cur])
  }
}

const dp = Array(N).fill(-1)

dfs(0)

console.log(dp[0])

function dfs(node){
  const {parent, children} = nodes[node]
  const isRoot = parent==node
  
  dp[node]= populations[node]

  if(children.length==0){
    if(!isRoot)
       dp[parent] = Math.max(
          dp[node], // 나를 우수
          dp[parent] // 부모를 우수
        )
  } else {
    let childSum = 0
    for(let i=0; i<children.length; i++){
      const child = children[i]
      dfs(child)
      childSum += dp[child]
    }
    dp[node] = Math.max(childSum, dp[node])
    if(!isRoot) dp[parent] = Math.max(dp[node], childSum + dp[parent])
  }
}