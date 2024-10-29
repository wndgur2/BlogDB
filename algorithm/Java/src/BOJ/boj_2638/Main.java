package BOJ.boj_2638;

/*

N(H) M(W) <= 100

8 9
0 0 0 0 0 0 0 0 0
0 0 0 1 1 0 0 0 0
0 0 0 1 1 0 1 1 0
0 0 1 1 1 1 1 1 0
0 0 1 1 1 1 1 0 0
0 0 1 1 0 1 1 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0

> 4

flood fill 반복?
flood fill 1회
melt -> 주변 melt++
if(melt>=2) arrayList에 추가 반복
-> 얼마나 짧을까
-> 최대 10000번 수행. 훨씬 짧다.

-> 공간이 뚫릴 때, 어떻게 할지.
-> 녹는 치즈 주변에 2가 있고 0이 있으면 floodFill

*/

import java.io.*;
import java.util.*;

public class Main{
    static int H, W;
    static int[][] map;
    static int[][] air;
    static int cheese;
    static ArrayDeque<int[]> toMelt;
    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        air = new int[H][W];
        cheese = 0;

        toMelt = new ArrayDeque<>();
        ArrayDeque<int[]> temp = new ArrayDeque<>();

        int ny, nx;
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1) cheese ++;
            }
        }

        map[0][0] = 2;
        floodFill(0, 0, toMelt);

        int res = 0;
        while(cheese>0){
            ++res;
            cheese -= toMelt.size();
            // System.out.println("left cheese: " + cheese);
            // printMap();

            temp = new ArrayDeque<>();
            while(!toMelt.isEmpty()){
                int[] yx = toMelt.poll();
                // System.out.println("Y: " + yx[0] + " ,  X: " + yx[1]);
                if(map[yx[0]][yx[1]] != 1) continue;
                map[yx[0]][yx[1]] = 2;
                floodFill(yx[0], yx[1], temp); // temp에 다음 녹을 치즈를 넣음
            }
            toMelt = temp;
        }

        System.out.println(res);
    }

    static public void printMap(){
        for(int i=0; i<H; i++) System.out.println(Arrays.toString(map[i]));
        System.out.println();
    }

    static public void floodFill(int y, int x, ArrayDeque<int[]> temp){
        int ny, nx;
        for(int[] dir: dirs){
            ny = y+dir[0];
            nx = x+dir[1];
            if(ny<0||nx<0||ny>=H||nx>=W) continue;
            if(map[ny][nx]==1){
                if(++air[ny][nx]==2)
                    temp.add(new int[]{ny, nx});
            } else if(map[ny][nx] == 0){
                map[ny][nx] = 2;
                floodFill(ny, nx, temp);
            }
        }
    }

}