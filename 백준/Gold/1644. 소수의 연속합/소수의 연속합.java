import java.io.*;

public class Main {
    static final int MAX_N = 4000000;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
    static StringBuilder sb = new StringBuilder();
    static boolean[] isNotPrime;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        isNotPrime = getNotPrimes(N);
        int stN = 2;
        int endN = 2;
        int curSum = 2;
        int res = 0;
        while(endN<=N && stN<=endN){
            if(curSum == N) {
                res++;
                endN = getNextPrime(endN, N);
                curSum += endN;
            } else if(curSum < N){
                endN = getNextPrime(endN, N);
                curSum += endN;
            } else if(curSum > N){
                curSum -= stN;
                stN = getNextPrime(stN, N);
            }
        }
        sb.append(res).append('\n');
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean[] getNotPrimes(int N){
        boolean[] isNotPrime_ = new boolean[N+1];
        isNotPrime_[0] = true;
        isNotPrime_[1] = true;
        for(int i=2; i<=N; i++){
            if(isNotPrime_[i]) continue;
            for(int j=i*2; j<=N; j+=i){
                isNotPrime_[j] = true;
            }
        }

        return isNotPrime_;
    }

    static int getNextPrime(int i, int N){
        for(i++; i<=N; i++){
            if(!isNotPrime[i]) return i;
        }
        return MAX_N+1;
    }
}