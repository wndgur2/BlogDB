const fs = require('fs')

const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const [N, S] = lines.shift().split(' ').map(Number)
const numbers = lines.shift().split(' ').map(Number)
numbers.push(0)

let p1=0, p2=0;
let sum = numbers[0]
let ml = N+1
while(p1<N && p2<N){
  const l = p2-p1+1
  if(sum>=S && l<ml) ml = l
  if(sum<S) sum+=numbers[++p2]
  else sum-=numbers[p1++]
}
console.log(ml<=N?ml:0)