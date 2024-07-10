import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2583_영역구하기_0710 {
    static int n;
    static int m;
    static int k;
    static int ans;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static int[][] map;
    static boolean[][] visit;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new boolean[n][m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = (n-1) - Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = (n-1) - (Integer.parseInt(st.nextToken()) - 1);

            for(int p = y2; p <= y1; p++) {
                for(int q = x1; q <= x2; q++) {
                    map[p][q] = 1;
                }
            }
        }
        ans = 0;
        list = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visit[i][j] && map[i][j] == 0) {
                    bfs(i,j);
                }
            }
        }
        System.out.println(ans);
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++){
            System.out.print(list.get(i) + " ");
        }
    }

    static void bfs(int i, int j) {
        ans++;
        Queue<Pos> q = new ArrayDeque<Pos>();
        q.add(new Pos(i,j));
        visit[i][j] = true;
        int cnt = 1;
        while (!q.isEmpty()){
            Pos pos = q.poll();

            for(int k = 0; k < 4; k++) {
                int nx = pos.x + dx[k];
                int ny = pos.y + dy[k];
                if (check(nx,ny)) {
                    visit[nx][ny] = true;
                    cnt++;
                    q.add(new Pos(nx, ny));
                }
            }
        }
        list.add(cnt);
    }

    static class Pos {
        int x;
        int y;
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static boolean check(int i, int j) {
        return i >= 0 && i < n && j >= 0 && j < m && !visit[i][j] && map[i][j] == 0;
    }
}
