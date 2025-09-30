let fs = require('fs');
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = Number(lines[0])
let steps = Number(lines[1])

let score = N * 10
if(N>=3) score+=20
if(N===5) score +=50
if(steps>1000) score -=15
if(score<0) score = 0

console.log(score)