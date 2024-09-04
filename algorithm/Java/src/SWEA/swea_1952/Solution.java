package SWEA.swea_1952;

/**
 	이중혁
 	
	메모리
	18,672 kb
	실행시간
	111 ms
 */

import java.io.*;
import java.util.*;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int Day, Month, ThreeMonth, Year, res;
	static int[] plan;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		plan = new int[12];
		for (int tc = 1; tc <= T; tc++) {
			res = 2100000000;
			st = new StringTokenizer(br.readLine());
			Day = Integer.parseInt(st.nextToken());
			Month = Integer.parseInt(st.nextToken());
			ThreeMonth = Integer.parseInt(st.nextToken());
			Year = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 12; i++) 
				plan[i] = Integer.parseInt(st.nextToken());
			dfs(0, 0);
			System.out.println("#" + tc + " " + res);
		}
	}
	
	public static void dfs(int month, int cost) {
		if(cost >= res) return;
		if(month >= 12) {
			if(cost < res) res = cost;
			return;
		}

		dfs(month+1, cost+ Month);
		dfs(month+1, cost+ Day*plan[month]);
		dfs(month+3, cost+ ThreeMonth);
		dfs(month+12, cost+ Year);
	}
}
