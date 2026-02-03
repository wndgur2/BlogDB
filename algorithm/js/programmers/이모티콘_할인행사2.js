function solution(users, emoticons) {
  const answer = [0, 0]
  const UN = users.length
  const EN = emoticons.length

  const q = [[]]
  let qi = 0
  while(qi<q.length){
    const dcs = q[qi++]
    if(dcs.length == EN){
      simulate(dcs)
      continue
    }
    for(let dc=10; dc<=40; dc+=10){
      q.push([...dcs, dc])
    }
  }

  return answer 

  function simulate(dcs){
    let plus=0
    let profit=0
    for(let ui=0; ui<UN; ui++){
      const u = users[ui]
      let costToBuy = emoticons.reduce((p, c, i)=>{
        const cost = c*(100-dcs[i])/100
        return p+(dcs[i]>=u[0]?cost:0)
      }, 0)

      if(costToBuy>=u[1]) plus++
      else{
        profit += costToBuy
      }
    }
    if(plus>answer[0]){
      answer[0] = plus
      answer[1] = profit
    } else if(plus==answer[0] && profit>answer[1]){
      answer[1] = profit
    }
  }
}


const res = solution([[40, 2900], [23, 10000], [11, 5200], [5, 5900], [40, 3100], [27, 9200], [32, 6900]],[1300, 1500, 1600, 4900])
console.log(res)
// [1, 5400]