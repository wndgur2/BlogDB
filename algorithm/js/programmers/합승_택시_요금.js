function solution(n, s, a, b, fares) {
    var answer = -1;
    
    // index화
    a--
    b--
    s--
    
    // 비용 테이블
    const fareTable = Array(n).fill(0).map(v=>Array(n).fill(-1))
    fares.forEach(([ta, tb, cost])=>{
        ta--
        tb--
        fareTable[ta][tb] = cost
        fareTable[tb][ta] = cost
    })
    
    // S부터 각 지점 까지의 최저 비용 
    const commonCosts = Array(n).fill(-1)
    const fromACosts = Array(n).fill(-1)
    const fromBCosts = Array(n).fill(-1)
    
    // 다익스트라
    dijkstra(s, commonCosts)
    dijkstra(a, fromACosts)
    dijkstra(b, fromBCosts)
    
    commonCosts.forEach((cost, i)=>{
        if(cost == -1) return
        let temp_result = cost
        
        // i까지 같이 타고 감
        temp_result += fromACosts[i]
        temp_result += fromBCosts[i]
        
        if(answer==-1 || temp_result<answer) answer = temp_result
    })
    
    return answer;
    
    function dijkstra(s, costArray){
        // bfs
        const queue = [[s,0]]
        costArray[s] = 0
        while(queue.length>0){
            const [node, cost] = queue.shift()
            if(cost>costArray[node]) continue
            
            fareTable[node].forEach((fare, to)=>{
                if(fare==-1) return
                
                const newCost = cost+fare
                if(costArray[to]>=0 && newCost>=costArray[to]) return
                costArray[to] = newCost
                queue.push([to, newCost])
            })
        }
    }
}