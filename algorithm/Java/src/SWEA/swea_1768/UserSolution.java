package SWEA.swea_1768;


class UserSolution {
    public final static int N = 4;
    public static int[] tempGuess;
    public static int[][] guesses;
    public static boolean[] isUsed;
    public static int guessCount;
    
    public void init() {
    	guesses = new int[10][6]; // index / nums(4)+strike(1)+ball(1)
    	tempGuess = new int[N];
    	isUsed = new boolean[10];
    	guessCount = 0;
    }
    
    public void doUserImplementation(int guess[]) {
    	init();
    	dfs(0);
    	for(int i=0; i<N; ++i) {
    		guess[i] = tempGuess[i];
//    		System.out.print(guess[i] + " ");
    	}
//    	System.out.println();
    }
    
    private Solution.Result userQuery(int digits[]){
    	int[] guess = tempGuess;
    	boolean[] digits_c = new boolean[10];
    	for(int d:digits) {
    		digits_c[d] = true;
    	}
    	
    	Solution.Result result = new Solution.Result();
		
		result.strike = 0;
		result.ball = 0;

		for (int idx = 0; idx < N; ++idx)
			if (guess[idx] == digits[idx])
				result.strike++;
			else if (digits_c[guess[idx]])
				result.ball++;
		return result;
    }
    
    public boolean sameResWithPast() {
    	for(int i=0; i<guessCount; i++) {
    		int[] digits = {guesses[i][0], guesses[i][1], guesses[i][2], guesses[i][3]};
    		Solution.Result tempRes = userQuery(digits);
    		if(tempRes.strike != guesses[i][4] || tempRes.ball != guesses[i][5]) return false;
    	}
    	return true;
    }
    
    public boolean dfs(int index) {
    	if(index==N) {
    		// try to guess
    		if(!sameResWithPast()) return false;
    		
    		Solution.Result newRes = Solution.query(tempGuess);
    		if(newRes.strike==4) return true;
    		
    		guesses[guessCount][0] = tempGuess[0];
    		guesses[guessCount][1] = tempGuess[1];
    		guesses[guessCount][2] = tempGuess[2];
    		guesses[guessCount][3] = tempGuess[3];
    		guesses[guessCount][4] = newRes.strike;
    		guesses[guessCount++][5] = newRes.ball;
    		
    		return false;
    	}
    	for(int i=0; i<10; i++) {
    		if(isUsed[i]) continue;
    		tempGuess[index] = i;
    		isUsed[i] = true;
    		if(dfs(index+1)) return true;
    		isUsed[i] = false;
    	}
    	return false;
    }
    
}
