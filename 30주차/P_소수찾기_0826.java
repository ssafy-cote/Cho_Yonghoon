import java.util.*;
import java.io.*;

class Solution {
    static boolean[] visit;
    static int size;
    static HashSet<Integer> ans;
    public int solution(String numbers) {
        String[] map = numbers.split("");
        ans = new HashSet<>();
        
        size = map.length;
        
        
        for(int i = 1; i <= size; i++){
            visit = new boolean[size];
            permutation(0, i, "", map);
        }
        
        return ans.size();
    }
    
    static void permutation(int index, int limit, String result, String[] map) {
        
        if(index == limit){
            int temp = Integer.parseInt(result);
            if(isPrim(temp)){
                ans.add(temp);
            }
            return;
        }
        
        for(int i = 0; i < size; i++){
            if(!visit[i]){
                visit[i] = true;
                permutation(index + 1, limit, result + map[i], map);
                visit[i] = false;
            }
        }
        return;
    }
    
    static boolean isPrim(int n) {
        if(n < 2){
            return false;
        }
        for(int i = 2; i * i <= n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}
