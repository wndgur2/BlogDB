let fs = require('fs')
let inputs = fs.readFileSync('/dev/stdin').toString().split('\n')
let N = Number(inputs[0])
let rooms = inputs[1].split(' ').map(v=>BigInt(v))

let answer = BigInt(rooms.reduce((pre,cur)=>BigInt(pre+cur), BigInt(0)))

if(answer==0){
  answer = Math.floor(N/2)
} else{
  let firstEmptyIndex;
  for(let i=0; i<N; i++){
    if(rooms[i]>0) {
      firstEmptyIndex=i
      break
    }
  }
  let length=0
  for(let i=firstEmptyIndex; i<firstEmptyIndex+N; i++){
    const index = i%N
    if(rooms[index]==0) length++
    else{
      answer += BigInt(Math.ceil(length/2))
      length=0
    }
  }
  answer += BigInt(Math.ceil(length/2))
}

console.log(answer.toString())