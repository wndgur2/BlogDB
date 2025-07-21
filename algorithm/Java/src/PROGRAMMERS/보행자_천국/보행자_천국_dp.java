class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(i==0 && j==0) continue;
                dp[i][j][0] = 0;
                dp[i][j][1] = 0;
                if(i>0){
                    if(cityMap[i-1][j]==0)
                        dp[i][j][0] += (dp[i-1][j][0] + dp[i-1][j][1]) % MOD;
                    else if(cityMap[i-1][j]==2)
                        dp[i][j][0] += dp[i-1][j][0] % MOD;
                }
                if(j>0){
                    if(cityMap[i][j-1]==0)
                        dp[i][j][1] += (dp[i][j-1][0] + dp[i][j-1][1]) % MOD;
                    else if(cityMap[i][j-1]==2)
                        dp[i][j][1] += dp[i][j-1][1] % MOD;
                }
                // System.out.print(dp[i][j][0] + "/" + dp[i][j][1] + ", ");
            }
            // System.out.println();
        }
        
        answer = (dp[m-1][n-1][0]+dp[m-1][n-1][1]) % MOD;
        
        return answer;
    }
}