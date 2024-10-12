package BOJ.boj_11780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

그러면 그냥 ArrayList

 */

public class AL_TEST {
	static final int INF = 1000000001;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		int E = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][N];
		ArrayList<Integer>[][] paths = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			// 최소비용 초기값
			Arrays.fill(dp[i], INF);
			dp[i][i] = 0;
			
			// 연결리스트 객체 생성
			Arrays.fill(paths[i], new ArrayList<>());
		}
		for (int i = 0; i < E; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int cost = Integer.parseInt(st.nextToken());
			if(dp[from][to] > cost) {
				dp[from][to] = cost;
			}
		}
		
		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if(i==k) continue;
				for (int j = 0; j < N; j++) {
					if(j==k || i==j) continue;
					// k를 거치는 경우가 더 빠름
					if(dp[i][k] + dp[k][j] < dp[i][j]) {
						// 비용 갱신
						dp[i][j] = dp[i][k] + dp[k][j];
						
						// 경로 갱신
						// 두 경로 사이에 k를 끼워 저장
						ArrayList<Integer> newPath = new ArrayList<>();
						newPath.addAll(paths[i][k]);
						newPath.add(k);
						newPath.addAll(paths[k][j]);
						paths[i][j] = newPath;
					}
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

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(dp[i][j]==INF || dp[i][j]==0){
					sb.append('0').append('\n');
					continue;
				}
				sb.append(paths[i][j].size() + 2);
				sb.append(' ').append(i+1).append(' ');
				for(int node: paths[i][j]) {
					sb.append(node+1).append(' ');
				}
				sb.append(j+1).append('\n');
			}
		}
		System.out.println(sb.toString());
	}
}
