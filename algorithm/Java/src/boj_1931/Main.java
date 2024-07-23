package boj_1931;

import java.io.*;
import java.util.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long[][] tasks = new long[N][2];
        for(int i=0; i<N;++i){
            String[] SE = br.readLine().split(" ");
            tasks[i][0] = Long.parseLong(SE[0]);
            tasks[i][1] = Long.parseLong(SE[1]);
        }
        Arrays.sort(tasks, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if(o1[1]<0 && o2[1]>=0){
                    return 1;
                } else if(o1[1]>=0 && o2[1]<0){
                    return -1;
                } else if(o1[1] > o2[1]){
                    return 1;
                } else if(o1[1] == o2[1]){
                    if(o1[0]<0 && o2[0]>=0){
                        return 1;
                    } else if(o1[0]>=0 && o2[0]<0){
                        return -1;
                    } else if(o1[0] > o2[0]){
                        return 1;
                    } else{
                        return -1;
                    }
                } else{
                    return -1;
                }
            }
        });
        int res = 0;
        long currentTime = 0;
        for(long[] task:tasks){
            if(task[0]<0 && currentTime>0){
                res++;
                currentTime = task[1];
                // System.out.println(Arrays.toString(task));
            }
            else if(task[0] >= currentTime){
                res++;
                currentTime = task[1];
                // System.out.println(Arrays.toString(task));
            }
        }
        bw.write(Long.toString(res));
        bw.flush();
        bw.close();
        br.close();
    }
}