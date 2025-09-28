let fs = require('fs');
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let inputs = lines.map(line=>line.split(' ').map(Number))
const [N, M] = inputs.shift()

const numbers = inputs.splice(0, N)
const queries = inputs

const ySum = Array.from({length:N}).map(_=>Array.from({length:N}).fill(0))
const xySum = Array.from({length:N}).map(_=>Array.from({length:N}).fill(0))


let tempSum=0

for(let x=0; x<N; x++){
  tempSum=0
  for(let y=0; y<N; y++){
    tempSum+=numbers[y][x]
    ySum[y][x] = tempSum
  }
}

for(let y=0; y<N; y++){
  tempSum=0
  for(let x=0; x<N; x++){
    tempSum+=ySum[y][x]
    xySum[y][x] = tempSum
  }
}

const ans = []

queries.forEach(([y1, x1, y2, x2])=>{
  x1-=1
  y1-=1
  x2-=1
  y2-=1
  const topSubstract = y1-1>=0 ? xySum[y1-1][x2] : 0
  const leftSubstract = x1-1>=0 ? xySum[y2][x1-1] : 0
  const commonAddition = ((y1-1>=0) && (x1-1>=0)) ? xySum[y1-1][x1-1] : 0
  ans.push(xySum[y2][x2] - leftSubstract - topSubstract + commonAddition)
})

console.log(ans.join('\n'));