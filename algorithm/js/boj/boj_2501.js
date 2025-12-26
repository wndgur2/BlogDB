let fs = require('fs')
let [N, K] = fs.readFileSync('inputs').toString().trim().split(' ')
// let [N, K] = fs.readFileSync('/dev/stdin').toString().trim().split(' ')
let count = 0

for (let i = 1; i <= N; i++) {
  if (N % i == 0) {
    if (++count == K) {
      console.log(i)
      break
    }
  }
}
if (count < K) console.log(0)
