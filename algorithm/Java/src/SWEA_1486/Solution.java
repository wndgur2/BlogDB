package SWEA_1486;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
//    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException{
//        int T = Integer.parseInt(br.readLine());
        int T = sc.nextInt();

        for(int test_case = 1; test_case<=T; test_case++){
//            String[] NB = br.readLine().split(" ");
//            int N = Integer.parseInt(NB[0]);
//            int B = Integer.parseInt(NB[1]);
            int N = sc.nextInt();
            int B = sc.nextInt();
            int[] heights = new int[N];
//            for(String height:br.readLine().split(" ")){
//                heights[i] = Integer.parseInt(height);
//                i++;
//            }
            for (int i = 0; i < N; i++) {
                heights[i] = sc.nextInt();
            }
            sort(heights);
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += heights[i];
                if(sum>=B) break;
            }

            int nextSum = sum;
            for (int i = 0; i < N; i++) {
                nextSum -= heights[i];
                if(nextSum<B) break;
                sum = nextSum;
            }
            int res = sum-B;
            if(res < 0) res *= -1;
            System.out.println("#" + test_case + " " + res);
//            bw.write("#" + test_case + " " + res);
//            bw.flush();
//            bw.close();
//            br.close();
        }
    }

    public static void sort(int[] arr){
        int tmp;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length-1; j++) {
                if(arr[j] > arr[j+1]){
                    tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }
        }
    }
}
