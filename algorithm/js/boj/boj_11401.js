// 답 아님 XXX

const MOD = 13

const fs = require('fs')
const [N, K] = fs.readFileSync('inputs').toString().trim().split(' ').map(Number)
// const [N, K] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(Number)

const factorials = Array(N+1).fill(0)
factorials[0] = 1
for(let i=1; i<N+1; i++){
  factorials[i] = factorials[i-1]*i % MOD
}
console.log(combination(N, K))

function combination(n, k){
  return (factorials[n]/(factorials[k]*factorials[n-k]))
}

console.log(factorials)