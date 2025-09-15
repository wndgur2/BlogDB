function solution(dice) {
    var answer = [];
    let maxWins = 0
    const maxCaseN = Math.pow(6, dice.length)
    
    pickDices(pick, [], -1)
    
    // 252
    function pickDices(callback, currentDices, flag){
        if(currentDices.length == dice.length/2){
            return callback(currentDices)
        }
        for(let i=flag+1; i<dice.length; i++){
            pickDices(callback, [...currentDices, i], i)
        }
    }
    
    function pick(dicesA){
        let winCount = 0
        let loseCount = 0
        
        // A has dicesA.
        // B has rest of it.
        const dicesB = []
        for(let i=0; i<dice.length; i++){
            if(dicesA.indexOf(i)!=-1) {
                continue
            }
            dicesB.push(i)
        }
        
        // roll
        const eyesA = getSumCounts(dicesA)
        const eyesB = getSumCounts(dicesB)
        
        const resultWithEyeA = new Map()
        
        eyesA.forEach((Acount, Asum)=>{
            eyesB.forEach((Bcount, Bsum)=>{
                if(Asum>Bsum){
                    winCount += Acount * Bcount
                    if(winCount > maxWins){
                        maxWins = winCount
                        answer = dicesA.map(v=>v+1)
                    }
                } else{
                    loseCount += Acount * Bcount
                }
            })
        })
    }
    
    function getSumCounts(dices){
        const sumCounts = new Map()
        rollDice(0, dices, 0, sumCounts)
        return sumCounts
    }
    
    function rollDice(currentSum, dices, index, sumCounts){
        if(index==dices.length){
            if(!sumCounts.has(currentSum))
                sumCounts.set(currentSum, 0)
            return sumCounts.set(currentSum, sumCounts.get(currentSum)+1)
        }
        for(let i=0; i<6; i++){
            rollDice(currentSum + dice[dices[index]][i], dices, index+1, sumCounts)
        }
    }
    
    function sum(arr){
        return arr.reduce((v,a)=>v+a, 0)
    }
    
    return answer;
}

// 6C2 x 4C2 x 36 x 36
// 15 x 6 x 36 x 36 =~ 120000

// 10C5 x 6^5 x 6^5 =~ 150억