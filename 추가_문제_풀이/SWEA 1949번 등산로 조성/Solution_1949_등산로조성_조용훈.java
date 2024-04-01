package algo0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_1949_등산로조성_조용훈 {	// 33,772kb 메모리, 131ms 시간
 
    static int ans, n, k;
    static int[][] map;
    static int[][] tmap;
    static int max;
    static int[][] ansMap;
 
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static boolean[][] visit;
 
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
 
        int t = Integer.parseInt(stringTokenizer.nextToken());
        StringBuilder builder = new StringBuilder();
 
        for (int T = 1; T <= t; T++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            n = Integer.parseInt(stringTokenizer.nextToken());
            k = Integer.parseInt(stringTokenizer.nextToken());
 
            map = new int[n][n];
            ansMap = new int[n][n];
 
            max = 0;
            for (int i = 0; i < n; i++) {
                stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                    max = (max < map[i][j]) ? map[i][j] : max;
 
                }
            }
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == max) {
                        int[][] ttmap = new int[n][n];
                        for (int p = 0; p < n; p++) {
                            for (int q = 0; q < n; q++) {
                                ttmap[p][q] = map[p][q];
                            }
                        }
                        visit = new boolean[n][n];
                        visit[i][j] = true;
                        go(i, j, i, j, 1, false, ttmap);
                    }
                }
            }
            ans = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    ans = (ans < ansMap[i][j]) ? ansMap[i][j] : ans;
                }
            }
            builder.append("#").append(T).append(" ").append(ans).append("\n");
        }
        System.out.println(builder);
    }
 
    static void go(int startX, int startY, int x, int y, int cnt, boolean useSkill, int[][] tempMap) {
        ansMap[startX][startY] = (ansMap[startX][startY] < cnt) ? cnt : ansMap[startX][startY];
        int[][] ttmap = new int[n][n];
        for (int p = 0; p < n; p++) {
            for (int q = 0; q < n; q++) {
                ttmap[p][q] = tempMap[p][q];
            }
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
             
            if (check(nx, ny) && !visit[nx][ny]) {
                if (tempMap[nx][ny] < tempMap[x][y]) {
                    visit[nx][ny] = true;
                    go(startX, startY, nx, ny, cnt + 1, useSkill, ttmap);
                    visit[nx][ny] = false;
                } else if (tempMap[nx][ny] >= tempMap[x][y] && !useSkill) {
                    if (tempMap[nx][ny] - tempMap[x][y] + 1 <= k) {
                        ttmap[nx][ny] -= (tempMap[nx][ny] - tempMap[x][y] + 1);
                        visit[nx][ny] = true;
                        go(startX, startY, nx, ny, cnt + 1, true, ttmap);
                        visit[nx][ny] = false;
                    }
                }
            }
        }
    }
 
    static boolean check(int x, int y) {
        return (-1 < x && x < n && -1 < y && y < n);
    }
 
}