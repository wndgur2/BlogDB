package SWEA.swea_2112;

/**
	이중혁
	
 	88,460 kb
	메모리
	378 ms
	실행시간
 */

import java.io.*;
import java.util.*;

public class Solution {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringBuilder sb = new StringBuilder();
	public static StringTokenizer st;
	public static int D, W, K, res;
	public static int[][] arr;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			arr = new int[D][W];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
//				System.out.println(Arrays.toString(arr[i]));
			}
			
			sb.append("#").append(tc).append(' ').append(solve()).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static int solve() {
		res = K;
		subset(0, 0);
		return res;
	}
	
	public static void subset(int depth, int drug) {
		if(drug>=res) return;
		if(depth == D) {
			// 검사 + 답 갱신
			if(check()) res = drug;
			return;
		}
		
		int[] tmp = Arrays.copyOf(arr[depth], W);
		
		// nothing to depth
		subset(depth+1, drug);
		
		// use drug A to depth
		Arrays.fill(arr[depth], 1);
		subset(depth+1, drug+1);
		
		// use drug B to depth
		Arrays.fill(arr[depth], 0);
		subset(depth+1, drug+1);
		
		arr[depth] = tmp;
	}
	
	public static boolean check() {
		// 열을 돌면서 조건 통과하는지 확인
		for (int col = 0; col < W; col++) {
			int cnt=1;
			int maxCnt=1;
			for (int row = 1; row < D; row++) {
				if(arr[row][col] == arr[row-1][col]) {
					if(++cnt > maxCnt) maxCnt = cnt;
				}
				else cnt = 1;
			}
			if(maxCnt < K) return false;
		}
		
		return true;
	}

}
