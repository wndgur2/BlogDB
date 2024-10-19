package BOJ.boj_4354;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while(!(input = br.readLine()).equals(".")){
            sb.append(solve(input)).append('\n');
        }
        System.out.println(sb.toString());
    }

    private static int solve(String input) {
        int res = 0;
        char[] pattern = input.toCharArray();
        int n = input.length();
        int[] pi = new int[n];

        int j=0;
        for(int i=1; i<n; i++) {
            while(j>0 && pattern[i] != pattern[j]){
                j = pi[j-1];
            }
            if(pattern[i]==pattern[j]){
                pi[i] = ++j;
            }
        }

        if(n%(n-pi[n-1])!=0) return 1;
        res = n/(n-(pi[n-1]));

        return res;
    }
}

/**
abababc
  abababc
0012340
 */