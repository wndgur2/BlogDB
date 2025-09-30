let fs = require('fs');
// let lines = fs.readFileSync('inputs').toString().trim().split('\n');
let lines = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
let N = Number(lines.shift())
let levelDif = 1
const stairs = lines.map(line=>
  line.trim().split(' ').map(Number))

stairs.forEach(([ent, exit]) => {
  let oldDif = levelDif
  if(ent<0 && exit>0){
    levelDif += exit - ent - 1
  } else if(ent>0 && exit<0){
    levelDif += exit - ent + 1
  } else{
    levelDif += exit - ent
  }
  if(levelDif<=0 && oldDif>0) levelDif--
  else if(levelDif>=0 && oldDif<0) levelDif++
})

console.log(levelDif)