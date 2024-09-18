import java.io.*;
import java.util.*;

class Solution {
    static boolean[] visit;
    static int answer;
    public int solution(int x, int y, int n) {
        answer = -1;
        visit = new boolean[y+1];
        bfs(x, y, n);
        return answer;
    }
    
    static void bfs (int x, int y, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(x);
        int cnt = 0;
        A : while(!q.isEmpty()){
            int size = q.size();
            cnt++;
            while (size-- > 0){
                int now = q.poll();
                if(now + n <= y && !visit[now + n]){
                    if(now + n == y){
                        answer = cnt;
                        break A;
                    }
                    visit[now + n] = true;
                    q.add(now  +n);
                }
                if(now * 2 <= y && !visit[now * 2]){
                    if(now * 2 == y){
                        answer = cnt;
                        break A;
                    }
                    visit[now * 2] = true;
                    q.add(now * 2);
                }
                if(now * 3 <= y && !visit[now * 3]){
                    if(now * 3 == y){
                        answer = cnt;
                        break A;
                    }
                    visit[now * 3] = true;
                    q.add(now * 3);
                }
            }
        }
        if(x == y){
            answer = 0;
        }
    }
}