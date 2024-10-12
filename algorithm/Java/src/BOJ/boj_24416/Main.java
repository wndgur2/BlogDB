package BOJ.boj_24416;

import java.util.Scanner;

public class Main {
	static int rc_cnt=1, dp_cnt=1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		recursiveFibo(n);
		dpFibo(n);
		System.out.println(rc_cnt + " " + dp_cnt);
		
	}
	
	public static int recursiveFibo(int n) {
		if(n==2 || n==1) return 1;
		rc_cnt++;
		return recursiveFibo(n-1) + recursiveFibo(n-2);
	}
	
	public static int dpFibo(int n) {
		int[] dp = new int[n+1];
		dp[1] = 1;
		dp[2] = 1;
		for (int i = 3; i < n; i++) {
			dp[i] = dp[i-1] + dp[i-2];
			dp_cnt++;
		}
		
		return dp[n];
	}

}
