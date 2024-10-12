package BOJ.boj_11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = 1000000001;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], INF);
			dp[i][i] = 0;
		}
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			dp[from][to] = Math.min(dp[from][to], cost);
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(i==k) continue;
				for (int j = 0; j < N; j++) {
//					if(j==k || i==j) continue;
					dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
//					System.out.println(i + " " + j + " " + " " + k);
//					if(i==1 && j==4 && k==3)
//						System.out.println(dp[i][j] + " " + dp[i][k] + " "+ dp[k][i]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(dp[i][j]==INF?0:dp[i][j]).append(' ');
//				sb.append(dp[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
