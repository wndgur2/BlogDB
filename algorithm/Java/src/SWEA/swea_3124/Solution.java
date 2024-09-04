package SWEA.swea_3124;

// 메모리 114,408 kb		실행시간 2,211 ms

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] parents;
	
	static void make() {
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int r1 = find(a);
		int r2 = find(b);
		if(r1==r2) return false;
		parents[r2] = r1;
		return true;
	}
	
	static class Edge implements Comparable<Edge>{
		int from, to, w;
		
		public Edge(int from, int to, int w) {
			this.from = from;
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
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int V, E, from, to, w, cnt;
		long cost;
		Edge[] edges;
		
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parents = new int[V];
			edges = new Edge[E];
			
			make();
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				edges[i] = new Edge(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
			}
			
			Arrays.sort(edges);
			
			cost = 0;
			cnt = 0;
			for (int i = 0; i < E; i++) {
				from = edges[i].from;
				to = edges[i].to;
				w = edges[i].w;
				if(union(from, to)) {
					cost += (long)w;
					if(++cnt == V-1) break;
				}
			}
			sb.append('#').append(tc).append(' ').append(cost).append('\n');
		}
		System.out.println(sb.toString());
	}

}
