package SWEA.swea_3282;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class Solution {
	static int N, K;
	static StringTokenizer st;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			knapsack(tc);
		}
	}
	
	public static void knapsack(int tc) throws IOException{
		int[][] stuff = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			stuff[i][0] = w;
			stuff[i][1] = v;
		}
		
		// 보석 도둑과 다른 점: 가방이 하나다.
		
		// int[] dp =new int[K+1]; // dp[i]: i의 무게로 담을 수 있는 최대 가치
		HashMap<Integer, Integer> weightValue = new HashMap<>();
		int maxV = 0;
		for (int i = 0; i < N; i++) {
			int w = stuff[i][0];
			int v = stuff[i][1];
			
			ArrayList<int[]> newEntries = new ArrayList<>();
			
			for(Entry<Integer, Integer> wv : weightValue.entrySet()) {
				int newV = v+wv.getValue();
				int newW = w+wv.getKey();
				
				if(K >= newW && (!weightValue.containsKey(newW) || weightValue.get(newW) < newV)) {
					newEntries.add(new int[] {newW, newV});
					if(newV > maxV) maxV = newV;
				}
			}
			
			for(int[] entry: newEntries) {
				weightValue.put(entry[0], entry[1]);
			}

			if(K >= w && (!weightValue.containsKey(w) || weightValue.get(w) < v)) {
				weightValue.put(w, v);
				if(v > maxV) maxV = v;
			}
		}
		System.out.println("#" + tc + " " + maxV);
	}
}
