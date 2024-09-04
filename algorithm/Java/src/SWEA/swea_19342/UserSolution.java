package SWEA.swea_19342;

class UserSolution
{
	int[] populations;
	int[] popSums;
	int[] roadCosts;
	int[] roadN;
//	int[] costSums;
	int N;
	
	void init(int N, int mPopulation[])
	{
		this.N = N;
		populations = mPopulation;
		popSums = new int[N];
		roadCosts = new int[N-1];
		roadN = new int[N-1];
//		costSums = new int[N-1];
//		int costSum = 0;
		int popSum = 0;
		for(int i=0; i<N-1; i++) {
			roadCosts[i] = populations[i]+populations[i+1];
			roadN[i] = 1;
//			costSum += roadCosts[i];
//			costSums[i] = costSum + roadCosts[i];
			
			popSum += populations[i];
			popSums[i] = popSum;
		}
		popSums[N-1] = popSum + populations[N-1];
		
		return;
	}

	int expand(int M)
	{
		int[] maxCosts = {0, 0, 0};
		int[] maxIdx = {0, 0, 0};
		
		// 최대 비용 세개 찾기 O(N)
		for(int i=0; i<N-1; i++) {
			if(roadCosts[i] > maxCosts[0]) {
				maxCosts[2] = maxCosts[1];
				maxCosts[1] = maxCosts[0];
				maxCosts[0] = roadCosts[i];
				
				maxIdx[2] = maxIdx[1];
				maxIdx[1] = maxIdx[0];
				maxIdx[0] = i;
			} else if(roadCosts[i] > maxCosts[1]) {
				maxCosts[2] = maxCosts[1];
				maxCosts[1] = roadCosts[i];
				
				maxIdx[2] = maxIdx[1];
				maxIdx[1] = i;
			} else if(roadCosts[i] > maxCosts[2]) {
				maxCosts[2] = roadCosts[i];
				
				maxIdx[2] = i;
			}
		}
		
		// 1, 2, 3번째 M개 비용 낮추기
		int res = 0;
		for(int i=0; i<M; i++) {
			roadN[maxIdx[i]] ++;
			int pops = populations[maxIdx[i]] + populations[maxIdx[i]+1];
			res = (roadCosts[maxIdx[i]] = pops/roadN[maxIdx[i]]);
//			for(int j=maxIdx[i]; j<N; j++) {
//				
//			}
		}
		return res;
	}
	
	int calculate(int mFrom, int mTo)
	{
		if(mFrom > mTo) {
			int temp = mTo;
			mTo = mFrom;
			mFrom = temp;
		}
		int cost = 0;
		for(int i=mFrom; i<mTo; i++) 
			cost += roadCosts[i];
		return cost;
	}
	
	int divide(int mFrom, int mTo, int K)
	{
		if(mFrom > mTo) {
			int temp = mTo;
			mTo = mFrom;
			mFrom = temp;
		}
		// 최소-최대 선거구 크기 이분탐색
		int l = 0;
		int r = popSums[N-1];
		int m=0, g;
		while(l<r) {
			m = (l + r)/2; // 1 차이가 날 때, l이 할당됨.
			g = getPossibleGroupN(m, mFrom, mTo);
			if(g > K) { // 그룹이 많으므로 크기를 키워야함.
				l = m+1;
			} else {
				r = m;
			}
		}
		
		return r;
	}
	
	int getPossibleGroupN(int maxSize, int mFrom, int mTo) {
		int groupN = 1;
		int sum = 0;
		for(int i=mFrom; i<=mTo; i++) {
			if(sum + populations[i] > maxSize) {
				sum = populations[i];
				groupN ++;
			} else {
				sum += populations[i];
			}
		}
		return groupN;
	}
}