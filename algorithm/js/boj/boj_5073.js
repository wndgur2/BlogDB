/**
Equilateral :  세 변의 길이가 모두 같은 경우
Isosceles : 두 변의 길이만 같은 경우
Scalene : 세 변의 길이가 모두 다른 경우
 */
const fs = require('fs')
const inputs = fs
  .readFileSync('/dev/stdin')
  .toString()
  .trim()
  .split('\n')
  .map((ls) => ls.split(' ').map(Number))
  .slice(0, -1)

for (let i = 0; i < inputs.length; i++) {
  if (
    inputs[i][0] >= inputs[i][1] + inputs[i][2] ||
    inputs[i][1] >= inputs[i][0] + inputs[i][2] ||
    inputs[i][2] >= inputs[i][1] + inputs[i][0]
  ) {
    console.log('Invalid')
  } else if (
    inputs[i][0] == inputs[i][1] &&
    inputs[i][1] == inputs[i][2] &&
    inputs[i][2] == inputs[i][0]
  ) {
    console.log('Equilateral')
  } else if (
    inputs[i][0] == inputs[i][1] ||
    inputs[i][1] == inputs[i][2] ||
    inputs[i][2] == inputs[i][0]
  ) {
    console.log('Isosceles')
  } else {
    console.log('Scalene')
  }
}
