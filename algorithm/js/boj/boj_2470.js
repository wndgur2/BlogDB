const fs = require('fs')
const lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n')
// const lines = fs.readFileSync('inputs').toString().trim().split('\n')
const numbers = lines[1].split(' ').map(Number)

numbers.sort((a, b) => a-b)

let l=0, r=numbers.length-1
let mix = numbers[l] + numbers[r]
let minMix = mix
let minCombination = [numbers[l], numbers[r]]
while(l+1<r){
  if(mix>0)r--
  else l++
  mix = numbers[l] + numbers[r]
  if(Math.abs(mix)<Math.abs(minMix)) {minMix = mix
    minCombination = [numbers[l], numbers[r]]
  }
}
console.log(minCombination.join(' '))