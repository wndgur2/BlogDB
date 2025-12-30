const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(inputs[0])
const numbers = inputs[1].split(' ').map(Number)
const M = Number(inputs[2])
const guesses = inputs[3].split(' ').map(Number)

const set = new Set()

for (let i = 0; i < N; i++) {
  set.add(numbers[i])
}

const res = []

for (let i = 0; i < M; i++) {
  if (set.has(guesses[i])) res.push(1)
  else res.push(0)
}

console.log(res.join(' '))
