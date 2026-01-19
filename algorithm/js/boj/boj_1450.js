const fs = require('fs')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const [N, S] = lines.shift().split(' ').map(Number)
const ws = lines[0].split(' ').map(Number)

ws.sort((a,b)=>a-b)

const p1 = ws.splice(0, Number.parseInt(ws.length/2))
const p2 = ws

const a1 = getSums(p1)
const a2 = getSums(p2)

a1.sort((a,b)=>a-b)
a2.sort((a,b)=>a-b)

// console.log(a1, a2)

let count = 0
let j = 0
for(let i=a1.length-1; i>=0; i--){
  count += j
  while(j<a2.length && a1[i] + a2[j] <= S) {
    count += 1
    j++
  }
}

console.log(count)

function getSums(arr){
  const res = [0]
  dfs(res, 0, 0, arr)
  return res
}

function dfs(res, index, sum, arr){
  if(index>=arr.length) return
  const newSum = sum+arr[index]
  res.push(newSum)
  dfs(res, index+1, sum, arr)
  dfs(res, index+1, newSum, arr)
}