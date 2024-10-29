package SWEA.swea_14618;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

class UserSolution {
    boolean[] players;
    int leftPlayers, totalPlayers;
    HashMap<Character, TreeSet<String>> wordStartsWith = new HashMap<>();
    HashMap<String, String> selected = new HashMap<>();
    ArrayList<String> nextWords = new ArrayList<>();
    
    public void init(int N, int M, char[][] mWords)
    {
        this.leftPlayers = N;
        totalPlayers = N;

        players = new boolean[N];
        Arrays.fill(players, true);
        wordStartsWith.clear();
        selected.clear();
        nextWords.clear();
        for(int i=0; i<M; i++) {
        	char[] word = mWords[i];
            String string = "";
            for(char c:word) string+=c;
            if(!wordStartsWith.containsKey(word[0]))
                wordStartsWith.put(word[0], new TreeSet<>());
            wordStartsWith.get(word[0]).add(string.trim());
        }
    }

    public int playRound(int mID, char mCh)
    {
        if(leftPlayers==1) return mID;

        // next를 wordStartWith에 넣는다.
        for(String word: nextWords){
            char key = word.charAt(0);
            if(!wordStartsWith.containsKey(key))
                wordStartsWith.put(key, new TreeSet<>());
            wordStartsWith.get(key).add(word);
        }

        nextWords.clear();

        int player = mID;
        while(true){
            // System.out.println("WORDS : " + wordStartsWith);
            // System.out.println("mCh : " + mCh);
        	
            // 해시맵에서 mCh를 찾는다.
            if(!wordStartsWith.containsKey(mCh)) break;
            TreeSet<String> words = wordStartsWith.get(mCh);
            if(words.size()==0) break;

            String word = null;
            do{
                word = words.first();
                words.remove(word);

                // 이게 selected에 있는지 확인한다.
            } while(words.size()>0 && selected.containsKey(word));
            if(words.size()==0 && selected.containsKey(word)) break;

            // 없으면 selected에 추가
            selected.put(word, word);

            // 뒤집어서 nextWords에 추가한다.
            nextWords.add(reverse(word));

            // System.out.println("SELECT: " +word);
            // System.out.println("LENGTH OF " + word + " : " + word.length());
            mCh = word.charAt(word.length()-1);
            

            // 다음 플레이어로 넘긴다. 50 ->1
            player = (player % totalPlayers) + 1;
            while(!players[player - 1]){
                player = (player % totalPlayers) + 1;
            }
        }
        // 플레이어를 탈락시킨다.
        players[player-1] = false;
        leftPlayers--;
        return player;
    }

    String reverse(String word){
        String res = "";
        char[] wordChar = word.toCharArray();
        for(char c: wordChar)
            res = String.join("", Character.toString(c), res);

        return res;
    }
}