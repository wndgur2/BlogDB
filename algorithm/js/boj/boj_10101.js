const fs = require('fs')

const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n').map(Number)
// const inputs = fs.readFileSync('inputs').toString().trim().split('\n').map(Number)

const sum = inputs.reduce((p, c) => p + c)

if (sum !== 180) console.log('Error')
else if (inputs[0] == inputs[1] && inputs[1] == inputs[2] && inputs[2] == inputs[0])
  console.log('Equilateral')
else if (inputs[0] == inputs[1] || inputs[1] == inputs[2] || inputs[2] == inputs[0])
  console.log('Isosceles')
else console.log('Scalene')
