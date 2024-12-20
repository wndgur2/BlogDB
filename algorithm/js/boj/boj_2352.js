let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');
let N = Number(inputs[0])
let numbers = inputs[1].split(' ').map(n=>Number(n))

// numbers LIS
let lengths = Array(N).fill(0)
let tempArr = []
let res = 0
// splice(i,0,v)
numbers.forEach((n)=>{
  let index = binarySearch(n)
  // console.log(n, index)
  tempArr.splice(index, 1, n)
  res = res<index+1?index+1:res
  // console.log(tempArr)
})

console.log(res)

function binarySearch(n){
  // n을 tempArr에 넣기
  let l = 0
  let r = tempArr.length-1
  let mid = l
  while(l+1<r){
    mid = Math.floor((l+r+1)/2)
  
    if(tempArr[mid]<n){ // go right
      l = mid
    } else{ // go left
      r = mid
    }
  }

  while(tempArr[mid]<n)mid++
  while(tempArr[mid-1]>n)mid--

  return mid
}