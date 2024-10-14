package BOJ.boj_2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        bf.readLine();
        System.out.println(solve());
    }

    private static String solve() throws IOException{
        LinkedList<Integer> result = new LinkedList<>();
        Stack<Building> stack = new Stack<>();

        StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());

        int index = 1;
        while(stringTokenizer.hasMoreTokens()){
            Building current_building = new Building(index, Integer.parseInt(stringTokenizer.nextToken()));
            while(!stack.empty() && stack.peek().height < current_building.height){
                stack.pop();
            }
            if(stack.empty()){
                stack.push(current_building);
                result.add(0);
            } else {
                result.add(stack.peek().index);
                stack.push(current_building);
            }
            index ++;
        }

        return result.toString().replaceAll("[,\\[\\]]", "");
    }

    static class Building{
        int index;
        int height;
        Building(int index, int height){
            this.index = index;
            this.height = height;
        }
    }
}
