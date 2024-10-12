package BOJ.boj_13460;
import java.io.*;
import java.util.StringTokenizer;
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static char[][] board;
	static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int res = 11;
	
	static class Position {
		public char value;
		public int y;
		public int x;
		public boolean alive = true;

		public Position(char value, int y, int x){
			this.value = value;
			this.y = y;
			this.x = x;
		}
		
		public void move(int[] dir) {
			y += dir[0];
			x += dir[1];
		}
		
		public boolean checkWall(int[] dir) {
			return board[y+dir[0]][x+dir[1]]=='#';
		}
		
		public boolean equals(Position pos) {
			return x==pos.x && y==pos.y;
		}
		
		public Position copy() {
			return new Position(value, y, x);
		}
		
		public char value() {
			return board[y][x];
		}
		
		public char frontValue(int[] dir) {
			return board[y+dir[0]][x+dir[1]];
		}
		
		public String toString() {
			return "( " + y + " " + x + " )";
		}
	}
	
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		Position red=null, blue=null;
		
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			for(int j=0; j<M; j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j]=='R') {
					red = new Position('R', i, j);
				} else if(board[i][j]=='B') {
					blue = new Position('B', i, j);
				}
			}
		}
		
		dfs(red, blue, 1, new int[] {});
		System.out.println(res==11?-1:res);
	}
	
	static void printBoard() {
		for(char[] row: board) {
			for(char cell: row) {
				System.out.print(cell);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static void move(Position bead, int[] dir) {
		if(!bead.alive) return;
		board[bead.y][bead.x]= '.';
		step(bead, dir);
		if(bead.alive)
			board[bead.y][bead.x] = bead.value;
	}
	
	static void step(Position bead, int[] dir) {
		char front = bead.frontValue(dir);
		if(front == 'O') {
			bead.alive = false;
		}else if(front == '.') {
			bead.y += dir[0];
			bead.x += dir[1];
			step(bead, dir);
		}
	}
	
	static void dfs(Position red, Position blue, int depth, int[] preDir) {
//		printBoard();
		if(depth >= res) return;
		
		for(int[] dir: dirs) {
			if(dir==preDir) continue;
			Position newRed = red.copy();
			Position newBlue = blue.copy();

			move(newRed, dir);
			move(newBlue, dir);
			move(newRed, dir);
			
			if(newBlue.alive && newRed.alive)
				dfs(newRed, newBlue, depth+1, dir);
			
			board[newRed.y][newRed.x] = '.';
			board[newBlue.y][newBlue.x] = '.';
			
			board[red.y][red.x] = 'R';
			board[blue.y][blue.x] = 'B';
			
			if(!newBlue.alive) continue;
			
			if(!newRed.alive){
				res = depth;
				return;
			}
		}
	}

}
