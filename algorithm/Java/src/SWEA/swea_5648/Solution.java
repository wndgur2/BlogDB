package SWEA.swea_5648;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/*

메모리
115,068 kb
실행시간
2,749 ms

4
4
0 -1 0 10
0 0 1 10
2 0 2 10
1 0 3 10
4
-1000 0 3 5
1000 0 2 3
0 1000 1 7
0 -1000 0 9
4
-1 1 3 3
0 1 1 1
0 0 2 2
-1 0 0 9
4
0 1 1 10
1 0 2 10
0 0 3 10
0 -3 0 10

 */

public class Solution {
	
	// 상하좌우
	static int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	
	static class Atom{
		int dir;
		int energy;
		int x, y;
		int moved;
		int key;
		boolean dead = false;
		
		public Atom(int dir, int energy, int x, int y) {
			super();
			this.dir = dir;
			this.energy = energy;
			this.x = x;
			this.y = y;
			setKey();
		}
		
		private void setKey() {
			// -1000 ~ 1000
			this.key = (y+1000)*2000 + x+1000;
		}
		
		public void move() {
			this.x += dirs[dir][0];
			this.y += dirs[dir][1];
			setKey();
		}
	}

	static HashMap<Integer, Atom> atoms = new HashMap<>();
	static HashMap<Integer, List<Atom>> movedAtoms = new HashMap<>();
	static int res;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			atoms.clear();
			movedAtoms.clear();
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int energy = Integer.parseInt(st.nextToken());
				Atom atom = new Atom(dir, energy, x*2, y*2);
				atoms.put(atom.key, atom);
			}
			
			// simulate
			simulate();
			sb.append('#').append(tc).append(' ').append(res).append('\n');
		}
		System.out.println(sb.toString());
	}

	private static int simulate() {
		res=0;
		while(!atoms.isEmpty()) {
			for(Atom atom: atoms.values()) {
				res += move(atom);
			}
			
			atoms.clear();
			
			movedAtoms.forEach((key, atomList)->{
				if(atomList.size()>1)
					for(Atom atom: atomList) res += atom.energy;
				else atoms.put(key, atomList.get(0));
			});
			
			movedAtoms.clear();
		}
		
		return res;
	}
	
	public static int move(Atom atom) {
		int energy=0;
		
		atom.move();
		if(atom.x<-2000 || atom.y<-2000 || atom.x>2000 || atom.y>2000) return 0;
		
		int newKey = atom.key;
		
		if(movedAtoms.containsKey(newKey)) { // 박치기
			movedAtoms.get(newKey).add(atom);
		} else { // 아무도 없다
			ArrayList<Atom> newAtomList = new ArrayList<>();
			newAtomList.add(atom);
			movedAtoms.put(newKey, newAtomList);
		}
		
		return energy;
	}

}
