package SWEA.swea_5653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Test {
    static class Cell{
        int y, x, life, activation, deactivation;

        public Cell(int y, int x, int life, int activation, int deactivation) {
            this.y = y;
            this.x = x;
            this.life = life;
            this.activation = activation;
            this.deactivation = deactivation;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return y == cell.y && x == cell.x && life == cell.life && activation == cell.activation && deactivation == cell.deactivation;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x, life, activation, deactivation);
        }

        @Override
        public String toString() {
            return "Cell{" +
                    "y=" + y +
                    ", x=" + x +
                    ", life=" + life +
                    ", activation=" + activation +
                    ", deactivation=" + deactivation +
                    '}' + "\n";
        }
    }
    static int T, N, M, K;
    static int[][] visited;
    static int[][] map;
    static Queue<Cell> queue;
    static Queue<Cell> newQueue;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb;

        T = Integer.parseInt(st.nextToken());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder();
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            queue = new ArrayDeque<>();
            visited = new int[410][410];
            map = new int[410][410];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i + 410/2][j + 410/2] = Integer.parseInt(st.nextToken());
                    if(map[i + 410/2][j + 410/2] == 0) continue;
                    visited[i + 410/2][j + 410/2] = 1;
                    queue.offer(new Cell(i + 410/2, j + 410/2, map[i + 410/2][j + 410/2], 0, map[i + 410/2][j + 410/2]));
                }
            }

            bfs();
            sb.append("#").append(t).append(" ").append(queue.size());
            System.out.println(sb.toString());
        }
    }
    static void bfs(){
        int cnt = 1;
        while (!queue.isEmpty()){
            if(cnt == K + 1){
                break;
            }
            int size = queue.size();
            newQueue = new ArrayDeque<>();
            for (int i = 0; i < size; i++) {
                Cell cell = queue.poll();
                if(cell.deactivation > 0){
                    cell.deactivation--;
                    if(cell.deactivation == 0)
                        cell.activation = cell.life;
                }else if(cell.deactivation == 0){
                    if(cell.activation == cell.life){
                        for (int j = 0; j < 4; j++) {
                            int ny = cell.y + dy[j];
                            int nx = cell.x + dx[j];
                            if(map[ny][nx] == 0){
                                map[ny][nx] = cell.life;
                                visited[ny][nx] = cnt;
                                newQueue.offer(new Cell(ny, nx, cell.life, 0, cell.life));
                            } else {
                            	// no need
                            }
                        }
                    }
                    cell.activation--;
                }
                if(cell.deactivation == 0 && cell.activation == 0) continue;
                queue.offer(cell);
            }
//            System.out.println("------------");
//            System.out.println("old queue");
//            System.out.println(queue);
//            System.out.println("------------");
//            System.out.println("new queue");
//            System.out.println(newQueue);
//            System.out.println("------------");
            queue.addAll(newQueue);
            ++cnt;
        }
    }
}