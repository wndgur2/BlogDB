function solution(key, lock) {
    let holes = lock.reduce((last_val, new_val)=>
        last_val+new_val.filter(v=>v==0).length
        , 0)
    
    let keys = []
    for(let i=0; i<4; i++){
        keys.push(key)
        key = rotate(key)
    }
    
    let M = key.length
    let N = lock.length
    let lock_w_pad = Array(N+(M-1)*2).fill(0).map(v=>Array(N+(M-1)*2).fill(2))
    for(let i=M-1; i<N+M-1; i++){
        for(let j=M-1; j<N+M-1; j++){
            lock_w_pad[i][j]=lock[i-M+1][j-M+1]
        }
    }
    
    for(let i=0; i<N+M-1; i++){
        for(let j=0; j<N+M-1; j++){
            for(let k=0; k<4; k++){
                if(fit(i, j, k)) return true
            }
        }
    }
    
    return false
    
    function rotate(arr){
        let height = arr.length
        let width = arr[0].length
        let new_arr = Array(height).fill(0).map(v=>Array(width))
        
        for(let i=0; i<height; i++){
            for(let j=0; j<width; j++){
                new_arr[i][j] = arr[height-j-1][i]
            }
        }
        
        return new_arr
    }
    
    function fit(y, x, k){
        let key = keys[k]
        let tmp_holes = holes
        for(let i=0; i<M; i++){
            for(let j=0; j<M; j++){
                let lock_y = y + i
                let lock_x = x + j
                if(key[i][j]==lock_w_pad[lock_y][lock_x]) return false
                if(key[i][j]==1 && lock_w_pad[lock_y][lock_x]==0) tmp_holes--
            }
        }
        
        if(tmp_holes==0) return true
        return false
    }
}