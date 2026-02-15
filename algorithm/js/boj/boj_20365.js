const fs = require('fs')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
const cs = lines[1]
let reds=0; blues=0;
let prev='G'

for(let i=0; i<cs.length; i++){
  const c = cs[i]
  if(prev!==c){
    prev = c
    if(c=='B') blues++
    else if(c=='R') reds++
  }
}

console.log(Math.min(reds,blues)+1)