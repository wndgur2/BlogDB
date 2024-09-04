package SWEA.swea_13469;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

class UserSolution
{
	int H, W;
	
	ListIterator<Character> iter;
	LinkedList<Character> list;
	HashMap<Character, Integer> count;
	
	void init(int H, int W, char mStr[])
	{
		// O(N)
		this.H = H;
		this.W = W;
		list = new LinkedList<>();
		iter = list.listIterator();
		count = new HashMap<>();
		for (int i = 0; i < mStr.length; i++) {
			if(mStr[i] == '\0') break;
			iter.add(mStr[i]);
			if(!count.containsKey(mStr[i])) count.put(mStr[i], 1);
			else count.put(mStr[i], count.get(mStr[i])+1);
		}
		while(iter.hasPrevious()) iter.previous();
	}
	
	void insert(char mChar)
	{
//		System.out.println("Insert");
		// O(1)
		iter.add(mChar);
		
		if(!count.containsKey(mChar)) count.put(mChar, 1);
		else count.put(mChar, count.get(mChar)+1);
	}

	char moveCursor(int mRow, int mCol)
	{
//		System.out.println("Move");
		// O(N/2)
		int idx = (mRow-1)*W + (mCol-1);
		
		if(idx >= list.size()) {
			iter = list.listIterator(list.size());
			return '$';
		} else iter = list.listIterator(idx);
		
		char next = iter.next();
		iter.previous();
		return next;
	}

	int countCharacter(char mChar)
	{
		// O(N/2)
//		System.out.println("Count");
		int cnt=0;
		int cursor =iter.nextIndex();
		
		if(!count.containsKey(mChar)) return 0;
		if(cursor < list.size()/2) {
			// 앞에서부터 탐색
			int i=0;
			Iterator<Character> iter = list.iterator();
			while(i++<cursor)
				if(iter.next() == mChar) cnt++;
			return count.get(mChar) - cnt;
		} else {
			// 뒤에서부터 탐색
			int i=list.size();
			Iterator<Character> iter = list.descendingIterator();
			while(i-->cursor)
				if(iter.next() == mChar) cnt++;
			
			return cnt;
		}
	}
}