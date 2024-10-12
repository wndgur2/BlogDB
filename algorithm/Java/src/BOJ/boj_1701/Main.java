package BOJ.boj_1701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
	abcdabcabb
	
	최대중복문자열의 길이
	
	KMP에서 부분 일치 테이블 구하기
	
	XX -> 접두사가 아닌 모든 부분 문자열을 고려해야함
	반례) aabbbcccc -> 1 (expected 3)
	cbezvjlugc
	 cbezvjlugc
*/

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		char[] str = br.readLine().toCharArray();
		int n = str.length;
		int res = 0;
		for (int k = 0; k < n-1; k++) { // 비교 패턴 문자열 시작 인덱스
			int j = k, i=k+1;
			int[] partMatchLen = new int[n];
			Arrays.fill(partMatchLen, k);
			while(i<n){
//				System.out.println("K: " + k + ", i, j = " + i + " " + j);
//				System.out.println(Arrays.toString(partMatchLen));
				if(j>=n) break;
				if(str[i]==str[j]) {
					partMatchLen[i++] = ++j;
					if(j-k> res) res = j-k;
				} else {
					if(j>k) j=partMatchLen[j-1];
					else {
						j = k;
						i++;
					}
					continue;
				}
			}
		}
			
		System.out.println(res);
	}

}
