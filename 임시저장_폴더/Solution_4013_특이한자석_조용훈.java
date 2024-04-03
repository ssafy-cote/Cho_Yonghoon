package algo0403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석_조용훈 {

	static int ans, k;

	static int[][] list;

	static int mg1, mg2, mg3, mg4;

	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();

		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			k = Integer.parseInt(stringTokenizer.nextToken());
			ans = 0;
			list = new int[5][9];

			for (int i = 1; i <= 4; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 1; j <= 8; j++) {
					list[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
				list[i][0] = 1;
			}

			for (int i = 0; i < k; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				int index = Integer.parseInt(stringTokenizer.nextToken());
				boolean command = true;
				if (Integer.parseInt(stringTokenizer.nextToken()) == 1) {
					command = false;
				}
				visit = new boolean[5];
				spin(index, command);
				checkSum();
			}

			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static void spin(int index, boolean command) {
		if (4 < index || index < 1) {
			return;
		}
		if (visit[index]) {
			return;
		}
		visit[index] = true;

		// 시계방향
		int right = ((list[index][0] + 2) % 8);
		int left = ((list[index][0] + 6) % 8);
		if (command) {
			list[index][0] = ((list[index][0] + 1) % 8);
		} else { // 반시계
			list[index][0] = ((list[index][0] + 7) % 8);
		}
		if (index > 1 && list[index - 1][((list[index - 1][0] + 2) % 8)] == list[index][left]) {
			spin(index - 1, !command);
		}
		if (index < 4 && list[index + 1][((list[index + 1][0] + 6) % 8)] == list[index][right]) {
			spin(index + 1, !command);
		}
		spin(index - 1, !command);
		spin(index + 1, !command);
	}

	static void checkSum() {
		if (list[0][mg1] == 1) {
			ans += 1;
		}
		if (list[1][mg2] == 1) {
			ans += 2;
		}
		if (list[2][mg3] == 1) {
			ans += 4;
		}
		if (list[3][mg4] == 1) {
			ans += 8;
		}
	}

}
