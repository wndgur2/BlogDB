package BOJ.boj_27172;

// 12:45
// 13:26
/**
 * N < 100000
 * x < 1000000
 */
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        String[] cardsString = br.readLine().split(" ");
        int[] cards = new int[N];
        int i=0, maxN=0;
        for(String cardString: cardsString){
            cards[i++] = Integer.parseInt(cardString);
            if(cards[i-1] > maxN) maxN = cards[i-1];
        }

        int[] scores = new int[maxN+1];
        boolean[] isPresent = new boolean[maxN+1];
        for(int card:cards){
            isPresent[card] = true;
        }

        for(i=0; i<N; i++){
            for(int j=cards[i]*2; j<=maxN; j+=cards[i]){
                // j가 cards에 있으면
                if(isPresent[j]){
                    scores[j]--;
                    scores[cards[i]]++;
                }
                // System.out.println(Arrays.toString(scores));
            }
        }

        for(int card:cards){
            sb.append(scores[card]).append(' ');
        }
        bw.write(sb.toString() + '\n');
        bw.flush();
        bw.close();
        br.close();
    }
}
