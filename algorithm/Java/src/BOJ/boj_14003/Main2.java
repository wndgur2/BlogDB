package BOJ.boj_14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main2 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] indexes = new int[N];
        ArrayList<Integer> dp = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            int idx = (-Collections.binarySearch(dp, numbers[i]) -1);
            if(idx<0) continue;
            if(idx>= dp.size()) dp.add(numbers[i]);
            else dp.set(idx, numbers[i]);
            indexes[i] = idx;
        }

        int curIdx = dp.size()-1;
        ArrayDeque<Integer> stack = new ArrayDeque<>(); 
        for(int i=N-1; i>=0; i--){
            if(indexes[i] == curIdx){
                curIdx--;
                stack.addFirst(numbers[i]);
                if(curIdx==-1) break;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(dp.size()).append("\n");
        while(!stack.isEmpty())sb.append(stack.pop() + " ");
        System.out.println(sb.toString().trim());
    }
}
