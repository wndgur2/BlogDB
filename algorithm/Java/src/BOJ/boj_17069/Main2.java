package BOJ.boj_17069;
import java.io.*;
import java.util.*;

/*
 * 시간 : 104ms, 메모리 : 14300KB
 */

public class Main2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		boolean[][] map = new boolean[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				if(st.nextToken().equals("1")) map[i][j] = true;
			}
		}
		int[][][] dp = new int[N][N][3]; //0: -. 1: \, 2: | 로 도착했을 때의 경우의 수
		
		for(int j=1;j<N;j++) {
			if(map[0][j]==true) break; 
			dp[0][j][0] = 1;
		}
		
		for(int i=1;i<N;i++) {
			for(int j=2;j<N;j++) {
				if(map[i][j]) continue;
				dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][1];
				dp[i][j][2] = dp[i-1][j][1] + dp[i-1][j][2];
				if(map[i-1][j] || map[i][j-1]) continue;
				dp[i][j][1] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
				
			}
		}
		bw.write(dp[N-1][N-1][0]+dp[N-1][N-1][1]+dp[N-1][N-1][2]+"");
		bw.flush();
	}

}
