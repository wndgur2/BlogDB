package BOJ.boj_23840;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge{
		int to;
		long weight;
		public Edge(int to, long weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
		
	}
	
	static class Vertex implements Comparable<Vertex>{
		int idx, flag;
		long weight;
		public Vertex(int idx, long weight, int flag) {
			super();
			this.idx = idx;
			this.weight = weight;
			this.flag = flag;
		}
		
		@Override
		public int compareTo(Vertex o) {
			return Long.compare(weight, o.weight);
		}
	}
	
	static int P, X, Z;
	static int[] ps;
	static int[] visited;
	static long[] distances;
	static ArrayList<Edge>[] edges;
	static long res;
	
	public static void main(String[] args) throws IOException {
		// 다익스트라 x 101 + 100P3
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			long weight = Integer.parseInt(st.nextToken());
			
			edges[from].add(new Edge(to, weight));
			edges[to].add(new Edge(from, weight));
		}
		
		st = new StringTokenizer(br.readLine());
		X = Integer.parseInt(st.nextToken())-1;
		Z = Integer.parseInt(st.nextToken())-1;
		
		P = Integer.parseInt(br.readLine());
		ps = new int[P];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < P; i++)
			ps[i] = Integer.parseInt(st.nextToken())-1;
		
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		q.add(new Vertex(X, 0, 0));
		
		while(!q.isEmpty()) {
			Vertex cur = q.poll();
			if(visited[cur.idx] == cur.flag) continue;
			// visited flag가 다를 때 어떻게 ..?
			
			// 2^20= 100만
			// 100만 x 20개? 20개만 해도 되나?
			
			for(Edge next: edges[cur.idx]) {
				if(next.to==Z && cur.flag != (1<<P)-1) continue;
			}
		}
	}
}
