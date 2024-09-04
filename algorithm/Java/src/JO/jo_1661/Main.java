package JO.jo_1661;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken()); // x 8
		int H = Integer.parseInt(st.nextToken()); // y 7

		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken())-1;
		int y1 = Integer.parseInt(st.nextToken())-1;
		int x2 = Integer.parseInt(st.nextToken())-1;
		int y2 = Integer.parseInt(st.nextToken())-1;
		
		boolean[][] visited = new boolean[H][W];
		for(int i=0; i<H; i++) {
			String row = br.readLine();
			for (int j = 0; j < W; j++) {
//				System.out.println(i + " " + j);
				visited[i][j] = row.charAt(j) == '1';
			}
		}
		
		LinkedList<Node> queue = new LinkedList<>();
		queue.add(new Node(y1, x1, 0));
		visited[y1][x1] = true;
		int res = 10000;
		while(!queue.isEmpty()) {
			Node cur = queue.pop();
			if(cur.x == x2 && cur.y == y2) {
				if(cur.cost < res) res = cur.cost;
				break;
			}
			
			for(int[] dir: dirs) {
				int ny = cur.y + dir[0];
				int nx = cur.x + dir[1];
				
				if(ny<0 || nx<0 || ny>=H || nx>=W) continue;
				if(visited[ny][nx]) continue;
				visited[ny][nx] = true;
				queue.add(new Node(ny, nx, cur.cost + 1));
			}
		}
		System.out.println(res);
	}
	
}
class Node{
	int y;
	int x;
	
	int cost = 0;
	
	Node(int y, int x, int cost){
		this.y = y;
		this.x = x;
		this.cost = cost;
	}
}
