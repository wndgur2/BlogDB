package BOJ.boj_9205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

2
2
0 0
1000 0
1000 1000
2000 1000
2
0 0
1000 0
2000 1000
2000 2000

 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int MAX = Integer.MAX_VALUE>>2;
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int[][] pos = new int[n+2][n+2];
			int[][] dp = new int[n+2][n+2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			pos[0] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			for (int i = 1; i < n+1; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				pos[i] = new int[] {y, x};
			}
			
			
			st = new StringTokenizer(br.readLine());
			pos[n+1] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			for (int i = 0; i < n+2; i++) {
				for (int j = 0; j < n+2; j++) {
					dp[i][j] = Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);
					if(dp[i][j] > 1000) dp[i][j] = MAX;
				}
			}
			
			for (int k = 0; k < n+2; k++) {
				for (int i = 0; i < n+2; i++) {
					for (int j = 0; j < n+2; j++) {
						int newDist = dp[i][k] + dp[k][j];
						if(dp[i][j] > newDist) {
							dp[i][j] = newDist;
						}
					}
				}
			}
			
			System.out.println(dp[0][n+1]!=MAX? "happy":"sad");
		}
	}
}

/*

1
3
0 0
1100 100
500 500
3000 0
2500 0

1
3
0 0
1100 100
500 500
2000 0
2500 0


*/