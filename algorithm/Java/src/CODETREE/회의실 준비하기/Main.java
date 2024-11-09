import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<int[]> meetings;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        meetings = new ArrayList<>();
        int from, to;
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
        }

        System.out.println(meetings);
        meetings.sort((a, b)->{
            if(a[0]==b[0]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[0], b[0]);
        });
        System.out.println(meetings);
    }
}