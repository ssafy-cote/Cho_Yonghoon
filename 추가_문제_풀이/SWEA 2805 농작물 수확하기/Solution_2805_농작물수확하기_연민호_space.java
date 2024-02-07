package algo230130;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2805_농작물수확하기_연민호_space {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		System.setIn(new FileInputStream("input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			final int N = Integer.parseInt(br.readLine());

			// 농장
			int[][] farm = new int[N][N];
			// 농작물의 가치 입력
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					farm[i][j] = s.charAt(j) - '0'; // char 숫자 => int
				}
			}

			int center = N / 2; // 중앙위치값
			int space = N / 2; // 공백의 크기

			int value = 0; // 총 가치

			// 행의 크기만큼 반복
			for (int i = 0; i < N; i++) {

				int width = N - (space * 2); // 수확할 너비
				// 공백 다음 인덱스부터 width만큼 수확
				for (int j = space; j <= space + width - 1; j++) {
					value += farm[i][j];
				}

				// 가운데 행 이전엔 공백 +1
				if (i < center)
					space--;
				// 가운데 행부터 공백 -1
				else
					space++;
			}
			sb.append("#" + tc + " " + value + "\n");
		}
		System.out.println(sb.toString());
	}
}