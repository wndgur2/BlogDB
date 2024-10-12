package SWEA.swea_19340;

import java.util.*;


// 8.19 19:50~

class Node{
	static HashMap<Integer, Node> nodes;
	int id;
	int n;
	Node parent = null;
	// this could be hashMap
	LinkedList<Node> children = new LinkedList<>();
	
	Node(int id, int n){
		this.id = id;
		this.n = n;
	}
	
	void addChild(Node child) {
		this.children.add(child);
		child.parent = this;
	}
	
	int getSumN() {
		int res = n;
		for(Node child:children)
			res += child.getSumN();
		return res;
	}
	
	void remove() {
//		this.parent.children.remove(this);
		nodes.remove(id);
		for(Node child:children) {
			child.remove();
		}
	}
	
	public String toString() {
		return "N(" + id + ") ";
	}
	
	public Node getRoot() {
		if(this.parent == null) return this;
		return this.parent.getRoot();
	}
}

class UserSolution {
	HashMap<Integer, Node> nodes;
	int[] roots;
	int[] sums;
	int R;
	
	public void init(int N, int mId[], int mNum[]) {
		R = N;
		nodes = new HashMap<>();
		Node.nodes = nodes;
		roots = new int[N];
		sums = new int[N];
		
		for(int i=0; i<N; i++) {
			Node newNode = new Node(mId[i], mNum[i]);
			nodes.put(newNode.id, newNode);
			roots[i] = mId[i];
			sums[i] = mNum[i];
		}
		return;
	}

	public int add(int mId, int mNum, int mParent) {
		Node newNode = new Node(mId, mNum);
		Node parent = nodes.get(mParent);
//		System.out.println(parent);
		if(parent.children.size()==3)
			return -1;
		nodes.put(mId, newNode);
		parent.addChild(newNode);
		Node root = parent.getRoot();
		for(int i=0; i<R; i++) {
			if(roots[i]==root.id) {
				sums[i] += mNum;
			}
		}
		
		return parent.getSumN();
	}

	public int remove(int mId) {
		Node node = nodes.get(mId);
		if(node==null) return -1;
		int res = node.getSumN();

		Node root = node.getRoot();
		for(int i=0; i<R; i++) {
			if(roots[i]==root.id) {
				sums[i] -= res;
			}
		}

		node.parent.children.remove(node);
		node.remove();
		nodes.remove(mId);
		
		return res;
	}

	public int distribute(int K) {
		int[] sizes = new int[R];
		int sum=0;
		int maxSize = 0;
		
		for(int i=0; i<R; i++) {
			sizes[i] = nodes.get(roots[i]).getSumN();
			sum += sizes[i];
			if(sizes[i] > maxSize) maxSize = sizes[i];
		}
		
		// 상품권이 넉넉함 
		if(K > sum) return maxSize;
		
		// 상한 개수 정하기
		int l=0;
		
		// 가장 큰 사이즈. 이만큼을 모든 그룹에 주면 상품권이 부족할 것이다.
		int r=maxSize; 
		
		// 현재 탐색중인 상한 사이즈.
		// 이만큼을 상한으로 잡고 주면 모든 그룹에게 줄 수 있어야 하며, 
		// 그 크기가 가능한 상한 중 가장 커야한다.
		int m=0;
		
		// m을 상한으로 분배할 수 있는 그룹의 개수 
		int g=0;
		while(l<r) {
			m = (l + r + 1)/2;
			g = getGroupN(K, m);
			if(g<R) { // 그룹 수가 적다. 상한을 낮춰야 한다.
				r = m-1; // m은 절대 아니다.
			} else { // 그룹 수가 충분하다. 상한을 올려보자.
				l = m; // m일 수도 있다.
			}
		}
		
		return r;
	}
	
	// K의 개의 상품권을, 상한을 limit으로 하면 몇 개의 그룹에게 분배할 수 있는가?
	int getGroupN(int K, int limit) {
		int res = 0;
		for(int i=0; i<R; i++) {
			int size = sums[i];
			if(size>limit) K-=limit;
			else K-=size;
			if(K<0) return res;
			res++;
		}
		return res;
	}
}