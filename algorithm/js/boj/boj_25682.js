const fs = require('fs')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const [N, M, K] = lines[0].split(' ').map(Number)

const board = lines.slice(1)
// 0,0 ~ i,j까지를 체스판으로 만드는 비용 각각을 구해놓기 (2000x2000)
const costsW = Array(N).fill(0).map(v=>Array(M).fill(0))

for(let i=0; i<N; i++){
  for(let j=0; j<M; j++){
    // 0,0이 W인 체스판을 만들 때
    const costW = (i>0?costsW[i-1][j]:0) + (j>0?costsW[i][j-1]:0) - ((i>0&&j>0)?costsW[i-1][j-1]:0)

    if((i+j)%2==0){ // should be white
      if(board[i][j]=='B') costsW[i][j] = costW+1
      else costsW[i][j] = costW
    } else{ // should be black
      if(board[i][j]=='W') costsW[i][j] = costW+1
      else costsW[i][j] = costW
    }
  }
}


let minCost = 4000000
for(let i=0; i<N-K+1; i++){
  for(let j=0; j<M-K+1; j++){
    const costW = Math.min(costsW[i+K-1][j+K-1] - ((j>0?costsW[i+K-1][j-1]:0) + (i>0?costsW[i-1][j+K-1]:0)-((i>0&&j>0)?costsW[i-1][j-1]:0)))
    minCost = Math.min(minCost, costW, K*K-costW)
  }
}

console.log(minCost)