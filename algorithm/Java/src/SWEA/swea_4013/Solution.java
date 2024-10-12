package SWEA.swea_4013;

import java.io.*;
import java.util.*;

/*

메모리
19,396 kb
실행시간
108 ms

1
2
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1
1 1
3 -1
 */

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] wheels;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(br.readLine());
			int[][] commands = new int[K][2];
			wheels = new int[4][8];
			
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					wheels[i][j] = Integer.parseInt(st.nextToken());
				} // 빨간화살표부터
			}
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int wheelIdx = Integer.parseInt(st.nextToken())-1;
				int direction = Integer.parseInt(st.nextToken());
				commands[i] = new int[]{wheelIdx, direction};
			}
			
			// 1 2 4 8
			int res = getRes(K, commands);
			System.out.println("#"+tc+" "+res);
		}
	}
	private static int getRes(int k, int[][] commands) {
		for (int i = 0; i < k; i++) {
//			System.out.println("COMMAND: " + i);
			int wheel = commands[i][0];
			int dir = commands[i][1];
			int visited = 1<<wheel;
			
			rotate(wheel, dir, visited);
		}
		
		int res = 0;
		for (int i = 0; i < 4; i++) 
			if(wheels[i][0] == 1)
				res += 1 << i;
		return res;
	}
	private static void rotate(final int cur, int dir, int visited) {
//		System.out.println("CUR: " + cur);
		
		// 연쇄작용
		if(cur>0 && isMatching(cur-1, cur) && (visited&(1<<(cur-1)))==0)
			rotate(cur-1, dir*-1, visited|(1<<(cur-1)));
		if((cur<3) && isMatching(cur, cur+1) && (visited&(1<<(cur+1)))==0)
			rotate(cur+1, dir*-1, visited|(1<<(cur+1)));

		// rotate cur
		int[] newWheels = new int[8];
		// 시계방향
		if(dir==1) for (int i = 0; i < 8; i++)
			newWheels[i] = wheels[cur][(i+8-1)%8];
		else for (int i = 0; i < 8; i++) 
			newWheels[i] = wheels[cur][(i+1)%8];
		
		wheels[cur] = newWheels;
	}
	private static boolean isMatching(int i, int j) {
		if(wheels[i][2] != wheels[j][6]) return true;
		return false;
	}

}
