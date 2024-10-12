package SWEA.swea_1225;

/*
 *	이중혁
 *	암호 생성기  
 *
 *	메모리
 *	19,476 kb
 *	실행시간
 *	111 ms
 */

import java.io.*;
import java.util.*;

public class Solution {
	static final int SIZE = 8;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] numbers;
	static int[] divisions;
	public static void main(String[] args) throws IOException {
		int T = 10;
		for(int tc=1; tc<=T; tc++) {
			br.readLine();
			numbers = new int[SIZE];
			divisions = new int[SIZE];
			
			/**
			 * 1. 각 수를 배열에 저장.
			 * 2. while true:
			 * 3. index = 0
			 * 4. for i 1~5
			 * 5. 	arr[index] -= i;
			 * 6. 	arr[index] < 0이면 =0, break; 
			 * 7. index 다음 수부터 그 수까지 출력.
			 * 
			 * 브루트포스: 최대 5억 ..?
			 * --> 15로 나눈 가장 작은 값 저장.
			 * --> 그 값으로 모든 수를 나눠준 후 브루트포스 시작.
			 */
			
			int minDivision = 1000000000; // 최대 21억/8일 것임
			int lastIdx = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<SIZE; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
				divisions[i] = numbers[i]/15;
				if(divisions[i]<minDivision) {
					minDivision = divisions[i];
				}
			}
			for(int i=0; i<SIZE; i++) {
				numbers[i] -= (15) * (minDivision-1); // 수가 남도록 
			}
			int curIdx = 0;
			int curAmount = 0;
			while(true) {
				numbers[curIdx] -= curAmount+1;
				if(numbers[curIdx] <= 0) {
					numbers[curIdx] = 0;
					lastIdx = curIdx;
					break;
				}
				curIdx = (curIdx+1)%8;
				curAmount = (curAmount+1)%5;
			}
			
			sb.append("#").append(tc).append(' ');
			for(int i=lastIdx+1; i<lastIdx+8; i++) {
				sb.append(numbers[i%8]).append(" ");
			}
			sb.append(numbers[lastIdx]).append('\n');
		}
		System.out.println(sb.toString());
	}

}
