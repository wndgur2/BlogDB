let fs = require('fs');
// let lines = fs.readFileSync('inputs').toString().trim().split('\n');
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = Number(lines[0])
//current state
let cs = lines[1].split(' ').map(v=>v=='1')
// goal state
let gs = lines[2].split(' ').map(v=>v=='1')

let currentChecked = 0
let currentUnChecked = 0

let goalChecked = 0
let goalUnChecked = 0

let toCheck = 0
let toUnCheck = 0
let toStay = 0

for(let i=0; i<N; i++){
  if(!cs[i]){
    currentUnChecked++
    if(gs[i]) {
      toCheck++
      goalChecked++
    }
    else goalUnChecked++
  } else{
    currentChecked++
    if(!gs[i]){
      toUnCheck++
      goalUnChecked++
    } else goalChecked++
  }
}

toStay = N - toCheck - toUnCheck

// console.log(cs, gs)
// console.log(toCheck, toUnCheck, toStay)
// console.log(currentChecked, currentUnChecked)

// 전체 체크 하는 경우
let actions1 = currentChecked===N?0:1
actions1 += goalUnChecked

// 전체 체크 해제하는 경우
let actions2 = currentUnChecked===N?0:currentChecked===N?1:2
actions2 += goalChecked

// 부분 체크로 해결하는 경우
let actions3 = toCheck + toUnCheck

console.log(min([actions1, actions2, actions3]))

function min(arr){
  let minVal = 987654321
  arr.forEach(v=>v<minVal?minVal=v:0)
  return minVal
}
