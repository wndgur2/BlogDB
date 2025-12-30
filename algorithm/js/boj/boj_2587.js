const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(Number)
inputs.sort((a, b) => a - b)
const sum = inputs.reduce((p, c) => p + c)
const avg = sum / 5
const mid = inputs[2]
console.log(avg)
console.log(mid)
