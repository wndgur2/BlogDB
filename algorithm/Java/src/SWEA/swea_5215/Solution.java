package SWEA.swea_5215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] tastes, kcals;
	static int maxTaste;
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int calLimit = Integer.parseInt(st.nextToken());

			tastes = new int[N];
			kcals = new int[N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				tastes[i] = Integer.parseInt(st.nextToken());
				kcals[i] = Integer.parseInt(st.nextToken());
			}
			maxTaste = 0;

//			System.out.println(Arrays.toString(tastes));
//			System.out.println(Arrays.toString(kcals));
			
			int taste=0, kcal=0;
			for(int visit=0; visit<(1<<N); visit++) {
				taste = 0;
				kcal = 0;
				for(int j=0; j<N; j++) {
					if((visit & (1<<(N-1-j))) == 0) continue;
					taste += tastes[j];
					kcal += kcals[j];
				}
				if((kcal <= calLimit) && (taste > maxTaste)) maxTaste = taste;
			}
			System.out.println("#" + tc + " " + maxTaste);
		}
	}
}
