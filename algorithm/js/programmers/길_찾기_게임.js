const MIN_X=0
const MAX_X=100_000

function solution(nodeinfo) {
    const N = nodeinfo.length
    const pres = []
    const posts = []
    var answer = [pres, posts]
    
    nodeinfo = nodeinfo.map((v, i)=>[...v, i])
    
    nodeinfo.sort((a,b)=>{
        const [ax, ay] = a
        const [bx, by] = b
        return ay==by?ax-bx:by-ay
    })
    
    const root = nodeinfo[0]
    const levelsMap = new Map()
    nodeinfo.forEach((node)=>{
        if(levelsMap.has(node[1])) levelsMap.get(node[1]).push(node)
        else levelsMap.set(node[1], [node])
    })
    
    const levels = Array.from(levelsMap.keys())
    
    // make tree
    const tree = Array(N).fill(0).map(v=>Array(N).fill(false))

    
    // dfs
    dfs(root, MIN_X, MAX_X)
    
    return answer;
    
    function dfs(node, startX, endX){
        const [x, y, i] = node
        pres.push(i+1)
        getChildren(node, startX, endX).forEach((child)=>{
            if(child[0]>x) dfs(child, x+1, endX)
            else dfs(child, startX, x-1)
        })
        posts.push(i+1)
    }
    
    function getChildren(node, startX, endX){
        const [x, y, index] = node
        
        if(y<=levels[levels.length-1]) return []
        
        // 다음 level
        const nextLevel = levels[levels.indexOf(y)+1]
        
        // find children
        const children = []
        levelsMap.get(nextLevel).forEach(([tmpX, tmpY, i])=>{
            if(tmpX>=startX && tmpX<=endX)
                children.push([tmpX, tmpY, i])
        })
        return children
    }
}