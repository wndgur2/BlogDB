const fs = require('fs')

const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const N = Number(lines[0])
const M = Number(lines[1])

const friends = Array(N).fill(0).map(_=>new Set())
const enemies = Array(N).fill(0).map(_=>new Set())

for(let i=0; i<M; i++){
  const [type, a, b] = lines[2+i].split(' ')
  const [p, q] = [Number(a)-1, Number(b)-1]
  if(type=='F'){
    friends[p].add(q)
    friends[q].add(p)
  } else{
    enemies[p].add(q)
    enemies[q].add(p)
  }
}

// 적의 적은 친구이다.
for(let i=0; i<N; i++){
  const fs = new Set()
  
  for(const e of enemies[i]){
    for(const f of enemies[e])
      fs.add(f)
  }
  
  for(const f of fs){
    if(i!=f) friends[i].add(f)
  }
}

const parents = Array(N)

function find(a){
  if(parents[a]==a) return a
  parents[a] = find(parents[a])
  return parents[a]
}

function union(a, b){
  const ra = find(a)
  const rb = find(b)
  if(ra != rb){
    parents[b] = ra
  }
}


for(let i=0; i<N; i++) parents[i]=i
for(let i=0; i<N; i++){
  const fs = Array.from(friends[i])
  for(let j=0; j<fs.length; j++){
    const f = fs[j]
    union(i, f)
  }
}

const uniqueTeams = new Set()
for(let i=0; i<parents.length; i++) uniqueTeams.add(parents[i])
console.log(uniqueTeams.size)