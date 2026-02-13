const fs = require('fs')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const N = Number(lines[0])
const K = Number(lines[2])
const amounts = lines[1].split(' ').map(Number)
const sum = amounts.reduce((p,c)=>p+c, 0)

// K개를 뽑았는데 모두 같은 색일 확률
// = 각 색을 모두 뽑을 확률을 더한 것

let answer = 0

for(let c=0; c<N; c++){
  const n = amounts[c]
  if(n<K) continue
  let perc = 1
  let cs = sum
  let cn = n
  let ck = K
  while(ck>0){
    perc *= cn/cs
    cn--;ck--;cs--;
  }
  answer += perc
}

console.log(answer.toFixed(12))
