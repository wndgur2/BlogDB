// bfs dp
function solution(board) {
  const N = board.length
  const costs = Array.from({ length: N }, (v) =>
    Array.from({ length: N }, (v) => Array(4).fill(Infinity))
  )

  const q = [
    {
      px: 0,
      y: 0,
      x: 0,
      cost: 0,
    },
    {
      px: -1,
      y: 0,
      x: 0,
      cost: 0,
    },
  ]

  const ds = [
    [0, 1],
    [1, 0],
    [0, -1],
    [-1, 0],
  ]

  const isValid = (y, x) => y >= 0 && x >= 0 && y < N && x < N && board[y][x] == 0

  while (q.length > 0) {
    let { px, y, x, cost } = q.shift()
    const fv = px == x
    ds.forEach(([dy, dx], di) => {
      const ny = y + dy
      const nx = x + dx
      if (!isValid(ny, nx)) return

      const tv = x == nx
      const isCorner = fv != tv
      const newCost = isCorner ? cost + 600 : cost + 100

      if (costs[ny][nx][di] > newCost) {
        costs[ny][nx][di] = newCost
        q.push({
          px: x,
          y: ny,
          x: nx,
          cost: newCost,
        })
      }
    })
  }

  return Math.min(...costs[N - 1][N - 1])
}
