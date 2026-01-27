const fs = require('fs')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const [N, M] = lines.shift().split(' ').map(Number)
const numbers = lines[0].split(' ').map(Number)

// 각 나머지의 개수를 세어서 조합하기
const mc = Array(M).fill(0)
mc[0]=1

let sum = 0
for(let i=0; i<N; i++) {
  sum += numbers[i]
  mc[sum%M]++
}

let res = 0
for(let i=0; i<M; i++){
  res += (mc[i]*(mc[i]-1))/2
}


console.log(res)