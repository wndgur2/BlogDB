const fs = require('fs')
const lines = fs.readFileSync("/dev/stdin").toString().trim().split('\n')
// const lines = fs.readFileSync("inputs").toString().trim().split('\n')

// set, map 활용 그래프 생성 => BFS

const [N, K] = lines[0].split(' ').map(Number)
const codes = lines.splice(1, lines.length-2)
const [start, end] = lines[lines.length-1].split(' ').map(v=>codes[Number(v)-1])
const codeToIdx = new Map()

const codeSet = new Set()
for(let i=0; i<N; i++){
  const code = codes[i]
  codeToIdx.set(code, i)
  codeSet.add(code)
}

// 그래프 생성 (1000 x 30)
const edges = new Map()
for(const code of codes){
  for(let i=0; i<K; i++){
    // code의 0번째 자리로 이동
    const nextCode = code.slice(0, i) + (1-code[i]).toString() + code.slice(i+1)
    
    if(!codeSet.has(nextCode)) continue

    if(!edges.has(code)) edges.set(code, new Set())
    edges.get(code).add(nextCode)

    // if(!edges.has(nextCode)) edges.put(nextCode, new Set())
    // edges.get(nextCode).add(code)
  }
}

const answer = bfs()

console.log(answer)

function bfs(){
  // BFS (1000)

  const visited = Array(N).fill(false)

  const q = [[start]]
  let head = 0

  while(head<q.length){
    const path = q[head++]
    const cur = path[path.length-1]
    if(cur == end){
      return path.map(v=>codeToIdx.get(v)+1).join(' ')
    }
    if(!edges.has(cur)) continue
    for(const next of edges.get(cur)){
      const nextIdx = codeToIdx.get(next)
      if(visited[nextIdx]) continue
      visited[nextIdx] = true
      q.push([...path, next])
    }
  }

  return -1
}