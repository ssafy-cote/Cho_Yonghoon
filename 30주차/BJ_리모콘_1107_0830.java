import java.util.*;
import java.io.*;

public class BJ_리모콘_1107_0830 {

    static boolean[] isWork;
    static int n;
    static String number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        number = st.nextToken();
        n = Integer.parseInt(number);
        st = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(st.nextToken());
        int ans = 0;
        if (m == 0) {
            // 망가진게 없을 떄
            ans = Math.min(Math.abs(n - 100), number.length());
        } else {
            isWork = new boolean[10];
            st = new StringTokenizer(br.readLine(), " ");
            // 망가진 숫자 체크
            for (int i = 0; i < m; i++) {
                isWork[Integer.parseInt(st.nextToken())] = true;
            }

            // 만약 모두 다 망가졌을 때
            if (m == 10) {
                ans = Math.abs(n - 100);
            } else {
                int count = 0;

                while (true) {

                    if (n == 100) {
                        ans = 0;
                        break;
                    }

                    if (check(n - count)) {
                        ans = Math.min(Math.abs(n - 100),
                            count + String.valueOf(n - count).length());
                        break;
                    }

                    if (check(n + count)) {
                        ans = Math.min(Math.abs(n - 100),
                            count + String.valueOf(n + count).length());
                        break;
                    }
                    count++;
                }
            }
        }
        System.out.println(ans);
    }

    static boolean check(int num) {
        if (num < 0) {
            return false;
        }
        for (int i = 0; i < String.valueOf(num).length(); i++) {
            if (isWork[String.valueOf(num).charAt(i) - '0']) {
                return false;
            }
        }
        return true;
    }
}
