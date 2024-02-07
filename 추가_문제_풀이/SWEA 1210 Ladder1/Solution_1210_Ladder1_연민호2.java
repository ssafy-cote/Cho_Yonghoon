package algo230130;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1210_Ladder1_연민호2 {
	static int[][] ladder;
	final static int N = 100; // 사다리 크기
	static int col; // 열 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			tc = Integer.parseInt(br.readLine());

			ladder = new int[N][N];

			// ladder 값 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			col = 0; // 초기화

			// step 01. 2탐색
			for (int i = 0; i < N; i++) {
				if (ladder[N - 1][i] == 2) {
					col = i; // 2를 찾았을 때 열 정보 세팅
					break;
				}
			}

			// step 02. 위로 이동(행크기를 감소)
			for (int row = N - 2; row >= 0; row--) {

				// 왼쪽이 1인 경우 - 왼쪽이동
				if (col - 1 >= 0 && ladder[row][col - 1] == 1)
					moveLeft(row);

				// 오른쪽이 1인 경우 - 오른쪽이동
				else if (col + 1 < N && ladder[row][col + 1] == 1)
					moveRight(row);

			}
			// 3. 이동 마치고 col 값 출력
			sb.append("#" + tc + " " + col + "\n");
		}
		System.out.println(sb);
	}

	// row 행에서 오른쪽으로 이동
	private static void moveRight(int row) {

		while (true) {
			col++; // 오른쪽으로 이동

			// 현재 열이 오른쪽 끝열이거나 다음 오른쪽 값이 0인 경우 오른쪽 이동 멈춤
			if (col == N - 1 || ladder[row][col + 1] == 0)
				return;
		}
	}

	// row 행에서 왼쪽으로 이동
	private static void moveLeft(int row) {
		while (true) {
			col--;// 왼쪽 이동
			// 현재 열이 0이거나 다음 왼쪽 값이 0인경우 왼쪽 이동 멈춤
			if (col == 0 || ladder[row][col - 1] == 0)
				return;
		}
	}

}