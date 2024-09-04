package SWEA.swea_1861;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 률아 코드 테스트

class Sol2
{
    public static int t, n, ans, ansN;
    public static int[][] arr;
    public static int[] dr= {-1,0,1,0};
    public static int[] dc= {0,1,0,-1};
    public static int chk[];
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        t=Integer.parseInt(br.readLine());
         
        for (int testCase = 1; testCase <= t; testCase++) {
            n=Integer.parseInt(br.readLine());
            arr=new int[n][n];
            chk=new int[n*n+1];
            ans=0;
            ansN=n*n;
             
            for (int i = 0; i < n; i++) {    
                StringTokenizer st=new StringTokenizer(br.readLine(), " ");             
                for (int j = 0; j < n; j++)
                    arr[i][j]=Integer.parseInt(st.nextToken());
            }
             
            for (int i = 0; i < n; i++) {    
                for (int j = 0; j < n; j++) {
                    int num=arr[i][j];
                    for (int k = 0; k < 4; k++) {
                        int nr=i+dr[k];
                        int nc=j+dc[k];
                         
                        if(!valid(nr,nc)) continue;
                         
                        if(arr[nr][nc]==num+1) {
                            chk[num]=1;
                            break;
                        }
                    }
                }
            }
             
 
            for (int i = n*n-1; i >0; i--) {
                if(chk[i]!=1)continue;
                
                chk[i]+=chk[i+1];
                if(ans<=chk[i]) {
                    ansN=i;
                    ans=chk[i];
                }
            }
 
            System.out.println("#"+testCase+" "+ansN+" "+(ans+1));
        }
         
         
         
         
    }
     
    public static boolean valid(int i, int j) {
        return i>=0&&i<n&&j>=0&&j<n;
    }
}