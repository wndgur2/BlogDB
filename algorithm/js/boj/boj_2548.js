let fs = require('fs')
let inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let numbers = inputs[1].split(' ').map(_=>Number(_))
numbers = numbers.sort((a, b)=> a-b)

let l = numbers[0]
let r = numbers[numbers.length-1]
let mid, lMid, rMid, leftSumDif, rightSumDif
let res = l
while(l+1<r){
  mid = Math.floor((l+r+1)/2)
  lMid = Math.floor((l+mid+1)/2)
  rMid = Math.floor((mid+r+1)/2)

  // sumDif = calcSumDif(mid)
  leftSumDif = calcSumDif(lMid)
  rightSumDif = calcSumDif(rMid)

  // console.log(lMid, mid, rMid)
  // console.log(l, mid, r)

  if(leftSumDif<=rightSumDif){ // go left
    r = mid
    res = lMid
  } else{ // go right
    l = mid
    res = rMid
  }
}

while(calcSumDif(res+1) < calcSumDif(res)) res += 1
while(calcSumDif(res-1) <= calcSumDif(res)) res -= 1

console.log(res)

function calcSumDif(repNum){
  let res = 0
  numbers.forEach(n=>res+=Math.abs(repNum - n))
  return res
}