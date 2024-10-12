package BOJ.boj_9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

ACAYKP
CAPCAK

4

 */
public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] stringA = br.readLine().toCharArray();
		char[] stringB = br.readLine().toCharArray();
		
		int[][] dp = new int[stringA.length+1][stringB.length+1];
		for (int i = 1; i < stringA.length+1; i++) {
			for (int j = 1; j < stringB.length+1; j++) {
				if(stringA[i] == stringB[j]) {
					
				}
			}
		}
	}

}
