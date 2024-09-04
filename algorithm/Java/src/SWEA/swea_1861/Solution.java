package SWEA.swea_1861;

import java.io.*;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int res, N;
	static int[][] board;
	static int[][] memo; // int[i][j]에서 갈 수 있는 최대 갯수
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<=T; tc++) {
			res = 0;
			
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			memo = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}
			
			sb.append("#").append(tc).append(" ");
			solve();
		}
		System.out.println(sb.toString());
	}
	
	public static void solve() {
		int res = 0;
		int num = 0;
		int tmp;
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				tmp = dfs(row, col);
				if(tmp > res) {
					res = tmp;
					num = board[row][col];
				} else if(tmp==res && num>board[row][col]) {
					num = board[row][col];
				}
			}
		}
		
		sb.append(num).append(" ").append(res).append('\n');
	}
	
	static final int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	
	public static int dfs(int y, int x) {
		if(memo[y][x]>0) return memo[y][x];
		int ny, nx;
		for(int[] dir: dirs) {
			ny = y+dir[0];
			nx = x+dir[1];
			if(ny <0 || nx<0 || nx>=N || ny>=N) continue;
			if(board[ny][nx] != board[y][x]+1) continue;
			return memo[y][x] = dfs(ny, nx)+1;
		}
		return 1;
	}
}
