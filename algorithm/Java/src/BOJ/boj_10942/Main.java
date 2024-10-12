package BOJ.boj_10942;

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
		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] dp = new boolean[N][N]; // dp[i][j] = i~j가 팰린드롬? xx
		
		for (int i = 0; i < N; i++) {
			dp[i][i] = true;
			if(i<N-1 && numbers[i]==numbers[i+1])
				dp[i][i+1] = true;
		}
		
		for (int length = 3; length <= N; length++) {
			for (int left = 0; left < N-length+1; left++) {
				int right = left + length -1; // inclusive
				if(numbers[left]!=numbers[right]) continue;
				dp[left][right] = dp[left+1][right-1];
			}
		}
		
		int q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken())-1;
			int right = Integer.parseInt(st.nextToken())-1;
			sb.append(dp[left][right]?"1":"0").append('\n');
		}
		System.out.println(sb.toString());
	}
}
