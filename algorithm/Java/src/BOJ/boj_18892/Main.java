package BOJ.boj_18892;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
- LIS를 유지하며 indexes를 채운다
- indexes를 돌며 가능한 LIS들을 구한다.
- 구한 LIS들을 사전순 정렬한다.
- k번째 LIS를 출력한다.
 */

/*
6 2
1 10 8 11 9 100
 */

public class Main {
    static int[] numbers, indexes;
    static ArrayList<Integer> LIS;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        // k번째 LIS
        int k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        numbers = new int[N];
        indexes = new int[N];
        LIS = new ArrayList<>();
        
        for(int i=0; i<N; i++)
            numbers[i] = Integer.parseInt(st.nextToken());

        // LIS를 유지하며 indexes를 채운다
        int length = 0;
        for(int i=0; i<N; i++){
            int idx = -Collections.binarySearch(LIS, numbers[i])-1;
            if(idx>=LIS.size()){ // add
                LIS.add(numbers[i]);
                indexes[i] = LIS.size()-1;
                length++;
            } else{ // set
                LIS.set(idx, numbers[i]);
                indexes[i] = idx;
            }
        }

        // System.out.println(Arrays.toString(indexes));

        // indexes를 돌며 가능한 LIS들을 구한다.
        ArrayList<int[]>[] LISs = new ArrayList[length+1];

        // LISs[i] : 길이가 i인 증가 수열들

        for(int i=0; i<length+1; i++)
            LISs[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if(indexes[i]==0) LISs[1].add(new int[]{numbers[i]});
            else{
                // indexes[i]보다 하나 적은 애들에서 마지막보다 크면 자기를 더한 배열을 LISs에 추가하기
                for(int[] j:LISs[indexes[i]]){
                    if(numbers[i] > j[j.length-1]){
                        int[] newArr = new int[j.length+1];
                        
                        // j를 newArr에 복사
                        for(int l=0; l<j.length; l++) newArr[l] = j[l];
                        newArr[j.length] = numbers[i];
                        
                        LISs[indexes[i]+1].add(newArr);
                    }
                }
            }
        }

        // 구한 LIS들을 사전순 정렬한다.
        // for(int[] arr: LISs[LISs.length-1]){
        //     System.out.println(Arrays.toString(arr));
        // }

        Collections.sort(LISs[LISs.length-1], (a, b)->{
            for(int i=0; i<a.length; i++){
                if(a[i] != b[i]) return Integer.compare(a[i], b[i]);
            }
            return 1;
        });

        StringBuilder sb = new StringBuilder();
        // k번째 LIS를 출력한다.
        if(k>LISs[LISs.length-1].size()) System.out.println(-1);
        else {
            for(int i=0; i<length; i++){
                sb.append(LISs[LISs.length-1].get(k-1)[i] + " ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

}
