package SWEA.swea_14616;

import java.util.*;

class Athlete implements Comparable<Athlete>{
	int id;
	int ability;
	
	public Athlete(int id, int ability) {
		this.id = id;
		this.ability = ability;
	}
	
	@Override
	public int compareTo(Athlete o1) {
		if(ability == o1.ability) return id-o1.id;
		return o1.ability-ability;
	}
	
	@Override
	public String toString() {
		return "(" + Integer.toString(id) + ":" + Integer.toString(ability) + ") ";
	}
}

class League{
	int id;
	TreeSet<Athlete> athletes = new TreeSet<>();
	
	public League(int id){
		this.id = id;
	}
}

public class UserSolution {
	
	ArrayList<League> leagues;
	int nPerLeague;

    void init(int N, int L, int mAbility[]) {
    	leagues = new ArrayList<>();
    	
//    	TreeSet<Athlete> input = new TreeSet<>();
//
//    	for(int i=0; i<N; i++) {
//    		Athlete newAth = new Athlete(i, mAbility[i]);
//    		input.add(newAth);
//    	}
    	
    	nPerLeague = N/L;
//    	Iterator<Athlete> iter = input.iterator();
    	for(int i=0; i<N; i++) {
    		if(i%nPerLeague==0) {
    			leagues.add(new League(i/nPerLeague));
//    			if(leagues.size()>=2)
//    			System.out.println(leagues.get(leagues.size()-2).athletes);
    		}
//    		Athlete athlete = iter.next();
    		Athlete athlete = new Athlete(i, mAbility[i]);
    		leagues.get(leagues.size()-1).athletes.add(athlete);
    	}
//		System.out.println(leagues.get(leagues.size()-1).athletes);
    }
    
//    private int swap(League l1, int idx1, League l2, int idx2) {
//    	int res=0;
//    	return res;
//    }

    int move() {
    	int res=0;
    	for(int i=0; i<leagues.size()-1; i++) {
			// swap last athlete of leagues[i] with first athlete of leagues[i+1]
        	Athlete last = leagues.get(i).athletes.pollLast();
        	Athlete first = leagues.get(i+1).athletes.pollFirst();
        	
        	leagues.get(i).athletes.add(first);
        	last.ability += 10000;
    		leagues.get(i+1).athletes.add(last);
        	
        	res += last.id + first.id;
        	

//        	System.out.println(res);
//        	System.out.println(leagues.get(i).athletes);
    	}
    	for(int i=1; i<leagues.size(); i++) {
    		leagues.get(i).athletes.first().ability-=10000;
    		leagues.get(i).athletes.add(leagues.get(i).athletes.pollFirst());
    	}
//		System.out.println(leagues.get(leagues.size()-1).athletes);
        return res;
    }

    int trade() {
    	int res=0;
    	for(int i=0; i<leagues.size()-1; i++) {
			// swap last athlete of leagues[i] with first athlete of leagues[i+1]
        	Athlete middle = null;
        	Iterator<Athlete> iter = leagues.get(i).athletes.iterator();
        	for(int j=0; j<(nPerLeague+1)/2-1; j++) {
        		iter.next();
        	}
        	middle = iter.next();
        	
        	Athlete first = leagues.get(i+1).athletes.first();
        	leagues.get(i).athletes.remove(middle);
        	leagues.get(i+1).athletes.remove(first);
        	
        	leagues.get(i).athletes.add(first);
        	middle.ability += 10000;
    		leagues.get(i+1).athletes.add(middle);
    		
        	
        	res += middle.id + first.id;
    	}
    	for(int i=1; i<leagues.size(); i++) {
    		leagues.get(i).athletes.first().ability-=10000;
    		leagues.get(i).athletes.add(leagues.get(i).athletes.pollFirst());
    	}
        return res;
    }
}
