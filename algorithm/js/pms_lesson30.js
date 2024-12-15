function solution(n) {
    var answer = [[]];
    answer = [...Array(n)].map((_)=>[...Array(n).fill(0)])
    let r = 0
    let c = 0
    let i = 1
    let dirs = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    let dir_i = 0
    while(i<=n**2){
        answer[r][c] = i++

        console.log(answer)
        
        move()
        
        if(c>=n || r>=n || c<0 || r<0 || answer[r][c]!=0) {
            moveBack()
            dir_i++
            move()
        }
    }

    function moveBack(){
        const dir = dirs[dir_i%4]
        r -= dir[0]
        c -= dir[1]
    }
    
    function move(){
        const dir = dirs[dir_i%4]
        r += dir[0]
        c += dir[1]
    }
    
    return answer;
}

console.log(solution(4))