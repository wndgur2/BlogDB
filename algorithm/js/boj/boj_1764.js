const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const unheard = new Set()
const unseen = new Set()

const [N, M] = inputs[0].split(' ').map(Number)

for (let i = 1; i < inputs.length; i++) {
  if (i > N) {
    unseen.add(inputs[i])
  } else {
    unheard.add(inputs[i])
  }
}

const unseenArr = Array.from(unseen)
const intersection = new Set()
for (let i = 0; i < unseenArr.length; i++) {
  if (unheard.has(unseenArr[i])) intersection.add(unseenArr[i])
}

const res = Array.from(intersection).sort()
console.log(res.length)
console.log(res.join('\n'))
