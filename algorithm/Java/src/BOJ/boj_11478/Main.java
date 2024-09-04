package BOJ.boj_11478;

import java.io.*;
import java.util.HashSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String word;
	static HashSet<String> subSet;
	static int N;
	
	public static void main(String[] args) throws IOException{
		word = br.readLine();
		N = word.length();
		subSet = new HashSet<>();

		for(int left=0; left<N; left++) {
			for(int right=left; right<=N; right++)
				subSet.add(word.substring(left, right));
		}
		subSet.remove("");
		
		System.out.println(subSet.size());
	}
}
