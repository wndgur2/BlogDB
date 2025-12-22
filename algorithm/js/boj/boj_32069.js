let fs = require('fs')
let inputs = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
const [L, N, K] = inputs[0].split(' ').map(v=>Number(v))
const lights = inputs[1].split(' ').map(v=>Number(v))
const darkness = new Array(K+1)
console.log([L, N, K], lights)
console.log(darkness)

lights.forEach((light_location, i)=>{
  // 이전 
})