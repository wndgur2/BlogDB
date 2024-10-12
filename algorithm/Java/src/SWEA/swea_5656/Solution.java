package SWEA.swea_5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**

89,068 kb
메모리
283 ms
실행시간

1 ≤ N ≤ 4
2 ≤ W ≤ 12
2 ≤ H ≤ 15

 */

public class Solution {
	static int N, H, W, res;
	static int[][] board = new int[15][12];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			// dfs로 가장 적은 블록 경우 찾기
			// W^4 = 2만
			// 터트리는 연산: 커봐야 W*H=180
			// -> 3600만
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
					board[i][j] = Integer.parseInt(st.nextToken());
			}
			
			res = 180;
			dfs(0);
			sb.append('#').append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb.toString());
	}

	private static void dfs(int depth) {
		if(depth==N) {
			int leftBlocks = countBlocks();
			if(leftBlocks<res) {
				res = leftBlocks;
			}
			return;
		}
		
		int[][] board2 = new int[15][12];
		for (int i = 0; i < H; i++)
			board2[i] = Arrays.copyOf(board[i], 12);

//		for (int i = 0; i < H; i++)
//			System.out.println(Arrays.toString(board[i]));
//		System.out.println(res);
		
		for (int i = 0; i < W; i++) {
			bomb(i);
			fall();
			dfs(depth+1);

			for (int j = 0; j < H; j++)
				board[j] = Arrays.copyOf(board2[j], 12);
		}
	}

	private static void fall() {
		int ground;
		for (int j = 0; j < W; j++) {
			ground = H-1;
			for (int i = H-1; i >=0; i--) {
				if(board[i][j]>0) {
					int v = board[i][j];
					board[i][j] = 0;
					board[ground--][j] = v;
				}
			}
		}
	}

	private static void bomb(int j) {
		for (int i = 0; i < H; i++) {
			if(board[i][j]>0) {
				explode(i, j);
				break;
			}
		}
	}
	
	public static int[][] dirs = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

	private static void explode(int i, int j) {
		if(board[i][j]==0) return;
		
		int power = board[i][j];
		board[i][j] = 0;
		for(int[] dir:dirs) {
			int ny = i, nx = j;
			for (int k = 0; k < power; k++) {
				if(ny<0 || nx<0 || ny>=H || nx>=W) break;
				explode(ny, nx);
				ny+=dir[0];
				nx+=dir[1];
			}
		}
	}

	private static int countBlocks() {
		int count = 0;
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				if(board[i][j]>0) count ++;
		return count;
	}

}
