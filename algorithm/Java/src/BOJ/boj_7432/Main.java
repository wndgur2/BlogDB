package BOJ.boj_7432;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*

7
WINNT\SYSTEM32\CONFIG
GAMES
WINNT\DRIVERS
HOME
WIN\SOFT
GAMES\DRIVERS
WINNT\SYSTEM32\CERTSRV\CERTCO~1\X86

GAMES
 DRIVERS
HOME
WIN
 SOFT
WINNT
 DRIVERS
 SYSTEM32
  CERTSRV
   CERTCO~1
    X86
  CONFIG

 */

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static class Node implements Comparable<Node>{
		String value;
		TreeSet<Node> children = new TreeSet<>();
		Node(){}
		Node(String value){
			this.value = value;
		}
		void addChild(Node node) {
			children.add(node);
		}
		@Override
		public int compareTo(Node o) {
			return value.compareTo(o.value);
		}
	}
	public static void main(String[] args) throws IOException{
		int N = Integer.parseInt(br.readLine());
		Node root = new Node();
		for (int i = 0; i < N; i++) {
			Node prev = root;
			StringTokenizer st = new StringTokenizer(br.readLine(), "\\");
			while(st.hasMoreTokens()) {
				String tmp = st.nextToken();
				Node node = new Node(tmp);
				if(!prev.children.contains(node)) {
					prev.addChild(node);
				} else {
					node = prev.children.ceiling(node);
				}
				prev = node;
			}
		}
		
		dfs(root, 0);
		System.out.println(sb.toString());
	}
	private static void dfs(Node node, int depth) {
		if(depth>0) {
			for (int i = 0; i < depth-1; i++) {
				sb.append(' ');
			}
			sb.append(node.value).append('\n');
		}
		for(Node child:node.children) {
			dfs(child, depth+1);
		}
	}

}
