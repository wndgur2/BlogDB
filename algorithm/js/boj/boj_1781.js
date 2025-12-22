// 메모리초과

let fs = require('fs')
let inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

let N = parseInt(inputs[0])
let PS = inputs.slice(1,).map((v)=>v.split(' ').map(r=>parseInt(r)))

// 1. 가장 긴박한 것 부터 푼다.
// -> 비싼게 한 시점에 몰려있으면 못 풀 수 있다.
// -> 전처리를 통해 중복을 푼다.

// 전처리
// 중복되어있는 문제의 점수가 앞 시간의 점수보다 높을 경우 앞 시간 문제를 대체함.
// 앞 시간은 이를 반복

let probs_time = new Map() // 각 데드라인의 문제들

PS.forEach(([a,b])=>{
  if(!probs_time.has(a)) probs_time.set(a, [])
  probs_time.get(a).push(b)
})

// console.log(probs_time)

for(let i=N; i>0; i--){
  if(!probs_time.has(i)) continue
  const v = probs_time.get(i)
  const k = i
  if(v.length<=1) continue
  v.sort((a, b)=>b-a)
  while(v.length>1) {
    if(!probs_time.has(k-1)) probs_time.set(k-1,[])
    probs_time.get(k-1).push(v.pop())
  }
}

// probs_time[0].sort((a, b)=>b-a)
probs_time.set(0, [])
// console.log(probs_time)

answer = 0
probs_time.forEach((v)=>{
  if(v.length>0) answer+=parseInt(v[0])
})

console.log(answer)

const used = process.memoryUsage().heapUsed / 1024 / 1024;
console.log(`약 ${Math.round(used * 100) / 100} MB의 메모리를 사용중입니다.`);