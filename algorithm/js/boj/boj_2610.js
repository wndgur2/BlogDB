const fs = require('fs');
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')

const N = Number(lines[0])
const edges = lines.splice(2).map(v=>v.split(' ').map(Number))

let parents = Array(N).fill(0).map((_,i)=>i)

function find(a){
  if(parents[a]===a) return a
  else return (parents[a] = find(parents[a]))
}

function union(a, b){
  const pa = find(a)
  const pb = find(b)
  if(pa == pb) return
  parents[pb] = pa
}

const edgeLists = Array(N+1).fill(0).map(v=>new Set())

for(let [a, b] of edges){
  a--;b--;
  edgeLists[a].add(b)
  edgeLists[b].add(a)

  union(a, b)
}

const membersByGroup = new Map()

parents = parents.map(find)

for(let i=0; i<N; i++){
  const group = parents[i]
  if(!membersByGroup.has(group)){
    membersByGroup.set(group, [])
  }
  membersByGroup.get(group).push(i)
}

const caps = []

for(const members of membersByGroup.values()){
  members.sort((a,b)=>a-b)
  // 최단 최장거리 대표 뽑기
  let minMaxD = 100
  let cap = 0
  for(let i=0; i<members.length; i++){
    const cc = members[i]
    let maxD = 0

    const visited = Array(N).fill(false)

    // bfs
    const q = [[cc, 0]]
    visited[cc] = true
    let head = 0
    while(head < q.length){
      const [m, c] = q[head++]
      if(c>maxD) maxD = c
      for(const nm of edgeLists[m]){
        if(visited[nm]) continue
        visited[nm] = true
        q.push([nm, c+1])
      }
    }

    if(maxD < minMaxD) {
      minMaxD = maxD
      cap = cc
    }
  }

  caps.push(cap+1)
}

caps.sort((a,b)=>a-b)

console.log(membersByGroup.size)
console.log(caps.join('\n'))
