function solution(maze) {
  var answer = 0
  let H = maze.length
  let W = maze[0].length
  let red_start, blue_start, red_end, blue_end
  const ds = [
    [1, 0],
    [0, 1],
    [-1, 0],
    [0, -1],
  ]

  maze.forEach((row, y) => {
    row.forEach((cell, x) => {
      if (cell == 1) red_start = [y, x]
      else if (cell == 2) blue_start = [y, x]
      else if (cell == 3) red_end = [y, x]
      else if (cell == 4) blue_end = [y, x]
    })
  })

  let nodes = [
    {
      red: red_start,
      blue: blue_start,
      depth: 0,
      red_visited: JSON.stringify(
        Array(H)
          .fill(0)
          .map((v) => Array(W).fill(false))
      ),
      blue_visited: JSON.stringify(
        Array(H)
          .fill(0)
          .map((v) => Array(W).fill(false))
      ),
    },
  ]

  while (nodes.length > 0) {
    let node = nodes.shift()
    let { red, blue, depth, red_visited, blue_visited } = node
    red_visited = JSON.parse(red_visited)
    blue_visited = JSON.parse(blue_visited)
    red_visited[red[0]][red[1]] = true
    blue_visited[blue[0]][blue[1]] = true

    if (
      red[0] == red_end[0] &&
      red[1] == red_end[1] &&
      blue[0] == blue_end[0] &&
      blue[1] == blue_end[1]
    ) {
      return depth
    }
    // console.log(node)
    ds.forEach(([red_dy, red_dx]) => {
      // red
      let red_ny = red[0] + red_dy
      let red_nx = red[1] + red_dx

      if (red[0] == red_end[0] && red[1] == red_end[1]) {
        red_ny = red[0]
        red_nx = red[1]
      } else {
        if (red_ny < 0 || red_nx < 0 || red_ny >= H || red_nx >= W) return
        if (maze[red_ny][red_nx] == 5) return
        if (red_visited[red_ny][red_nx]) return
      }

      ds.forEach(([blue_dy, blue_dx]) => {
        // blue
        let blue_ny = blue[0] + blue_dy
        let blue_nx = blue[1] + blue_dx

        if (blue[0] == blue_end[0] && blue[1] == blue_end[1]) {
          blue_ny = blue[0]
          blue_nx = blue[1]
        } else {
          if (blue_ny < 0 || blue_nx < 0 || blue_ny >= H || blue_nx >= W) return
          if (maze[blue_ny][blue_nx] == 5) return
          if (blue_visited[blue_ny][blue_nx]) return
        }

        if (red_ny == blue[0] && red_nx == blue[1] && blue_ny == red[0] && blue_nx == red[1]) return
        if (red_ny == blue_ny && red_nx == blue_nx) return

        nodes.push({
          red: [red_ny, red_nx],
          blue: [blue_ny, blue_nx],
          depth: depth + 1,
          red_visited: JSON.stringify(red_visited),
          blue_visited: JSON.stringify(blue_visited),
        })
      })
    })
  }

  return answer
}
