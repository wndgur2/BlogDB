package BOJ.boj_17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

7 8
0 0 0 0 0 0 1 1
1 1 0 0 0 0 1 1
1 1 0 0 0 0 0 0
1 1 0 0 0 1 1 0
0 0 0 0 0 1 1 0
0 0 0 0 0 0 0 0
1 1 1 1 1 1 1 1

 */
public class Main {
	static int H, W, res;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map, edges;
	static int[] parents;
	static int islandNum;
	
	static class Edge implements Comparable<Edge>{
		int from, to, cost;
		
		Edge(int from, int to, int cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}
	
	public static void make() {
		parents = new int[islandNum];
		for (int i = 0; i < dirs.length; i++) {
			parents[i] = i;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra==rb) return false;
		parents[ra] = rb;
		return true;
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken())-2;
			}
		}
		
		
		// 섬 번호 표시
		islandNum = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j]==-1) floodFill(i, j, islandNum++);
			}
		}
		
		edges = new int[islandNum][islandNum];
		
		// 간선 계산
		int prev=-1, cur, cost=0;
		// 행
		for (int i = 0; i < H; i++) {
			cost = 0;
			prev = -1;
			for (int j = 0; j < W; j++) {
				cur = map[i][j];
				if(cur==-2) {
					cost++;
				} else if(cur!=prev) {
					if(prev!=-1 && cost>1) {
						if(edges[cur][prev]==0) edges[cur][prev] = cost;
						else edges[cur][prev] = Math.min(edges[cur][prev], cost);
						
						if(edges[prev][cur]==0) edges[prev][cur] = cost;
						else edges[prev][cur] = Math.min(edges[prev][cur], cost);
					}
					prev = cur;
					cost = 0;
				} else if(cur==prev) {
					cost = 0;
				}
			}
		}
		
		// 열
		for (int j = 0; j < W; j++) {
			cost = 0;
			prev = -1;
			for (int i = 0; i < H; i++) {
				cur = map[i][j];
				if(cur==-2) {
					cost++;
				} else if(cur!=prev) {
					if(prev!=-1 && cost>1) {
						if(edges[cur][prev]==0) edges[cur][prev] = cost;
						else edges[cur][prev] = Math.min(edges[cur][prev], cost);
						
						if(edges[prev][cur]==0) edges[prev][cur] = cost;
						else edges[prev][cur] = Math.min(edges[prev][cur], cost);
					}
					prev = cur;
					cost = 0;
				} else if(cur==prev) {
					cost = 0;
				}
			}
		}
		
		// kruskal
		// Edge 생성
		
		ArrayList<Edge> edgeList = new ArrayList<>();
		for (int i = 0; i < islandNum; i++) {
			for (int j = 0; j < i; j++) {
				if(edges[i][j]>0) edgeList.add(new Edge(i, j, edges[i][j]));
			}
		}
		
		res = 100;
		
		System.out.println(res==100?-1:res);

//		for (int i = 0; i < islandNum; i++)
//			System.out.println(Arrays.toString(edges[i]));
//		System.out.println();
//
//		for (int i = 0; i < H; i++)
//			System.out.println(Arrays.toString(map[i]));
//		System.out.println();
	}
	
	public static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void floodFill(int y, int x, int num) {
		map[y][x] = num;
		for(int[] dir:dirs) {
			int ny = y + dir[0];
			int nx = x + dir[1];
			
			if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
			if(map[ny][nx] != -1) continue;
			floodFill(ny, nx, num);
		}
	}
}
