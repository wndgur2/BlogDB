package BOJ.boj_1786;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

408ms
100692kb

 */

public class Main {
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		
		kmp(T, P);
		System.out.println(sb.toString());
	}
	
	private static void kmp(char[] origin, char[] pattern) {
		int[] pi = getPI(pattern);
		System.out.println(Arrays.toString(pi));
		int n = origin.length;
		int plen = pattern.length;
		int resN = 0;
		List<Integer> idxList = new ArrayList<>();
		
		int j=0;
		for (int i = 0; i < n; i++) {
			while(j>0 && origin[i] != pattern[j]) {
				j=pi[j-1];
			}
			if(origin[i] == pattern[j]) {
				if(++j==plen) {
					resN++;
					idxList.add(i-plen+2);
					j=pi[j-1];
				}
			}
		}
		
		sb.append(resN).append('\n');
		for(int idx: idxList) {
			sb.append(idx + " ");
		}
		sb.append('\n');
	}

	public static int[] getPI(char[] pattern) {
		int n = pattern.length;
		int[] pi = new int[n];
		
		int j=0;
		for (int i = 1; i < n; i++) {
			while(j>0 && pattern[i] != pattern[j]) {
				j=pi[j-1];
			}
			if(pattern[i] == pattern[j]) {
				pi[i] = ++j;
			}
		}
		
		return pi;
	}

}
