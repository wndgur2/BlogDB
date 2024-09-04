package SWEA.swea_3124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_Prim {

	static class Edge implements Comparable<Edge>{
		int to, w;
		
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(w, o.w);
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int from, to, w, V, E;
		boolean[] visited;
		long cost;
		PriorityQueue<Edge> q;
		ArrayList<Edge>[] edges;
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edges = new ArrayList[V];
			visited = new boolean[V];
			
			for (int i = 0; i < V; i++) {
				edges[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				from = Integer.parseInt(st.nextToken())-1;
				to = Integer.parseInt(st.nextToken())-1;
				w = Integer.parseInt(st.nextToken());
				edges[from].add(new Edge(to, w));
				edges[to].add(new Edge(from, w));
			}

			q = new PriorityQueue<>();
			for(Edge e: edges[0]) {
				q.add(e);
			}
			visited[0] = true;
			Edge edge;
			int cnt=0;
			cost=0;
			while(!q.isEmpty()) {
				edge = q.poll();
				to = edge.to;
				if(visited[to]) continue;
				w = edge.w;
				cost += (long) w;
				visited[to] = true;
				if(++cnt==V-1) break;
//				System.out.println(to);
				for (Edge e: edges[to]) {
					if(visited[e.to]) continue;
					q.add(e);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(cost).append('\n');
		}
		System.out.println(sb.toString());
	}
}
