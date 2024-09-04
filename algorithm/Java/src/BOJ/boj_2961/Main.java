package BOJ.boj_2961;

import java.io.*;
import java.util.StringTokenizer;
/**
 * 
 * @author 이중혁
 * 
 * 백준 2961_도영이가 만든 맛있는 음식
 * 
 * 메모리		시간
 * 11508kb	64ms
 *
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] acids;
	static int[] bitters;
	static int res = 1000000000;
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		acids = new int[N];
		bitters = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			acids[i] = Integer.parseInt(st.nextToken());
			bitters[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int flag = 0; flag < 1<<N; flag++) {
			int acidity = 1;
			int bitter = 0;
			for (int j = 0; j < N; j++) {
				if((flag & (1<<j)) == 0) continue;
				acidity *= acids[j];
				bitter += bitters[j];
			}
			if(acidity==1 && bitter==0) continue;
//			System.out.println(acidity + " " + bitter);
			int newRes = Math.abs(acidity - bitter);
			if(newRes < res) res = newRes; 
		}
		System.out.println(res);
	}
}
