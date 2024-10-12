package BOJ.boj_3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**

124ms
12348kb

1. 물 확장
2. 고슴도치 BFS

반복

 */

public class Main {
	static int H, W;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		char[][] map = new char[H][W];
		boolean[][] visited = new boolean[H][W];
		
		int[] start = new int[2];
		int[] end = new int[2];
		
		for (int i = 0; i < H; i++) {
			char[] str = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				map[i][j] = str[j];
				if(map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
					map[i][j] = '.';
				} else if(map[i][j] == 'D') {
					end[0] = i;
					end[1] = j;
				}
			}
		}
		boolean alive = true;
		boolean goal = false;
		int distance = 0;

		ArrayDeque<int[]> qA = new ArrayDeque<>();
		ArrayDeque<int[]> qB = new ArrayDeque<>();
		ArrayDeque<int[]>[] qs = new ArrayDeque[]{qA, qB};
		qA.add(start);
		
		int qI = 0;
		while(alive && !goal) {
			// bfs
			ArrayDeque<int[]> q = qs[qI];
			qI = ++qI%2;
			while(!q.isEmpty()) {
				int[] cur = q.pollFirst();
				if(map[cur[0]][cur[1]] == '*') continue;
				else if(map[cur[0]][cur[1]] == 'D') {
					goal = true;
					break;
				}
				for(int[] dir:dirs) {
					int ny = cur[0] + dir[0];
					int nx = cur[1] + dir[1];
					if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
					if(visited[ny][nx])continue;
					if(map[ny][nx] == 'X' || map[ny][nx] == '*') continue;
					qs[qI].add(new int[] {ny, nx});
					visited[ny][nx] = true;
				}
			}
			// flood
			flood(map);
			
			if(qs[qI%2].isEmpty()) alive = false;
			distance ++;
		}
		
		if(!goal) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(distance-1);
		}
	}
	
	static int[][] dirs={{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public static void flood(char[][] map) {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]=='*') {
					// 물, 돌, D가 아닌 곳으로 퍼지기
					for(int[] dir: dirs) {
						int ny = i + dir[0];
						int nx = j + dir[1];
						if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
						if(map[ny][nx] == 'X' || map[ny][nx] == 'D' || map[ny][nx]=='*') continue;
						map[ny][nx] = 'W';
					}
				}
			}
		}
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] == 'W') map[i][j] = '*';
			}
		}
	}

}
