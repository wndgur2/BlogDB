package SWEA_1208;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader bufferedReader;

    static ArrayList<Integer> boxHeights;
    static int minIndex;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./input.txt"));
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<10; ++i){
            System.out.print("#" + (i + 1) + ' ');
            solve();
        }
    }

    static void solve() throws IOException{
        int maxDumpN = Integer.parseInt(bufferedReader.readLine());
//        System.out.println(maxDumpN);
        StringTokenizer boxHeightsTokens = new StringTokenizer(bufferedReader.readLine());

        boxHeights = new ArrayList<>();
        while(boxHeightsTokens.hasMoreTokens()){
            int height = Integer.parseInt(boxHeightsTokens.nextToken());
//            System.out.print(Integer.toString(height) + ' ');
            boxHeights.add(height);
        }
//        System.out.println();
        boxHeights.sort((o1, o2) -> o1>o2?-1: o1.equals(o2)? 0:1);
        minIndex = boxHeights.size()-1;

        int dumpedN = 0;
        int index = 0;
        int curHeight = boxHeights.get(0);
        while(dumpedN < maxDumpN && index < minIndex && boxHeights.get(index) > boxHeights.get(minIndex)){
            if(boxHeights.get(index) == curHeight){
                dump(index);
                dumpedN++;
                index++;
            } else{
                index = 0;
                curHeight = boxHeights.get(0);
            }
        }

        System.out.println(boxHeights.get(index)-boxHeights.get(minIndex));
    }

    static void dump(int index){
        boxHeights.set(index, boxHeights.get(index)-1);
        int minHeight = boxHeights.get(minIndex);
        boxHeights.set(minIndex, boxHeights.get(minIndex)+1);
        minIndex--;
        if(boxHeights.get(minIndex) != minHeight){
            minIndex = boxHeights.size()-1;
        }
    }
}
