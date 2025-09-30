let fs = require('fs');
// let lines = fs.readFileSync('inputs').toString().trim().split('\n');
let lines = fs.readFileSync(0, "utf-8").toString().trim().split('\n');

let N = Number(lines.shift())
let [A, B, K] = lines[0].trim().split(' ').map(Number)

const dp = Array.from({length:N+1}).map(v=>new Map())

dp[0].set(A*1000 + B, 0)

let p=0

// 500
for(let day=1; day<N+1; day++){
  for([key, bs] of dp[day-1]){
    const [a, b] = [Number.parseInt(Number(key)/1000), Number(key)%1000]
    if(a<0 || b<0) return
    //A에 더하는 경우
    if(a<b){
      for(let k=K; k<=Math.min(A+B-a, b); k++){
        const newKey = (a+k)*1000 + b-k
        const newScore = bs+(a+k)*(b-k)
        
        if(!dp[day].has(newKey) || newScore > dp[day].get(newKey)){
          dp[day].set(newKey, newScore)
        }
        p++
      } 
    } else {
      //B에 더하는 경우
      for(let k=K; k<=Math.min(A+B-b, a); k++){
        const newKey = (a-k)*1000 + b+k
        const newScore = bs+(b+k)*(a-k)
        
        if(!dp[day].has(newKey) || newScore > dp[day].get(newKey)){
          dp[day].set(newKey, newScore)
        }
        p++
      }
    }
  }
}

let res = 0
// console.log(dp[N].size)
// console.log('p', p)
for([k, v] of dp[N])
  res=v>res?v:res
console.log(res)