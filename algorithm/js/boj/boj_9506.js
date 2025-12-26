let fs = require('fs')
// let numbers = fs.readFileSync('inputs').toString().trim().split('\n').map(Number)
let numbers = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(Number)
let L = numbers.length

for (let i = 0; i < L; i++) {
  const N = numbers[i]
  if (N == -1) break

  let sum = 0
  let divisors = []
  for (let j = 1; j < N; j++) {
    if (N % j == 0) {
      sum += j
      divisors.push(j)
    }
  }
  if (sum === N) {
    console.log(`${N} = ${divisors.join(' + ')}`)
  } else {
    console.log(`${N} is NOT perfect.`)
  }
}
