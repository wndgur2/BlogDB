package BOJ.boj_17135;

import java.io.*;
import java.util.*;

class Enemy implements Comparable<Enemy>{
	int id;
	int distance;
	int x, y;
	
	public Enemy(int id, int y, int x, int distance) {
		this.id = id;
		this.distance = distance;
		this.y = y;
		this.x = x;
	}
	
	@Override
	public int compareTo(Enemy e1) {
		if(distance==e1.distance) return x-e1.x;
		return distance-e1.distance;
	}
	
	@Override
	public String toString() {
		return "[DISTANCE: " + distance + " X: " + x + "] ";
	}
}

public class Main {
//	
	static TreeSet<Enemy>[] archers = new TreeSet[3];
	
	static BufferedReader br=  new BufferedReader(new InputStreamReader(System.in));
	static int N, M, D, R=3, res=0;
	static int[] archerXs;
	static boolean[] archerVisit;
	static StringTokenizer st;
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		archerVisit = new boolean[M];
		archerXs = new int[3];
		combination(0, 0);
		
		System.out.println(res);
	}
	
	public static void combination(int depth, int start) throws IOException{
		if(depth == R) {
			play();
			return;
		}
		
		for (int i = start; i < M; i++) {
			archerXs[depth] = i;
			combination(depth+1, i+1);
		}
	}
	
	public static void play() throws IOException {
//		System.out.println(Arrays.toString(archerXs));
		for (int i = 0; i < 3; i++) {
			archers[i] = new TreeSet<>();
		}

		int idx=0;//적의 id
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 1) {
					// 적들 트리셋에 추가
					for (int j2 = 0; j2 < 3; j2++) {
						archers[j2].add(new Enemy(idx, i, j, Math.abs(i-N) + Math.abs(j-archerXs[j2])));
					}
					idx ++;
				}
			}
		}
		boolean[] isDead = new boolean[idx];
//		System.out.println(archers[0]);
		
		// 게임 구현부
		int tempD = D;
		int max_y = N;
		int tempRes = 0;
		while(max_y-- > 0) {
			// 적 죽이기
			ArrayList<Integer> targets = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				// 가장 가까운 적 찾기
				Enemy toShoot = null;
				while((toShoot = archers[i].pollFirst())!=null) {
					if(isDead[toShoot.id]) continue;
					if(toShoot.y > max_y) {
						isDead[toShoot.id] = true;
						continue;
					}
					break;
				}
				if(toShoot==null) break; // 적이 다 죽음
				if(toShoot.distance > tempD) {
//					System.out.println("NO TARGET FOR archer[" + i + "]");
					// 범위 내 적이 없음.
					archers[i].add(toShoot);
					continue;
				}
				targets.add(toShoot.id);
			}
			
			for(int index:targets) {
				if(isDead[index]) continue;
				isDead[index] = true;
//				System.out.println("KILLED: " + index);
				tempRes++;
			}
			
			tempD++;
//			System.out.println("TURN: " + (N-max_y) + " tempRes: " + tempRes);
		}
		if(tempRes > res) res = tempRes;
	}
}
