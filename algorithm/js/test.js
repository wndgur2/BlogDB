var fs = require('fs')
var inputs = fs.readFileSync('./dev/stdin').toString().split(' ')
var number = Number(inputs[0])
var base = Number(inputs[1])
var answer = []
var tmp = number

while (tmp !== 0) {
  answer.push(tmp % base)
  tmp = Math.floor(tmp / base)
}
answer = answer.reverse()
for (var i = 0; i < answer.length; i++) {
  if (answer[i] >= 10 && answer[i] <= 35) {
    answer[i] = String.fromCharCode(answer[i] + 55)
  }
}
console.log(answer.join('').trim())
