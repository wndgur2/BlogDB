package SWEA.swea_13072;

import java.util.HashMap;

/**

이중혁

소요시간
1시간 40분 

메모리
126,580kb

실행시간
641ms

 */

class Soldier{
	boolean isFired=false;
	int id, team;
	Soldier(int id, int team){
		this.id = id;
		this.team = team;
	}
}

class Nodes{
	Node head, last;
	
	void addLast(Nodes nodes){
		if(nodes.head == null) return;
		
		if(this.last != null) this.last.next = nodes.head;
		else this.head = nodes.head;
		this.last = nodes.last;
	}
	
	void addLast(Node node) {
		if(this.head == null) this.head = node;
		else this.last.next = node;
		this.last = node;
	}
	
	int getBestSoldier() {
		int res=0;
		Node node = head;
		
		// if head isFired => find new head node
		while(node!=null && node.soldier.isFired) {
			this.head = node.next;
			node = this.head;
			
			// 모든 노드가 fired
			if(node==null) this.last = null;
		}
		
		while(node != null) {
			// last node 갱신
			this.last = node;
			// if 다음 노드 isFired => remove
			while(node.next!=null && node.next.soldier.isFired)
				node.next = node.next.next;
			if(node.soldier.id>res) res = node.soldier.id;
			node = node.next;
		}
		
		// 헤드가 없다면 0 리턴 
		return res;
	}
}

class Node{
	Soldier soldier;
	Node next;
	Node(Soldier soldier){
		this.soldier = soldier;
	}
}

class UserSolution
{
	HashMap<Integer, Soldier> soldiers;
	Nodes[][] teams;
	
	public void init()
	{
		soldiers = new HashMap<>();
		teams = new Nodes[5][5]; // [team][score]
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				teams[i][j] = new Nodes();
			}
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		Soldier soldier = new Soldier(mID, mTeam-1);
		soldiers.put(mID, soldier);
		teams[mTeam-1][mScore-1].addLast(new Node(soldier));
	}
	
	public void fire(int mID)
	{
		Soldier soldier = soldiers.get(mID);
		soldier.isFired = true; // 표시만 함
		soldiers.remove(mID);
	}

	public void updateSoldier(int mID, int mScore) // fire and hire
	{
		Soldier oldSoldier = soldiers.get(mID);
		fire(mID);
		hire(mID, oldSoldier.team+1, mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		Nodes[] newScores = new Nodes[5];
		
		for (int i = 0; i < 5; i++) {
			newScores[i] = new Nodes();
		}
		
		for (int i = 0; i < 5; i++) {
			int newScore = i + mChangeScore;
			if(newScore<0) newScore = 0;
			else if(newScore>4) newScore = 4;
			
			newScores[newScore].addLast(teams[mTeam-1][i]);
		}
		
		teams[mTeam-1] = newScores;
	}
	
	public int bestSoldier(int mTeam)
	{
		int res = 0;
		for (int i = 4; i >= 0; i--) {
			res = teams[mTeam-1][i].getBestSoldier();
			if(res>0) break;
		}
		return res;
	}
}