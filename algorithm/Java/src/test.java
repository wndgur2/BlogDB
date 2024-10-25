import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static class P{
        String name;
        P(String name){
            this.name = name;
        }
    }
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        String a = new String("abc");

        System.out.println("abc"==st.nextToken());
    }
}
