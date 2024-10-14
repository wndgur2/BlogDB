package BOJ.boj_17103;
import java.io.*;
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static final int MAX_SIZE = 1000000;
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		boolean[] isNotPrime = new boolean[MAX_SIZE + 1];
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		for(int i=2; i<=MAX_SIZE; ++i) {
			if(isNotPrime[i]) continue;
			for(int j=i*2; j<=MAX_SIZE; j+=i) {
				isNotPrime[j] = true;
			}
		}
		
		for(int i=0; i<T; ++i) {
			int N = Integer.parseInt(br.readLine());
			int res = 0;
			for(int j = 2; j<=N/2; j++) {
				if(!isNotPrime[j] && !isNotPrime[N-j]) {
					res ++;
				}
			}
			sb.append(res).append('\n');
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
