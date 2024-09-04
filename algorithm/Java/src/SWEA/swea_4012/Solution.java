package SWEA.swea_4012;

import java.io.*;
import java.util.*;

/**
	요리사 이중혁
	
	메모리
 	38,080 kb
 	
	실행시간
	204 ms
 
 * 
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, R;
	static int[][] S;
	static int minRes;
	static boolean[] selected;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			minRes = 987654321;
			N = Integer.parseInt(br.readLine());
			S = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append('#').append(tc).append(' ');
			solve();
		}
		System.out.println(sb.toString());
	}
	
	public static void solve() {
		selected = new boolean[N];
		R = N/2;
		
		combination(0, 0);
		
		sb.append(minRes).append('\n');
	}
	
	public static void combination(int depth, int start) {
		if(depth == R) {
			int res = calcDiff();
			if(res < minRes) minRes = res;
			return;
		}
		for(int i=start; i<N; i++) {
			selected[i] = true;
			combination(depth+1, i+1);
			selected[i] = false;
		}
	}
	
	public static int calcDiff() {
		int A = getSynergy(false);
		int B = getSynergy(true);
		return Math.abs(A-B);
	}
	
	public static int getSynergy(boolean team) {
		int[] idxOfIngredients = new int[R];
		int idx=0;
		for(int i=0; i<N; i++) 
			if(selected[i]==team) idxOfIngredients[idx++] = i;
		
		int syn = 0;
		for(int i: idxOfIngredients) {
			for(int j:idxOfIngredients) {
				if(i==j) continue;
				syn += S[i][j];
			}
		}
		return syn;
	}
}
