class Solution {
    int MOD = 20170805;
    int WIDTH, HEIGHT;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        
        HEIGHT = m;
        WIDTH = n;
        
        int[][] paths = new int[m+1][n+1];
        for(int i=0; i<m+1; i++){
            for(int j=0; j<n+1; j++){
                paths[i][j] = 0;
            }
        }
        paths[m-1][n-1] = 1;
        
        answer = dfs(0, 0, paths, cityMap, 0);
        
        return answer;
    }
    
    public int dfs(int y, int x, int[][] paths, int[][] cityMap, int dir){
        if(y>=HEIGHT) return 0;
        if(x>=WIDTH) return 0;
        if(cityMap[y][x]==0 && paths[y][x]>0) return paths[y][x];
        if(cityMap[y][x]==1) return 0;
        
        int pathsUnder=0, pathsRight=0;
        
        if(cityMap[y][x]==0){
             pathsUnder = dfs(y+1, x, paths, cityMap, 0);
             pathsRight = dfs(y, x+1, paths, cityMap, 1);
        } else if(cityMap[y][x]==2){
            if(dir==0){
                return dfs(y+1, x, paths, cityMap, dir);
            }else{
                return dfs(y, x+1, paths, cityMap, dir);
            }
        }
        
        paths[y][x] = (pathsUnder + pathsRight) % MOD;
        return paths[y][x];
    }
}