package JO.jo_1661.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int ex, ey;
	static int[][] board;
	static int H, W;
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // x 8
		H = Integer.parseInt(st.nextToken()); // y 7

		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken())-1;
		int y1 = Integer.parseInt(st.nextToken())-1;
		ex = Integer.parseInt(st.nextToken())-1;
		ey = Integer.parseInt(st.nextToken())-1;
		

		board = new int[H][W];
		
		for(int i=0; i<H; i++) {
			String row = br.readLine();
			for (int j = 0; j < W; j++) {
				board[i][j] = row.charAt(j) == '1'? 0: 10000;
			}
		}
		dfs(y1, x1, 0);
		System.out.println(board[ey][ex]);
	}
	
	public static void dfs(int y, int x, int cost) { // y,x에서 ey,ex까지 최소거리
		board[y][x] = cost;
		if(y==ey && x==ex) return;
		int nx, ny;
		for (int[] dir: dirs) {
			ny = y + dir[0];
			nx = x + dir[1];
			
			if (ny < 0 || nx<0 || ny>=H || nx>=W)continue;
			if (board[ny][nx] <= cost+1) continue;
			dfs(ny, nx, cost+1);
		}
	}
}