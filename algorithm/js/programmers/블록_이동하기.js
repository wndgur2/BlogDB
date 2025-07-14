function solution(board) {
    var answer = 0;
    var N = board.length
    var board_ = Array(N+2).fill(0).map((v)=>Array(N+2).fill(1))
    
    for(let i=1; i<N+1; i++){
        for(let j=1; j<N+1; j++){
            board_[i][j] = board[i-1][j-1]
        }
    }
    
    // BFS (8가지 경우)
    let visited = Array(N+2).fill(0)
        .map((v)=>Array(N+2).fill(0)
             .map(v=>Array(4).fill(false)
         )
    )
    // [y][x][dir]
    
    // 하 우 상 좌
    let dirs = [[1, 0], [0, 1], [-1, 0], [0, -1]]
    let branches = [[{
        y:1,
        x:1,
        dir:1,
        depth:0
    }], []]
    let turn = 0
    
    while(!!branches[0].length || !!branches[1].length){
        while(branches[turn].length>0){
            let next_turn = (turn+1)%2
            let branch = branches[turn].pop()

            let sub_y = branch.y + dirs[branch.dir][0]
            let sub_x = branch.x + dirs[branch.dir][1]

            if((branch.y==N && branch.x==N)
                || (sub_y==N && sub_x==N))
                return branch.depth

            // 무빙
            dirs.forEach(([dy, dx])=>{
                let ny = branch.y + dy
                let nx = branch.x + dx
                let ny2 = branch.y + dy + dirs[branch.dir][0]
                let nx2 = branch.x + dx + dirs[branch.dir][1]
                if(board_[ny][nx]==1 || board_[ny2][nx2]==1) return
                if(visited[ny][nx][branch.dir]) return
                visited[ny][nx][branch.dir] = true
                branches[next_turn].push({
                    y:ny,
                    x:nx,
                    dir:branch.dir,
                    depth:branch.depth+1
                })
            })

            // 회전
            // 메인축
            // 시계방향
            let new_dir = branch.dir==0?3:branch.dir-1
            let new_sub_y = branch.y + dirs[new_dir][0]
            let new_sub_x = branch.x + dirs[new_dir][1]
            let diagonal = board_[branch.y + dirs[branch.dir][0] + dirs[new_dir][0]][branch.x + dirs[branch.dir][1] + dirs[new_dir][1]]
            if(board_[new_sub_y][new_sub_x]!=1
                && diagonal!=1
                && !visited[branch.y][branch.x][new_dir]){
                visited[branch.y][branch.x][new_dir] = true
                branches[next_turn].push({
                    ...branch,
                    dir: new_dir,
                    depth: branch.depth+1
                })
            }
            // 반시계방향
            new_dir = branch.dir==3?0:branch.dir+1
            new_sub_y = branch.y + dirs[new_dir][0]
            new_sub_x = branch.x + dirs[new_dir][1]
            diagonal = board_[branch.y + dirs[branch.dir][0] + dirs[new_dir][0]][branch.x + dirs[branch.dir][1] + dirs[new_dir][1]]
            
            if(board_[new_sub_y][new_sub_x]!=1
               && diagonal!=1
               && !visited[branch.y][branch.x][new_dir]){
                visited[branch.y][branch.x][new_dir] = true
                branches[next_turn].push({
                    ...branch,
                    dir: new_dir,
                    depth: branch.depth+1
                })
            }

            // 서브축
            // 시계방향
            new_dir = branch.dir==0?3:branch.dir-1
            let new_y = branch.y + dirs[branch.dir][0] - dirs[new_dir][0]
            let new_x = branch.x + dirs[branch.dir][1] - dirs[new_dir][1]
            diagonal = board_[branch.y - dirs[new_dir][0]][branch.x - dirs[new_dir][1]]
            
            if(board_[new_y][new_x]!=1
               && diagonal!=1
               && !visited[new_y][new_x][new_dir]){
                visited[new_y][new_x][new_dir] = true
                branches[next_turn].push({
                    y: new_y,
                    x: new_x,
                    dir: new_dir,
                    depth: branch.depth+1
                })
            }
            
            // 반시계방향
            new_dir = branch.dir==3?0:branch.dir+1
            new_y = branch.y + dirs[branch.dir][0] - dirs[new_dir][0]
            new_x = branch.x + dirs[branch.dir][1] - dirs[new_dir][1]
            diagonal = board_[branch.y - dirs[new_dir][0]][branch.x - dirs[new_dir][1]]
            
            if(board_[new_y][new_x]!=1
               && diagonal!=1
               && !visited[new_y][new_x][new_dir]){
                visited[new_y][new_x][new_dir] = true
                branches[next_turn].push({
                    y: new_y,
                    x: new_x,
                    dir: new_dir,
                    depth: branch.depth+1
                })
            }
        }

        turn = (turn+1)%2
    }
    
    return answer;
}