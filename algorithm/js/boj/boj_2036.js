const fs = require('fs')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const numbers = lines.splice(1).map(Number)

const positives = numbers.filter(v=>v>0).sort((a,b)=>a-b)
const negatives = numbers.filter(v=>v<=0).sort((a,b)=>b-a)

let score = 0n

while(positives.length>=2){
  const a = positives.pop()
  const b = positives.pop()
  if(b>1 && a>1){
    score += BigInt(a*b)
  } else{
    score += BigInt(a + b)
  }
}

while(positives.length>0){
  score += BigInt(positives.pop())
}

while(negatives.length>=2){
  const a = negatives.pop()
  const b = negatives.pop()
  score += BigInt(a*b)
}

while(negatives.length>0){
  score += BigInt(negatives.pop())
}

console.log(score.toString())