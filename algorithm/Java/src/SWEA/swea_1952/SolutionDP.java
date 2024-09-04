package SWEA.swea_1952;

import java.io.*;
import java.util.*;

public class SolutionDP {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int Day, Month, ThreeMonth, Year;
	static int[] dp;
	static int[] plan;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		dp = new int[13];
		plan = new int[13];
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			Day = Integer.parseInt(st.nextToken());
			Month = Integer.parseInt(st.nextToken());
			ThreeMonth = Integer.parseInt(st.nextToken());
			Year = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			// dp[i]: i월까지 다닐 수 있는 최소 가격
			Arrays.fill(dp, 987654321);
			dp[0] = 0;
			for (int i = 1; i < 13; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i < 13; i++) {
				// 1달권 vs 1일권
				dp[i] = Math.min(dp[i-1]+Month, dp[i-1] + plan[i]*Day);
				
				// 3달권
				if(i>=3) {
					dp[i] = Math.min(dp[i-3]+ThreeMonth, dp[i]);
				} else {
					dp[i] = Math.min(ThreeMonth, dp[i]);
				}
			}
			
			dp[12] = Math.min(dp[12], Year);
			
			System.out.println("#" + tc + " " + dp[12]);
		}
	}
}
