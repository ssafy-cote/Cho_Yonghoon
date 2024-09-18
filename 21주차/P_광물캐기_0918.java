import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int index = 0;
        int size = 0;
        int temp = 0;
        for(int i = 0; i < 3; i++){
            temp += picks[i];
        }
        int maxIndex = minerals.length;
        if(temp * 5 < minerals.length){
            maxIndex = temp * 5;
        }
        if(maxIndex % 5 != 0){
            size = 1;
        }
        int[][] total = new int[(maxIndex / 5) + size][2];
        
        while(index < maxIndex){
            
            int sum = 0;
            for(int i = 0; i < 5; i++){
                if(index+i < maxIndex){
                    if(minerals[index+i].equals("diamond")){
                        sum += 25;
                    }else if(minerals[index+i].equals("iron")){
                        sum += 5;
                    }else if(minerals[index+i].equals("stone")){
                        sum += 1;
                    }
                }
            }
            
            total[index / 5][0] = sum;
            
            index+=5;
        }
        
        PriorityQueue<What> pq = new PriorityQueue<>();
        
        for(int i = 0; i < total.length; i++){
            pq.add(new What(i, total[i][0]));
        }
        
        while(!pq.isEmpty()){
            What w = pq.poll();
            if(picks[0] > 0){
                picks[0] -= 1;
                total[w.index][1] = 1;
            }else if(picks[1] > 0){
                picks[1] -= 1;
                total[w.index][1] = 2;
            }else if(picks[2] > 0){
                picks[2] -= 1;
                total[w.index][1] = 3;
            }else{
                break;
            }
        }
        
        for(int i = 0; i < maxIndex; i++){
            if(total[i / 5][1] == 1){
                answer += 1;
            }else if(total[i / 5][1] == 2){
                if(minerals[i].equals("diamond")){
                    answer += 5;
                }else{
                    answer += 1;
                }
            }else if(total[i / 5][1] == 3){
                if(minerals[i].equals("diamond")){
                    answer += 25;
                }else if(minerals[i].equals("iron")){
                    answer += 5;
                }else{
                    answer += 1;
                }
            }
        }
        
        return answer;
    }
    
    static class What implements Comparable<What>{
        int index;
        int sum;
        
        public What (int index, int sum){
            this.index = index;
            this.sum = sum;
        }
        
        @Override
        public int compareTo (What w){
            return -(this.sum - w.sum);
        }
    }
}