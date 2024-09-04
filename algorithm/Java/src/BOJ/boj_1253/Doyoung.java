package BOJ.boj_1253;
import java.io.*;
import java.util.*;

/*
 * 시간 : 244 ms, 메모리 : 49184 kb
 */

public class Doyoung {
	
	static int[] nums;
	static int sum, N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		nums = new int[N];
		HashMap<Integer, Integer> hm = new HashMap<>();
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			hm.put(nums[i],hm.getOrDefault(nums[i], 0)+1);
		}
		Arrays.sort(nums);
		int goodCnt = 0;
		for(int i=0;i<N-1;i++) {
			for(int j=i+1; j<N; j++) { // i != j
				sum = nums[i] + nums[j];
				if(hm.getOrDefault(sum, 0) == 0) continue;
				int cnt = hm.get(sum);
				int offset = 0;
				

				if(sum==nums[i] && sum==nums[j]) {
					// 0 0 0
					if(cnt<=2) {
						continue;
					}
				} else if(sum == nums[i] || sum == nums[j]) {
					// 0 1 1
					if(cnt <= 1){
						continue;
					}
				}
				
				goodCnt += hm.get(sum) + offset;
				hm.put(sum, 0);
			}
		}
		System.out.println(goodCnt);
	}
}