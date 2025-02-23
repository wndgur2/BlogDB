// https://school.programmers.co.kr/learn/courses/30/lessons/214289
let OUTSIDE_TEMP, T1, T2, A, B, ONBOARD;

function solution(temperature, t1, t2, a, b, onboard) {
    var answer = 0;
    
    OUTSIDE_TEMP=temperature
    T1=t1
    T2=t2
    A=a
    B=b
    ONBOARD=onboard
    
    let cur=0;
    while(cur<onboard.length)
        if(checkNoBoard(cur)) break;
        if(onboard[cur]){
            const time_to_maintain = getTimeToMaintain(cur);
            maintain(time_to_maintain);
        } else{
            const time_to_prepare = getTimeToPrepare(cur);
        }
    }
    
    return answer;
}

function checkNoBoard(){
    //
}

function getTimeToMaintain(cur){
    for(let i=cur; i<ONBOARD.length; i++)
        if(ONBOARD[i]==0) break
    return i-cur
}

function getTimeToPrepare(cur){
    for(let i=cur; i<ONBOARD.length; i++)
        if(ONBOARD[i]==1) break
    return i-cur
}

function maintain(duration){
    if(A>B*2){
        //유지
        return B * duration
    } else{
        return A * duration/2 + duration%2*B
    }
}