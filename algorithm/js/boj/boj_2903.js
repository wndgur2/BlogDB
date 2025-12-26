const fs = require('fs')
const n = Number(fs.readFileSync('/dev/stdin').toString().trim())
// const n = Number(fs.readFileSync('inputs').toString().trim())

/**

1 - 1개 -> 4
2 - 4개 -> 9
3 - 16개 -> 25
4 - 64개 -> 80
49 + 15
64 + 16 = 80개 ?
*/

const boxes = Math.pow(4, n)
const w = Math.sqrt(boxes)

console.log(boxes + w * 2 + 1)
