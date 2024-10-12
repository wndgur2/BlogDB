package SWEA.swea_5643;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**

a의 키 순서를 알기 위해서, a에서 b로 갈 수 없다면 b에서 a로 갈 수 있어야 한다.
 
메모리
93,376 kb

실행시간
1,348 ms

 */

public class Solution2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int E = Integer.parseInt(br.readLine());
			StringTokenizer st = null;
			int[][] dp = new int[N][N];
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken())-1;
				int to = Integer.parseInt(st.nextToken())-1;
				dp[from][to] = 1;
			}
			
			for (int k = 0; k < N; k++) 
				for (int i = 0; i < N; i++) 
					for (int j = 0; j < N; j++) 
						if(dp[i][k]==1 && dp[k][j]==1) dp[i][j] = 1;
			
			int res = 0;
			for (int i = 0; i < N; i++) {
				// i의 키 순서를 알 수 있는가?
				boolean canFigure = true;
				for (int j = 0; j < N; j++) {
					if(i==j) continue;
					if(dp[i][j]==0 && dp[j][i] == 0) {
						canFigure = false;
						break;
					}
				}
				if(canFigure) res++;
			}
			sb.append("#").append(tc).append(" ").append(res).append('\n');
		}
		System.out.println(sb.toString());
	}

}
