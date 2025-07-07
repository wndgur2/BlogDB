function solution(n, t, m, timetable) {
    // 마지막에 타는 사람 -1분 혹은 마지막에 오는 셔틀 시간
    timetable.sort()
    timetable.reverse()
    
    let hour=9
    let min=0
    
    let answer = [0, 0]
    
    for(let i=0; i<n; i++){
        if(i>0) [hour, min] = tictoc(hour, min, t)
        let onboard = 0
        
        if(i==n-1){ // last bus
            while(onboard<m){
                if(timetable.length==0) {
                    answer = [hour, min]
                    break
                }
                let [next_h, next_m] = parseTime(timetable[timetable.length-1])
                if(next_h>hour || (next_h==hour && next_m>min)){
                    answer = [hour, min]
                    break
                }
                onboard++
                let [last_h, last_m] = parseTime(timetable.pop())
                if(onboard==m){
                    if(last_m==0){
                        last_h--
                        last_m = 59
                    } else last_m--
                    answer = [last_h, last_m]
                }
            }
        } else{
            while(onboard<m){
                if(timetable.length==0) break
                let [next_h, next_m] = parseTime(timetable[timetable.length-1])
                if(next_h>hour || (next_h==hour && next_m>min)) break
                onboard++
                timetable.pop()
            }
        }
    }
    
    return parseString(answer)
    
    function tictoc(hour, min, term){
        min = min+term
        if(min>=60){
            hour++
            min-=60
        }
        
        return [hour, min]
    }
    
    function parseTime(timeString){
        let [h, m] = timeString.split(':')
        return [Number(h), Number(m)]
    }
    
    function parseString([hour, min]){
        let res = ""
        if(hour<10) res = "0"
        res += hour
        res += ":"
        if(min<10) res += "0"
        res += min
        return res
    }
}