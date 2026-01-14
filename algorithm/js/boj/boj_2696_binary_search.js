const fs = require('fs')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
let lp = 0
const T = Number(lines[lp++])

const res = []

for (let t = 0; t < T; t++) {
  const N = Number(lines[lp++])
  let numbers = lines
    .slice(lp, lp + Number.parseInt(N / 10) + 1)
    .map((v) => v.split(' ').map(Number))
    .reduce((p, c) => p.concat(c), [])

  lp += Number.parseInt(N / 10) + 1
  res.push(Number.parseInt((N + 1) / 2))

  const tres = []
  let tarr = []

  for (let i = 0; i < N; i++) {
    const ni = bs(tarr, numbers[i])
    tarr = tarr.slice(0, ni).concat([numbers[i]]).concat(tarr.slice(ni))
    // console.log(numbers[i], 'inserting at', ni)
    // console.log(tarr)

    if (i % 2 == 0) {
      const mid = tarr[Number.parseInt((tarr.length + 1) / 2) - 1]
      tres.push(mid)
    }
  }
  while (tres.length > 0) {
    res.push(tres.splice(0, 10).join(' '))
  }
}

console.log(res.join('\n'))

function bs(array, n) {
  // search closest (be) index to insert n
  let l = 0,
    r = array.length - 1,
    mid
  while (l < r) {
    mid = Number.parseInt((l + r) / 2)
    if (array[mid] < n) {
      l = mid + 1
    } else if (array[mid] === n) {
      break
    } else {
      r = mid
    }
  }
  while (l > 0 && array[l] > n) l--
  while (l < array.length && array[l] < n) l++
  return l
}
