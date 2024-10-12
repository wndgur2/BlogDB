package SWEA.swea_19457;

import java.util.*;

class Node{
	int mDevice;
	ArrayList<Edge> edges = new ArrayList<>();
	
	Node(int mDevice){
		this.mDevice = mDevice;
	}
	
	public int getCostFrom(Node b) {
		int totalCost = 0;
		
		LinkedList<Branch> queue = new LinkedList<>();
		queue.add(new Branch(this, null, 0));
		
		while(!queue.isEmpty()) {
			Branch branch = queue.poll();
			Node node = branch.node;
			int cost = branch.cost;
			if(node == b) return cost;
			for(int i=0; i<node.edges.size(); i++) {
				Edge edge = node.edges.get(i);
				Node next = edge.getDestFrom(node);
				if(next == branch.from) continue;
				queue.add(new Branch(next, node, cost + edge.cost));
			}
		}
		return totalCost;
	}
	
	public int test() {
		TreeSet<Integer> costs = new TreeSet<>();
		costs.add(0);
		
		for(int i=0; i<this.edges.size(); i++) {
			Edge edge = this.edges.get(i);
			int cost = edge.getDestFrom(this).getMaxCostFrom(this, edge.cost);
			costs.add(cost);
		}
		
		return costs.pollLast() + costs.pollLast();
	}
	
	public int getMaxCostFrom(Node startNode, int initCost) {
		int maxCost = initCost;
		LinkedList<Branch> queue = new LinkedList<>();
		queue.add(new Branch(this, null, initCost));
		
		while(!queue.isEmpty()) {
			Branch branch = queue.poll();
			Node node = branch.node;
			if(node == startNode) continue;
			int cost = branch.cost;
			if(cost > maxCost) maxCost = cost;
			for(int i=0; i<node.edges.size(); i++) {
				Edge edge = node.edges.get(i);
				Node next = edge.getDestFrom(node);
				if(next == branch.from) continue;
				queue.add(new Branch(next, node, cost + edge.cost));
			}
		}
		
		return maxCost;
	}
}

class Branch{
	Node from;
	Node node;
	int cost;
	
	Branch(Node node, Node from, int cost){
		this.node = node;
		this.from = from;
		this.cost = cost;
	}
}

class Edge{
	int cost;
	Node a;
	Node b;
	
	Edge(Node a, Node b, int cost){
		this.a = a;
		this.b = b;
		this.cost = cost;
	}
	
	public Node getDestFrom(Node c){
		if(c==a) return b;
		if(c==b) return a;
		return null;
	}
}

class UserSolution
{
	Map<Integer, Node> nodes;
	
	public void init(int mDevice)
	{
		nodes = new HashMap<>();
		nodes.put(mDevice, new Node(mDevice));
		return;
	}
	
	
	public void connect(int mOldDevice, int mNewDevice, int mLatency)
	{
		Node oldNode = nodes.get(mOldDevice);
		Node newNode = new Node(mNewDevice);
		Edge newEdge = new Edge(oldNode, newNode, mLatency);
		oldNode.edges.add(newEdge);
		newNode.edges.add(newEdge);
		nodes.put(mNewDevice, newNode);
		return;
	}
	
	public int measure(int mDevice1, int mDevice2)
	{
		return nodes.get(mDevice1).getCostFrom(nodes.get(mDevice2));
	}
	
	public int test(int mDevice)
	{
		return nodes.get(mDevice).test();
	}
}