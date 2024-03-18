import java.util.*;
import java.io.*;

class Solution {
    static ArrayList<Node>[] map;
    static boolean[] visit;
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        visit = new boolean[N+1];
        map = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            map[i] = new ArrayList<>();
        }
        for(int[] num : road){
            int start = num[0];
            int end = num[1];
            int value = num[2];
            map[start].add(new Node(end, value));
            map[end]. add(new Node(start, value));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] ans = new int[N+1];
        Arrays.fill(ans, Integer.MAX_VALUE);
        ans[1] = 0;
        pq.offer(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node node = pq.poll();
            if(visit[node.end])
                continue;
            visit[node.end] = true;
            for(Node n : map[node.end]){
                if(ans[n.end] > ans[node.end] + n.value){
                    ans[n.end] = ans[node.end] + n.value;
                    pq.offer(new Node(n.end, ans[n.end]));
                }
            }
            System.out.println(Arrays.toString(ans));
        }
        
        for(int n : ans){
            if(K >= n){
                ++answer;
            }
        }
        System.out.println(Arrays.toString(ans));
        return answer;
    }
    
    static class Node implements Comparable<Node>{
        int end;
        int value;
        public Node(int end, int value) {
            this.end = end;
            this.value = value;
        }
        
        @Override
	    public int compareTo(Node o) {
		    return Integer.compare(this.value, o.value);
	    }
    }
}