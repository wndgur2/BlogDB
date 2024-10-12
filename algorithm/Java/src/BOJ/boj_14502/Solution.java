package BOJ.boj_14502;

import java.io.*;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[][] map;
	static int[][] tempMap;
	static int N, M;
	static int[] initialCounts;
	static int[][] virusPos;
	static int res;
	static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	public static void main(String[] args) throws IOException{
		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);
		map = new int[N][M];
		tempMap = new int[N][M];
		initialCounts = new int[3];
		virusPos = new int[10][2];
		
		for(int i=0; i<N; ++i) {
			String[] vals = br.readLine().split(" ");
			for(int j=0; j<M; ++j) {
				map[i][j] = Integer.parseInt(vals[j]);
				if(map[i][j]==2) {
					virusPos[initialCounts[2]][0] = i;
					virusPos[initialCounts[2]][1] = j;
				}
				initialCounts[map[i][j]]++;
			}
		}
		// 모든 빈자리에 벽을 놓는다. 64c3
		makeWall(0, 0, 3); // y, x, left walls,  
		// 배열을 복사하고 바이러스 확산. 남은 빈칸 수를 기록한다. 3 * 64
		System.out.println(res);
	}
	
	static void makeWall(int y, int x, int wallN) {
		if(wallN == 0) {
			renewRes();
			return;
		}
		if(y>=N) return;
		
		int newY = x==M-1?y+1:y;
		int newX = x==M-1?0:x+1;
		
		// 벽을 안 세우는 경우 
		makeWall(newY, newX, wallN);
		if(map[y][x] != 0) return;
		
		// 벽을 세우는 경우
		map[y][x] = 1;
		makeWall(newY, newX, wallN-1);
		map[y][x] = 0;
	}
	
	static void initTempMap() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				tempMap[i][j] = map[i][j];
			}
		}
	}
	
	static void printMap() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void printTempMap() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				System.out.print(tempMap[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void renewRes() {
		initTempMap();
		int zeroN = initialCounts[0] - 3;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				if(tempMap[i][j]!=2) continue;
				int temp = dfs(i, j);
				zeroN -= temp;
			}
		}
		if(zeroN > res) {
			res = zeroN;
		}
	}
	
	static int dfs(int y, int x) {	
		int res = 0;
		int ny=y, nx=x;
		for(int[] dir: dirs) {
			ny = y + dir[0];
			nx = x + dir[1];
			if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
			if(tempMap[ny][nx] == 0) {
				tempMap[ny][nx] = 2;
				res += 1 + dfs(ny, nx);
			}
		}
		return res;
	}

}

