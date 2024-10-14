package SWEA.swea_1954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 	이중혁 1954 달팽이_숫자  풀이 
 * 
 *	메모리
 *	18,452 kb
 *	
 *	실행시간
 *	100 ms
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=T; i++) {
			sb.append('#').append(i).append('\n');
			sb.append(new Solution().solve());
		}
		System.out.println(sb.toString());
	}
	
	String solve() throws IOException{
		int n = Integer.parseInt(br.readLine());
		if(n==1) return "1\n";
		int[][] snail = new int[n][n];
		int y=0, x=0, nx, ny;
		int dir = 0;
		int num = 1;
		while(true) {
			if(snail[y][x] != 0) break;
			snail[y][x] = num++;
			ny = y+dirs[dir][0];
			nx = x+dirs[dir][1];
			if(nx==n || ny==n || nx==-1 || ny==-1 || snail[ny][nx] != 0) {
				dir = (dir+1)%4;
				ny = y+dirs[dir][0];
				nx = x+dirs[dir][1];
			}
			y = ny;
			x = nx;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int[] row : snail) {
			for(int cell: row) {
				sb.append(cell).append(' ');
			}
			sb.append('\n');
		}
		
		return sb.toString();
	}
}
