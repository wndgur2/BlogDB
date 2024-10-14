package BOJ.boj_11047;

import java.io.*;

public class Main{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        String[] N_COST = br.readLine().split(" ");
        int N = Integer.parseInt(N_COST[0]);
        int cost = Integer.parseInt(N_COST[1]);
        int[] units = new int[N];
        while(N-->0){
            units[N] = Integer.parseInt(br.readLine());
        }
        int currentCost = 0;
        int idx=0, res = 0;
        while(currentCost!=cost){
            if(currentCost>cost){
                currentCost-=units[idx++];
                currentCost+=units[idx];
            } else if(currentCost<cost){
                currentCost+=units[idx];
                res ++;
            }
        }
        sb.append(res).append('\n');
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}