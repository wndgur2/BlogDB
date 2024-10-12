package JO.jo_1441;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

// 104ms, 32512MB

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		dp[1] = 1;
		dp[2] = 3;
		for (int i = 3; i <= N; i++) 
			dp[i] = (dp[i-1] + dp[i-2]*2)%20100529;
		System.out.println(dp[N]);
	}

}
