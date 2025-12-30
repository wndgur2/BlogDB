const fs = require('fs')
const inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const N = Number(inputs[0])
const logs = inputs.slice(1).map((log) => log.split(' '))

const workers = new Set()

for (let i = 0; i < N; i++) {
  if (logs[i][1] == 'leave') {
    if (workers.has(logs[i][0])) {
      workers.delete(logs[i][0])
    }
  } else {
    workers.add(logs[i][0])
  }
}

console.log(Array.from(workers).sort().reverse().join('\n'))
