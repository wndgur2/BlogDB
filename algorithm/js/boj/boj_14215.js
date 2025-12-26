const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split(' ').map(Number)

for (let i = 0; i < inputs.length; i++) {
  const sum = inputs.reduce((p, c) => p + c)
  if (inputs[i] >= sum - inputs[i]) {
    inputs[i] = sum - inputs[i] - 1
  }
}
console.log(inputs.reduce((p, c) => p + c))
