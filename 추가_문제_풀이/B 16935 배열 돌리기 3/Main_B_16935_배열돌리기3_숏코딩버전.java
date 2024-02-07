package algo240207;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:118440KB, 시간:456ms
 */
public class Main_B_16935_배열돌리기3_숏코딩버전 {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로직
        int[] ops = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int op : ops) {
            rotate(op);
        }

        // 출력부
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void rotate(int mode) {
        switch (mode) {
            case 1:
                op1();
                break;
            case 2:
                op2();
                break;
            case 3:
                op3();
                break;
            case 4:
                op4();
                break;
            case 5:
                op5();
                break;
            case 6:
                op6();
                break;
        }
    }

    static void op1() {
        int[][] newMap = new int[N][M];
        for (int i = 0; i < N; i++) newMap[i] = map[N - i - 1];
        map = newMap;
    }

    static void op2() {
        op3();
        op1();
        op4();
    }

    static void op3() {
        int[][] newMap = new int[M][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newMap[j][N - 1 - i] = map[i][j];
            }
        }
        map = newMap;
        N = map.length;
        M = map[0].length;
    }

    static void op4() {
        op3();
        op3();
        op3();
    }

    static void op5() {
        int[][] newMap = new int[N][M];

        write(0, 0, N / 2, 0, newMap);
        write(0, M / 2, 0, 0, newMap);
        write(N / 2, M / 2, 0, M / 2, newMap);
        write(N / 2, 0, N / 2, M / 2, newMap);
        map = newMap;
    }

    static void op6() {
        op5();
        op5();
        op5();
    }

    private static void write(int x1, int y1, int x2, int y2, int[][] newMap) {
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                newMap[x1 + i][y1 + j] = map[x2 + i][y2 + j];
            }
        }
    }
}