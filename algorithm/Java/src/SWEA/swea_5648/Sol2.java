package SWEA.swea_5648;
import java.io.*;
import java.util.*;

public class Sol2 {
    static int N, res;
    static int[][] atom;
    static int[][][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        map = new int[4001][4001][2];
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            atom = new int[N][5];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                atom[i][0] = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                atom[i][1] = (Integer.parseInt(st.nextToken()) + 1000) * 2;
                atom[i][2] = Integer.parseInt(st.nextToken());
                atom[i][3] = Integer.parseInt(st.nextToken());
            }
            res = 0;
            simulation(t-1);
            sb.append("#").append(t).append(" ").append(res).append("\n");
        }
        System.out.println(sb);
    }

    static void simulation(int t) {
        for (int i = t*4000+1; i <= t*4000+0; i++) {
            for (int j = 0; j < N; j++) {
                if(atom[j][4] != 0) continue;
                atom[j][0] += dx[atom[j][2]];
                atom[j][1] += dy[atom[j][2]];
                if(atom[j][0] < 0 || atom[j][1] < 0 || atom[j][0] > 4000 || atom[j][1] > 4000) continue;
                if (map[atom[j][0]][atom[j][1]][1] == i) {
                    atom[map[atom[j][0]][atom[j][1]][0]][4] = 2;
                    atom[j][4] = 2;
                } else {
                    map[atom[j][0]][atom[j][1]][0] = j;
                    map[atom[j][0]][atom[j][1]][1] = i;
                }
            }
            for (int j = 0; j < N; j++) {
                if(atom[j][4] == 2) {
                    atom[j][4] = 1;
                    res += atom[j][3];
                }
            }
        }
    }
}