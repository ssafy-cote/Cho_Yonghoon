package algo0328;

import java.util.*;

class P_더맵게_0327 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i : scoville){
            q.add(i);
        }
        
        while(q.size() > 1){
            if(q.peek() >= K){
                return answer;
            }
            int a = q.poll();
            int b = q.poll();
            ++answer;
            q.add(a+(b*2));
        }
        if(q.peek() >= K){
            return answer;
        }
        return -1;
    }
}