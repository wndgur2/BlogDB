package SWEA.swea_1251;
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 메모리 102,952 kb	실행시간 530 ms

public class Solution {
     
    static class Edge implements Comparable<Edge>{
        int to;
        double cost;
 
        public Edge(int to, double cost) {
            super();
            this.to = to;
            this.cost = cost;
        }
         
        @Override
        public int compareTo(Edge o) {
            return Double.compare(cost, o.cost);
        }
    }
     
    static double getDistance(int y1, int x1, int y2, int x2) {
        return Math.sqrt(Math.pow(y1-y2, 2) + Math.pow(x1-x2, 2));
    }
     
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int N;
        double dist, rate, res;
        int[] ys, xs;
        ArrayList<Edge>[] edges;
        boolean[] visited;
         
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            ys = new int[N];
            xs = new int[N];
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                ys[i] = Integer.parseInt(st.nextToken());
            }
             
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                xs[i] = Integer.parseInt(st.nextToken());
            }
             
            rate = Double.parseDouble(br.readLine());
             
            edges = new ArrayList[N];
            for (int i = 0; i < N; i++)
                edges[i] = new ArrayList<>();
             
            // 모든 엣지 구하기 O(N^2)
            for (int i = 0; i < N-1; i++) {
                for (int j = i+1; j < N; j++) {
                    // i와 j를 연결하는 간선
                    dist = getDistance(ys[i], xs[i], ys[j], xs[j]);
                    edges[i].add(new Edge(j, dist));
                    edges[j].add(new Edge(i, dist));
                }
            }
             
            res = 0;
            visited = new boolean[N];
             
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            for(Edge e: edges[0]) queue.add(e);
            visited[0] = true;
            // BFS
            Edge edge;
            int cnt = 0;
            while(!queue.isEmpty()) {
                // 연결할 엣지
                edge = queue.poll();
                if(visited[edge.to]) continue;
                visited[edge.to]= true; 
                res+=rate*edge.cost*edge.cost;
                if(++cnt==N-1) break;
//              System.out.println("add " + edge.cost);
                for(Edge e: edges[edge.to]) {
                    if(visited[e.to]) continue;
                    queue.add(e);
                }
            }
//          System.out.println("COST: " + res);
            res = Math.round(res);
            String result = String.format("%.0f", res);
//          sb.append('#').append(tc).append(' ').append(res).append('\n');
            sb.append('#').append(tc).append(' ').append(result).append('\n');
        }
        System.out.println(sb.toString());
    }
}