const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const T = Number(inputs[0])
let t = 0

while (t++ < T) {
  const N = Number(inputs[t * 2 - 1])
  const numbers = inputs[t * 2].split(' ').map(Number)
  const dp = Array(N)
    .fill(0)
    .map((_) => Array(N).fill(-1))

  const res = solve(0, N - 1, false)
  console.log(res)

  function solve(l, r, even) {
    if (l > r) return 0
    if (dp[l][r] != -1) return dp[l][r]
    if (even) {
      dp[l][r] = Math.min(solve(l + 1, r, !even), solve(l, r - 1, !even))
    } else {
      dp[l][r] = Math.max(solve(l + 1, r, !even) + numbers[l], solve(l, r - 1, !even) + numbers[r])
    }
    return dp[l][r]
  }
}
