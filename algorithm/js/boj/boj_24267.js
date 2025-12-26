const fs = require('fs')
// const input = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(Number)
const input = fs.readFileSync('inputs').toString().trim().split(' ').map(Number)

/*
MenOfPassion(A[], n) {
    sum <- 0;
    for i <- 1 to n - 2
        for j <- i + 1 to n - 1
            for k <- j + 1 to n
                sum <- sum + A[i] × A[j] × A[k]; # 코드1
    return sum;
}
*/

let n = BigInt(input[0])
let proc = 0n
let sum = 0n

for (let i = 1n; i < n - 1n; i++) {
  sum += i
  proc += sum
}

console.log(proc.toString())
console.log(3)
