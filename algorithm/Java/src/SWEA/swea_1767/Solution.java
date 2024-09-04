package SWEA.swea_1767;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
 
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, R, res, maxConnectedCoreN;
	static int[][] board;
	static ArrayList<int[]> processors = new ArrayList<>();
	static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static boolean isConnectable(int depth, int[] dir) {
		int y = processors.get(depth)[0];
		int x = processors.get(depth)[1];
		
		do {
			y=y+dir[0];
			x=x+dir[1];
			if(y<0||x<0||y>=N||x>=N) break;
			if(board[y][x] == 1) return false;
			if(board[y][x] == -1) return false;
		} while(board[y][x] == 0);
		
		return true;
	}
	
	public static int connect(int depth, int[] dir) {
		int usedWireLength = 0;

		int y = processors.get(depth)[0];
		int x = processors.get(depth)[1];
		
		do {
			y=y+dir[0];
			x=x+dir[1];
			if(y<0||x<0||y>=N||x>=N) break;
			board[y][x] = -1;
			usedWireLength++;
		} while(true);
		
		return usedWireLength;
	}
	
	public static void disconnect(int depth, int[] dir) {
		int y = processors.get(depth)[0];
		int x = processors.get(depth)[1];
		
		do {
			y=y+dir[0];
			x=x+dir[1];
			if(y<0||x<0||y>=N||x>=N) break;
			board[y][x] = 0;
		} while(true);
	}
	
	public static void dfs(int depth, int connectedCoreN, int wireLength) {
		if(connectedCoreN + R - depth < maxConnectedCoreN) return;
		
		// 모든 프로세서를 결정함.
		if(depth == R) {
			if(connectedCoreN > maxConnectedCoreN) {
				res = wireLength;
				maxConnectedCoreN = connectedCoreN;
			} else if(connectedCoreN == maxConnectedCoreN) {
				if(wireLength < res) res = wireLength;
			}
			return;
		}
		
		// 연결하지 않음.
		dfs(depth+1, connectedCoreN, wireLength);
		
		// 상하좌우
		boolean is;
		for (int[] dir: dirs) {
			if(is=isConnectable(depth, dir)) {
				int wire = connect(depth, dir);
				dfs(depth+1, connectedCoreN+1, wireLength+wire);
				disconnect(depth, dir);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			processors.clear();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1) {
						// 프로세서 추가.
						processors.add(new int[] {i, j});
					}
				}
			}
			
			// 어떤 프로세서를 어디에 연결할 것인가? DFS(부분집합)
			res=0;
			maxConnectedCoreN=0;
			R = processors.size();
			dfs(0, 0, 0);
			sb.append("#").append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb.toString());
	}
}