package SWEA.swea_1238;

// 메모리 19,340 kb 	실행시간 115 ms

import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, start;
	static ArrayList<Integer>[] edges;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		int T = 10;
		StringTokenizer st;
		
		int from, to;
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken())-1;
			
			edges = new ArrayList[100];
			visited = new boolean[100];
			
			for(int i=0; i<100; i++)
				edges[i] = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N/2; i++) {
				from = Integer.parseInt(st.nextToken())-1;
				to = Integer.parseInt(st.nextToken())-1;
				
				edges[from].add(to);
			}
			
			ArrayDeque<int[]> q = new ArrayDeque<>();
			q.add(new int[]{start, 0});
			
			int lastDepth = 0;
			int biggestNode = -1;
			
			while(!q.isEmpty()) {
				int[] nodeAndDepth = q.pollFirst();
				int node = nodeAndDepth[0];
				int depth = nodeAndDepth[1];
				
				if(depth > lastDepth) {
					lastDepth = depth;
					biggestNode = node;
				} else if(depth==lastDepth) {
					if(node > biggestNode) biggestNode = node;
				}
				
				for(int next: edges[node]) {
					if(visited[next]) continue;
					visited[next] = true;
					q.add(new int[] {next, depth+1});
				}
			}
			
			System.out.println("#" + tc + " " + (biggestNode+1));
		}
	}

}
