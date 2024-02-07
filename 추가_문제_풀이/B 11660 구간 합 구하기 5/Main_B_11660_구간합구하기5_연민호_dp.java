package algo230131;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_11660_구간합구하기5_연민호_dp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 판 크기
		int M = Integer.parseInt(st.nextToken()); // 합을 구하는 횟수

		// 해당 정보를 2차원 배열에 누적합으로 담기
		// dp[3][2]
		// (0,0) ~ (3,2)까지의 사각형 만큼의 구간합을 의미
		int[][] dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				dp[i][j] = dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - 1] + Integer.parseInt(st.nextToken());
			}
		}

//		System.out.println(Arrays.deepToString(arr));
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int sum = dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1];
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}
}