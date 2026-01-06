const fs = require('fs')
const [A, B] = fs.readFileSync('/dev/stdin').toString().trim().split('\n')

const dp = new Array(A.length + 1).fill(0).map((v) => new Array(B.length + 1).fill(0))

for (let i = 1; i < A.length + 1; i++) {
  for (let j = 1; j < B.length + 1; j++) {
    if (A[i - 1] === B[j - 1]) {
      dp[i][j] = dp[i - 1][j - 1] + 1
    } else {
      dp[i][j] = dp[i - 1][j] > dp[i][j - 1] ? dp[i - 1][j] : dp[i][j - 1]
    }
  }
}

let i = A.length
let j = B.length
console.log(dp[i][j])

const res = []
while (i > 0 && j > 0) {
  if (A[i - 1] == B[j - 1]) {
    res.push(A[i - 1])
    i--
    j--
  } else if (dp[i - 1][j] > dp[i][j - 1]) {
    i--
  } else {
    j--
  }
}

console.log(res.reverse().join(''))
