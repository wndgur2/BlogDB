const MIN_VALUE = -2100000000

const fs = require('fs')
// const inputs = fs.readFileSync('inputs').toString().trim().split('\n')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const [N, M] = inputs.shift().split(' ').map(Number)
const board = inputs.map(v=>v.split(' ').map(Number))
const dp = Array(N).fill(0).map(v=>Array(M).fill(MIN_VALUE))

dp[0][0] = board[0][0]

for(let y=0; y<N; y++){
  const rdp = [...dp[y]]
  const ldp = [...dp[y]]
  // move right
  for(let x=1; x<M; x++){
    rdp[x] = Math.max(rdp[x], rdp[x-1] + board[y][x])
  }
  // move left
  for(let x=M-2; x>=0; x--){
    ldp[x] = Math.max(ldp[x], ldp[x+1] + board[y][x])
  }
  
  for(let x=0; x<M; x++){
    dp[y][x] = Math.max(ldp[x], rdp[x])
    if(y<N-1) dp[y+1][x] = dp[y][x] + board[y+1][x]
  }
}
// console.log(dp)
console.log(dp[N-1][M-1])