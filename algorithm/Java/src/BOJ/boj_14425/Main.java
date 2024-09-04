package BOJ.boj_14425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

// 백준 14425 문자열 집합
// 이중혁
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		HashSet<String> set = new HashSet<>();
		
		for (int i = 0; i < N; i++)
			set.add(br.readLine());
		
		int res = 0;
		for(int i=0; i<M; i++)
			if(set.contains(br.readLine())) res ++;
		
		System.out.println(res);
	}

}
