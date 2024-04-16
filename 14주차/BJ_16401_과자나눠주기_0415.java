import java.io.*;
import java.util.*;

public class BJ_16401_과자나눠주기_0415 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken()); // 조카 수
        int N = Integer.parseInt(st.nextToken()); // 과자 수
        int[] L = new int[N]; // 과자의 길이를 담을 배열
        st = new StringTokenizer(br.readLine());
        int max = 0; // 길이 최대값
        for (int i = 0; i < N; i++) {
            L[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, L[i]);
        }

        System.out.println(binarySearch(L, M, 1, max));;
    }

    static int binarySearch(int[] L, int M, int left, int right) {
        int result = 0;
        while (left <= right) {
            int mid =  left + (right - left) / 2;
            int cnt = 0;

            for (int i = 0; i < L.length; i++) {
            	// 중앙값으로 과자의 길이를 나누어 나오는 갯수 파악
                cnt += L[i] / mid;
            }

            if (cnt >= M) {
            	// 위에서 나눈 갯수가 조키수보다 같거나 많을 경우
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
}