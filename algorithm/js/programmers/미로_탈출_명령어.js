// u r l d (사전역순)
const ds = [[-1, 0, 'u'], [0, 1, 'r'], [0, -1, 'l'], [1, 0, 'd']]

function solution(H, W, y, x, r, c, k) {
    y--;x--;r--;c--;
    
    const distances = Array.from({length:H}, (_, ty)=>
                           Array.from({length: W}, (__, tx)=>
                              distanceBetween(ty, tx, r, c)))
    
    if(distances[y][x] > k || distances[y][x]%2 != k%2) return 'impossible'
    
    const startNode = {ty: y, tx: x, depth: 0, path: ""}
    const queue = [startNode]
    
    // dfs
    while(queue.length>0){
        const {ty, tx, depth, path} = queue.pop()
        if(distances[ty][tx]>k-depth) continue
        if(ty===r && tx===c && depth===k) return path
        
        ds.forEach(([dy, dx, dir])=>{
            const ny = ty + dy
            const nx = tx + dx
            
            if(ny<0||nx<0||ny>=H||nx>=W) return
            queue.push({ty: ny, tx: nx, depth: depth+1, path: path + dir})
        })
    }
    
    return "impossible";
    
    function distanceBetween(y1, x1, y2, x2){
        return Math.abs(x1-x2) + Math.abs(y1-y2)
    }
}