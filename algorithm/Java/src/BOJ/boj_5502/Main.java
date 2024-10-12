package BOJ.boj_5502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

7
1 2 1 3 1 2 1
4
1 3
2 5
3 3
5 7

1
0
1
1

 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		char[] chars = br.readLine().toCharArray();
		
		boolean[][] dp = new boolean[N][N]; // dp[i][j] = i~j가 팰린드롬? xx
		
		for (int i = 0; i < N; i++) {
			dp[i][i] = true;
			if(i<N-1 && chars[i]==chars[i+1])
				dp[i][i+1] = true;
		}
		
		for (int length = 3; length <= N; length++) {
			for (int left = 0; left < N-length+1; left++) {
				int right = left + length -1; // inclusive
				if(chars[left]!=chars[right]) continue;
				dp[left][right] = dp[left+1][right-1];
			}
		}
		
		/*
		
		 제일 긴 팰린드롬을 찾고,
		 나머지를 맞추기 위해 삽입을 하는데,
		 삽입으로 인한 순서 밀림을 미리 고려한다?
		 
		 133321 -> 1
		 33321 -> 2
		 033321 -> 3
		 
		 */
	}
}
