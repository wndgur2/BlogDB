const MAX_COST = 1_000_000_000

const fs = require('fs')
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

const costs = Array(N)

costs.fill(MAX_COST)
dijkstra(0, costs)

let fn = costs.reduce((p, c, i)=>costs[p]>costs[i]?p:i, 0)

costs.fill(MAX_COST)
dijkstra(fn, costs)

const res = costs.reduce((p, c)=> c>p?c:p, 0)
console.log(res)

function dijkstra(st, costs){
  const q = [[st, 0]]
  let qi = 0
  while(qi<q.length){
    const [cn, cc] = q[qi++]
    
    if(costs[cn] < cc) continue
    costs[cn] = cc

    for(let i=0; i<edges[cn].length; i++){
      const [nn, nc] = edges[cn][i]
      if(costs[nn] <= cc + nc) continue
      q.push([nn, cc + nc])
      costs[nn] = cc + nc
    }
  }
}