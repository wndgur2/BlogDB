// B진법 수 N을 10진법으로 출력하기

const fs = require('fs')
// const [N, B] = fs.readFileSync('inputs').toString().trim().split(' ')
const [N, B] = fs.readFileSync('/dev/stdin').toString().trim().split(' ')

let res = 0

Array.from(N)
  .reverse()
  .forEach((n, i) => {
    const number = toHex(n)
    res += number * Math.pow(B, i)
  })

console.log(res)

function toHex(char) {
  const charCode = char.charCodeAt(0)
  if (charCode < 'A'.charCodeAt(0)) {
    return charCode - '0'.charCodeAt(0)
  } else {
    return charCode - 'A'.charCodeAt(0) + 10
  }
}
