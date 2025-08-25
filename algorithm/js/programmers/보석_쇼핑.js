const MAX_LENGTH = 100_000

function solution(gems) {
    let answer = [0, 0]
    let from = 0
    let minLength = MAX_LENGTH
    const indexes = new Map()
    const kinds = new Set(gems).size
    
    let prevNode = null
    // 100,000번
    gems.forEach((gem, i)=>{
        if(prevNode?.gem===gem)
            prevNode = prevNode.prev
        
        const node = {
            gem, 
            index: i, 
            prev: null, 
            next: null
        }
        
        if(prevNode){
            node.prev = prevNode
            prevNode.next = node
        }
        
        if(indexes.has(gem)){
            const alreadyExistedNode = indexes.get(gem)
            const {index, prev, next} = alreadyExistedNode
            if(index === from){
                if(next !== null){
                    from = next.index
                    next.prev = null
                } else{
                    from = i
                }
            } else{
                // index !== from 이므로 prev는 항상 존재
                if(next !== null){
                    prev.next = next
                    next.prev = prev
                } else{
                    prev.next = node    
                }
            }
        }
        
        indexes.set(gem, node)
        
        if(indexes.size==kinds){
            const length = i-from
            if(length < minLength){
                minLength = length
                answer = [from+1, i+1]
            }
        }
        
        prevNode = node
    })
    
    return answer;
}