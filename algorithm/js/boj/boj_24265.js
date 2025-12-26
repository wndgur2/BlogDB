const fs = require('fs')
const input = fs.readFileSync('/dev/stdin').toString().trim().split().map(Number)

let proc = 0
while (input[0]--) proc += input[0]
console.log(proc)
console.log(2)
