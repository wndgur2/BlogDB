const fs = require('fs')
const [A, B] = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(Number)
const big = A > B ? A : B
const small = A > B ? B : A

let n = big
for (; n % small != 0; n += big) {}

console.log(n)
