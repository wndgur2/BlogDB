package SWEA.swea_4014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

메모리
23,816 kb
실행시간
127 ms

 */

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int slopeSize = Integer.parseInt(st.nextToken());
			
			int[][] rows = new int[N][N];
			int[][] cols = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					rows[i][j] = Integer.parseInt(st.nextToken());
					cols[j][i] = rows[i][j];
				}
			}
			
			int res = 0;
			
			for (int i = 0; i < N; i++) {
				if(constructable(rows[i], slopeSize)) res++;
				if(constructable(cols[i], slopeSize)) res++;
//				System.out.println(i + " : " + res);
			}
			
			sb.append("#" + tc + " " + res + '\n');
		}
		System.out.println(sb.toString());
		
//		System.out.println(constructable(new int[] {1, 1, 2, 2, 3, 3, 3, 3, 3, 4}, 2));
	}

	private static boolean constructable(int[] heights, int slopeSize) {
		boolean[] visit = new boolean[heights.length];
		int prev = heights[0];
//		System.out.println(Arrays.toString(heights));
		for (int i = 1; i < heights.length; i++) {
//			System.out.println(i);
			if(heights[i] == prev+1) {
				if(getFlatLen(heights, i-1, -1, visit, slopeSize) < slopeSize) return false;
			} else if(heights[i] == prev) {
				//
			} else if(heights[i] == prev-1) {
				if(getFlatLen(heights, i, 1, visit, slopeSize) < slopeSize) return false;
			} else {
				return false;
			}
			prev = heights[i];
		}
		return true;
	}

	private static int getFlatLen(int[] heights, int i, int dir, boolean[] visit, int slopeSize) {
		int len = 0;
		int h = heights[i];
		for (int k = i; k < heights.length && k>=0; k+=dir) {
			if(heights[k] != h) break;
			if(visit[k]) break;
			visit[k] = true;
			len++;
			if(len==slopeSize) break;
		}
		return len;
	}
}
