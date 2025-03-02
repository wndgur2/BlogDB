// 틀렸습니다.

let fs = require('fs')
let inputs = fs.readFileSync('dev/stdin').toString().trim().split('\n');

let N = parseInt(inputs[0])
let HONEYS = inputs[1].split(' ').map(v=>parseInt(v))
let sums = Array(N).fill(0)

// 누적합 구하기
HONEYS.reduce((prev, cur, i)=>{
  sums[i] = prev+HONEYS[i]
  return sums[i]
},0)

let answer = 0

// 벌통 장소 정하기
for(let i=0; i<N; i++){
  // 꿀벌 배치하기
  getBeeLocation(i).forEach(([a, b])=>{
    // a>b
    let temp_honey = 0
    let honey_a = 0
    let honey_b = 0

    if(a<i){
      // a
      honey_a += sums[i]-sums[a]

      // b
      if(b>a && b<i){
        honey_a -= HONEYS[b] // a가 b 위를 지나감
        honey_b += sums[i] - sums[b]
      } else if(b>i){
        honey_b += sums[b-1] - sums[i] + HONEYS[i]
      } else if(b<a){
        honey_b += sums[i] - sums[b] - HONEYS[a]
      }
    } else{
      // a
      honey_a += sums[a-1]-sums[i]+HONEYS[i]

      // b
      if(b>i && b<a){
        honey_a -= HONEYS[b] // a가 b 위를 지나감
        honey_b += sums[b-1] - sums[i] + HONEYS[i]
      } else if(b<i){
        honey_b += sums[i] - sums[b]
      } else if(b>a){
        honey_b -= HONEYS[a] // b가 a 위를 지나감
        honey_b += sums[b-1] - sums[i] + HONEYS[i]
      }
    }
    temp_honey = honey_a + honey_b
    if(answer < temp_honey) {
      answer = temp_honey
      // console.log(a, b, i)
      // console.log(honey_a)
      // console.log(honey_b) 
    }
  })
}

function getBeeLocation(index){
  // a < b
  if(index==0)
    return [[N-2, N-1]]
  if(index==1)
    return [[N-2, N-1], [0, N-1]]
  if(index==N-1)
    return [[0, 1]]
  if(index==N-2)
    return [[0, 1], [0, N-1]]

  const locations = []
  locations.push([0, 1]) // st,st
  locations.push([0, N-1]) // st,end
  locations.push([N-2, N-1]) // end,end

  return locations
}

function getDistance(a, b){
  if(a>b) return a-b
  return b-a
}

console.log(answer)