import java.io.*;
import java.util.*;

class Solution {
    static int visit[];
    static int groupCnt;
    public int solution(int[] cards) {
        int answer = 0;
        
        visit = new int[cards.length];
        groupCnt = 0;
        for(int i = 0; i < cards.length; i++){
            if(visit[i] == 0){
                groupCnt++;
                makeGroup(i, cards);
            }
        }
        
        int[] list = new int[groupCnt + 1];
        for(int i = 0; i < visit.length; i++){
            list[visit[i]] += 1;
        }
        
        Arrays.sort(list);
        if(groupCnt > 1){
            answer = list[groupCnt-1] * list[groupCnt];
        }        
        return answer;
    }
    static void makeGroup (int index, int[] cards) {
        while (visit[index] == 0){
            visit[index] = groupCnt;
            index = cards[index] - 1;
        }
    }
}