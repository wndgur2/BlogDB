package SWEA.swea_14618;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution
{
    private static BufferedReader br;
    private static final UserSolution userSolution = new UserSolution();

    private final static int MAX_M = 50000;
    private final static int MAX_LEN = 11;

    private static final char[][] mWords = new char[MAX_M][MAX_LEN];

    private static boolean run() throws Exception
    {
        StringTokenizer stdin = new StringTokenizer(br.readLine(), " ");
        boolean ok = true;
        int N = Integer.parseInt(stdin.nextToken());
        int M = Integer.parseInt(stdin.nextToken());

        for (int i = 0; i < M; i++)
        {
            String word = br.readLine();
            Arrays.fill(mWords[i], (char)0);
            word.getChars(0, word.length(), mWords[i], 0);
        }

        userSolution.init(N, M, mWords);

        int cnt = Integer.parseInt(br.readLine());

        for (int i = 0; i < cnt; i++)
        {
            stdin = new StringTokenizer(br.readLine(), " ");
            int mID, ret, ans;
            char mCh;

            mID = Integer.parseInt(stdin.nextToken());
            mCh = stdin.nextToken().charAt(0);
            ret = userSolution.playRound(mID, mCh);
            ans = Integer.parseInt(stdin.nextToken());
            if (ret != ans)
            {
//                System.out.println("!!!RET : " + ret + " ANS : " + ans);
//                System.out.println();
                ok = false;
            }
        }

        return ok;
    }

    public static void main(String[] args) throws Exception
    {
         System.setIn(new java.io.FileInputStream("src/SWEA/swea_14618/sample_input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stinit = new StringTokenizer(br.readLine(), " ");

        int T, MARK;
        T = Integer.parseInt(stinit.nextToken());
        MARK = Integer.parseInt(stinit.nextToken());

        for (int tc = 1; tc <= T; tc++)
        {
            int score = run() ? MARK : 0;
            System.out.printf("#%d %d\n", tc, score);
        }

        br.close();
    }
}