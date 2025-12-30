const fs = require('fs')

const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(inputs[0])
const lines = inputs.slice(1).map((line) => line.split(' ').map(Number))

lines.sort((a, b) => a[0] - b[0])

let res = 0
let lastX = -1000000000
for (let i = 0; i < N; i++) {
  if (lines[i][0] > lastX) {
    res += lines[i][1] - lines[i][0]
  } else {
    if (lines[i][1] > lastX) {
      res += lines[i][1] - lastX
    }
  }
  if (lines[i][1] > lastX) lastX = lines[i][1]
}

console.log(res)
