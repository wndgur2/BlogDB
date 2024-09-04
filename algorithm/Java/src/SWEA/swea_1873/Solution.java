package SWEA.swea_1873;

/*
실행시간	138 ms
메모리	21,892 kb
 */

import java.io.*;
import java.util.StringTokenizer;
public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int H, W, C;
	static char[][] map;
	static String cmds;
	static char dir;
	static int y, x;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			cmds = "";
			
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]=='<' || map[i][j]=='>' || map[i][j]=='^' || map[i][j]=='v') {
						y = i;
						x = j;
						dir = map[i][j];
					}
				}
			}
			
			C = Integer.parseInt(br.readLine());
			if(C>0) cmds = br.readLine();
			for (int i = 0; i < cmds.length(); i++)
				play(cmds.charAt(i));
			sb.append('#').append(tc).append(' ');
			sb.append(map());
		}
		System.out.print(sb.toString());
	}
	
	public static String map() {
		StringBuilder sb=  new StringBuilder();
		for(char[] row: map) {
			for(char c:row) sb.append(c);
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public static void play(char cmd) {
//		System.out.println("CMD: " + cmd);
		map[y][x] = '.';
		switch (cmd) {
		case 'U':
			dir = '^';
			if(isMovable(y-1, x)) y-=1;
			break;
		case 'D':
			dir = 'v';
			if(isMovable(y+1, x)) y+=1;
			break;
		case 'L':
			dir = '<';
			if(isMovable(y, x-1)) x-=1;
			break;
		case 'R':
			dir = '>';
			if(isMovable(y, x+1)) x+=1;
			break;
		case 'S':
			shoot();
			break;
		default:
			break;
		}
		map[y][x] = dir;
		
//		System.out.println(map());
	}
	
	public static boolean isMovable(int y, int x) {
		if(y<0||x<0||y>=H||x>=W) return false;
		return map[y][x]== '.';
	}
	
	public static void shoot() {
		int ty = y;
		int tx = x;
		
		switch (dir) {
		case '^':
			while(--ty>=0) {
				if(map[ty][tx]=='#') break;
				if(map[ty][tx]=='*') {
					map[ty][tx] = '.';
					break;
				}
			}
			break;
		case '>':
			while(++tx<W) {
				if(map[ty][tx]=='#') break;
				if(map[ty][tx]=='*') {
					map[ty][tx] = '.';
					break;
				}
			}
			break;
		case '<':
			while(--tx>=0) {
				if(map[ty][tx]=='#') break;
				if(map[ty][tx]=='*') {
					map[ty][tx] = '.';
					break;
				}
			}
			break;
		case 'v':
			while(++ty<H) {
				if(map[ty][tx]=='*') {
					map[ty][tx] = '.';
					break;
				}
			}
			break;
		}
	}

}
