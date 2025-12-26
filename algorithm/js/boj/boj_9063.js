const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const inputs = fs.readFileSync('inputs').toString().trim().split('\n')
const N = inputs[0]
const dots = inputs.slice(1).map((v) => v.split(' ').map(Number))

let minX = 10000,
  minY = 10000,
  maxX = -10000,
  maxY = -10000

for (let i = 0; i < dots.length; i++) {
  if (dots[i][0] < minX) minX = dots[i][0]
  if (dots[i][0] > maxX) maxX = dots[i][0]
  if (dots[i][1] < minY) minY = dots[i][1]
  if (dots[i][1] > maxY) maxY = dots[i][1]
}

console.log((maxY - minY) * (maxX - minX))
