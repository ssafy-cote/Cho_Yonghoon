import java.util.*;
import java.io.*;

class Solution {
    static int[] parents;
    public int solution(int n, int[][] wires) {
        int answer = 100;

        for(int i = 0; i < wires.length; i++){
            init(n);
            for(int j = 0; j < wires.length; j++){
                if(j == i)  continue;
                union(wires[j][0], wires[j][1]);
            }
            int team1 = 1;
            int team2 = 0;
            int number = find(1);
            for(int j = 2; j < n+1; j++){
                if(number == find(j)){
                    team1++;
                }else{
                    team2++;
                }
            }
            System.out.println(Arrays.toString(parents));
            answer = Math.min(answer, Math.abs(team1-team2));
        }
        return answer;
    }
    static void init (int n) {
        parents = new int[n+1];
        for(int i = 1; i < n + 1; i++){
            parents[i] = i;
        }
    }
    
    static int find (int a) {
        if (a == parents[a]){
            return a;
        }
        return parents[a] = find(parents[a]);
    }
    
    static void union (int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa > pb){
            parents[pa] = pb;
        }else{
            parents[pb] = pa;
        }
    }
}