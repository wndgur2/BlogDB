let fs = require('fs');
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
// let lines = fs.readFileSync('inputs').toString().trim().split('\n');
const N = lines.shift()

let inputs = lines.map(ab=>ab.split(' ').map(Number))
const dp = Array.from({length:N+1}).map(v=>new Map())
dp[0].set(`${12}.${120}`, 0)

for(let v of dp[0]){
  doNothing()
  // console.log(v)
}

const res = new Map()
// const res = []

inputs.forEach(([a, b], i)=>{
  const index = pow(a, b)
  if(index==0) {
    res.set(i, 10)
    // res.push(10)
  }
  else {
    // res.push(index)
    res.set(i, index)
  }
})

res.forEach(v=>console.log(v))

function pow(a, b){
  const oldA = a
  a %=10
  for(let i=0; i<b-1; i++){
    a *= oldA
    a %= 10
  }

  return a
}

function doNothing(){
  //
}