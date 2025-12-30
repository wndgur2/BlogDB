const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(inputs[0])
const times = inputs.slice(1).map((v) => {
  const [h, m] = v.split(':').map(Number)
  return h * 60 + m
})
times.sort((a, b) => a - b)

let last = times[0] + 10
let res = 1
let people = 1

for (let i = 1; i < N; i++) {
  if (times[i] <= last + 10 && people++ < 3) continue
  else {
    res++
    last = times[i] + 10
    people = 1
  }
}

console.log(res)
