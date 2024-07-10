import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11048_이동하기_0710 {
    static int n, m;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+2][m+2];
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] ans = new int[n+2][m+2];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                int temp = Math.max(ans[i-1][j-1], ans[i][j-1]);
                temp = Math.max(temp, ans[i-1][j]);
                ans[i][j] = temp + map[i][j];
            }
        }
        System.out.println(ans[n][m]);
    }
}
