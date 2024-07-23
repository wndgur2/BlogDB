package boj_1931;
import java.io.*;
import java.util.Scanner;
class Test{
    // static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args)throws IOException{
        // int a = Integer.parseInt(br.readLine());
        // int b = Integer.parseInt(br.readLine());
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(a>b);
    }
}