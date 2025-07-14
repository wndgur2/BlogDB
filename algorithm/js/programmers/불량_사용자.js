function solution(user_id, banned_id) {
    var banned_set = new Set()
    
    dfs(0, banned_id, user_id, [])
    return banned_set.size;
    
    function dfs(index, banned_patterns, users, banned_users){
        if(index>=banned_patterns.length){
            let temp_arr = banned_users.slice().sort()
            banned_set.add(serialize(temp_arr))
            return
        }
        
        let banned_pattern = banned_patterns[index]
        for(let i=0; i<users.length; i++){
            let user_id = users[i]
            if(!!banned_users.find((u)=>u==user_id)) continue
            if(match(user_id, banned_pattern)){
                banned_users.push(user_id)
                dfs(index+1, banned_patterns, users, banned_users)
                banned_users.pop()
            }
        }
    }
    
    function match(id, con){
        if(id.length!=con.length) return false
        let length = id.length
        for(let i=0; i<length; i++){
            if(id[i]!=con[i])
                if(id[i]!='*' && con[i]!='*')
                    return false
        }
        return true
    }
    
    function serialize(id_list){
        return id_list.join('')
    }
}
