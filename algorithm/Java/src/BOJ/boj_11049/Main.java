package BOJ.boj_11049;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// 병합정렬처럼 작동하기
		// 3개를 봐서 1,2를 합칠지 2,3을 합칠지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int[][] matrix = new int[n][2];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			matrix[i][0] = r;
			matrix[i][1] = c;
		}
		int[][] dp = new int[n+1][n+1]; // i~j를 합쳤을 때 최소 연산 횟수 
		System.out.println(dp[0][n]);
	}
}
