package BOJ.boj_1269;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 이중혁
// 백준 1269 대칭 차집합
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nA = Integer.parseInt(st.nextToken());
		int nB = Integer.parseInt(st.nextToken());

		HashSet<Integer> A = new HashSet<>();
		HashSet<Integer> B = new HashSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nA; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nB; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}
		
		int res = 0;

		for (int a:A) {
			if(!B.contains(a))res++;
		}
		for (int b:B) {
			if(!A.contains(b))res++;
		}
		
		System.out.println(res);
	}

}
