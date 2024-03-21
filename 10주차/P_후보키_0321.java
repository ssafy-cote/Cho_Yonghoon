import java.util.*;
import java.io.*;

class Solution {
    static int[] result;
    static int N,M;
    static int ans;
    static boolean[] visit;
    static ArrayList<String> ansList;
    public int solution(String[][] relation) {
        int answer = 0;
        N = relation.length;
        M = relation[0].length;
        // 조합을 만든다 (1 ~ M개를 선택하는)
        // 1개를 선택하는 조합 먼저 하고 여기서 후보키가 되는 행은 다음 조합에서 제외
        ans = 0;
        visit = new boolean[M];
        ansList = new ArrayList<>();
        for(int i = 1; i <= M; i++){
            result = new int[i];
            combi(i, 0, 0, relation);
        }
        // System.out.println(ans);
        
        ArrayList<String> real = new ArrayList<>();
        for(String str : ansList){
            real.add(str);
        }
        int size = ansList.size();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                String[] sub = ansList.get(i).split("");
                StringBuilder builder = new StringBuilder();
                builder.append("(.*)");
                for(int k = 0; k < sub.length; k++){
                    builder.append(sub[k]).append("(.*)");
                }
                if(ansList.get(j).equals(ansList.get(i))){
                    continue;
                }
                if(ansList.get(j).matches(builder.toString())){
                    real.remove(ansList.get(j));
                }
            }
        }
        
        return real.size();
    }
    
    static void combi(int len, int start, int index, String[][] relation){
        if(index == len){
            System.out.println(Arrays.toString(result));
            check(relation);
            return;
        }
        for(int i = start; i < M; i++){
            if(visit[i]){
                continue;
            }
            result[index] = i;
            combi(len, i+1, index+1, relation);
        }
    }
    static void check(String[][] relation){
        Set<String> keys = new HashSet<>();
        for(int i = 0; i < N; i++){
            StringBuilder builder = new StringBuilder();
            for(int j : result){
                builder.append(relation[i][j]);
            }
            if(!keys.add(builder.toString())){
                return;
            }
        }
        StringBuilder builder = new StringBuilder();
        for(int i : result){
            builder.append(i);
        }
        ansList.add(builder.toString());
        ++ans;
    }
}