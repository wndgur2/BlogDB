let fs = require('fs');
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
// let lines = fs.readFileSync('inputs').toString().trim().split('\n');
let N = Number(lines.shift())
let heights = lines[0].trim().split(' ').map(Number)
let oldTH = BigInt(0)
let minimum = Math.pow(10, 9)

heights.forEach((h)=>{
  oldTH += BigInt(h)
  if(h<minimum) minimum = h
})

console.log((oldTH - BigInt(N*minimum)).toString())
