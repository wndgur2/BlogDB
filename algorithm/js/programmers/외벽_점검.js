// 1명부터 dist.length명까지 완탐
// weak의 id = weak[i]%n

function solution(n, weak, dist) {
  const isCovered = new Map()
  const weakN = weak.length
  weak.forEach((v) => (isCovered[v] = false))
  weak.forEach((v) => weak.push(v + n))

  let answer = -1

  dist.sort((a, b) => b - a)
  dfs(0, 0)

  return answer

  function dfs(depth, coveredN) {
    if (answer != -1 && depth >= answer) return
    if (coveredN == weakN) return (answer = depth)
    if (depth == dist.length) return

    const length = dist[depth]
    for (let st = 0; st < weakN; st++) {
      if (getCovered(st)) continue
      cover(st)

      // covers st~st+l
      let l = 0
      let end = st + 1
      let covered = 1
      while (end < weak.length && !getCovered(end) && l + weak[end] - weak[end - 1] <= length) {
        l += weak[end] - weak[end - 1]
        cover(end)
        covered++
        end++
      }
      dfs(depth + 1, coveredN + covered)
      for (let i = st; i < end; i++) {
        uncover(i)
      }
    }
  }

  function getCovered(i) {
    const location = weak[i]
    return isCovered[location % n]
  }

  function cover(i) {
    const location = weak[i]
    isCovered[location % n] = true
  }

  function uncover(i) {
    const location = weak[i]
    isCovered[location % n] = false
  }
}
