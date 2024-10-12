package SWEA.swea_2105;

// 메모리 89,508 kb 	실행시간 285 ms

import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[][] map;
	static int res;
	
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			
			res = -1;
			solve();
			sb.append('#').append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb.toString());
	}
	
	public static void solve() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=1; k<=j; k++)
					searchRoute(new Pos(i, j), k, 1);
			}
		}
	}
	
	static int[][] dirs = {{1, -1},{1, 1},{-1, 1},{-1, -1}};
	
	public static boolean searchRoute(Pos top, int h, int w) {
		Pos left = new Pos(top.y+h, top.x-h);
		Pos right = new Pos(top.y+w, top.x+w);
		Pos bottom = new Pos(top.y+h+w, top.x+w-h);
		Pos[] poss = new Pos[4];
		poss[0] = left;
		poss[1] = bottom;
		poss[2] = right;
		poss[3] = top;
		
		for(int i=0; i<4; i++)
			if(!poss[i].isValidate()) return false;
		
		searchRoute(top, h, w+1);
		
		int y=top.y, x=top.x;
		
		HashSet<Integer> desserts = new HashSet<>();
		
		for(int i=0; i<4; i++) {
			do {
				if(desserts.contains(map[y][x])) return false;
				desserts.add(map[y][x]);
				
				y+=dirs[i][0];
				x+=dirs[i][1];
				
			} while(y!=poss[i].y && x!=poss[i].x);
		}

		if(desserts.size() > res) res = desserts.size();
		
		return true;
	}
	
	static class Pos{
		int y, x;
		Pos(int y, int x){
			this.y = y;
			this.x = x;
		}
		public boolean isValidate() {
			if(x<0||y<0||x>=N||y>=N) return false;
			return true;
		}
	}

}
