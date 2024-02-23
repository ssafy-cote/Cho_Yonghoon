package algo0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_B_17281_야구_조용훈 {
	static int[] number = { 2, 3, 4, 5, 6, 7, 8, 9 }; // 크기 8
	static ArrayList<Integer> order;
	static boolean[] visit;
	static int[] result;
	static int inning;
	static int[][] hitInfo;
	static int nextPlayerIndex;
	static int score;
	static int ans;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		inning = Integer.parseInt(stringTokenizer.nextToken());
		hitInfo = new int[inning + 1][9];
		for (int i = 1; i <= inning; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				hitInfo[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}
		visit = new boolean[8];
		result = new int[9];
		ans = 0;
		makeP(0);
		System.out.println(ans);
	}

	static void makeP(int index) {

		if (index == 8) {
			result[3] = 1;
			doGame();
			ans = (ans < score) ? score : ans;
			return;
		}

		for (int i = 0; i < 8; i++) {
			if (!visit[i]) {
				visit[i] = true;
				if (index > 2) {
					result[index + 1] = number[i];
				} else {
					result[index] = number[i];
				}
				makeP(index + 1);
				visit[i] = false;
			}
		}
	}

	static int doGame() {
		cnt = 0;
		nextPlayerIndex = result[0];
		score = 0;
		for (int i = 0; i < inning; i++) {
			doInning(i);
		}
		return score;
	}

	// 다음 타자 얻는 함수
	static void nextPlayer() {
		// 순환작업
		if (++cnt == 9) {
			cnt = 0;
		}
		nextPlayerIndex = result[cnt];
	}

	static void doInning(int index) {
		boolean[] roo = new boolean[4];
		int outCnt = 0;

		while (outCnt < 3) {
			int command = hitInfo[index + 1][nextPlayerIndex - 1];
			roo[0] = true;
			switch (command) {
			case 0:
				++outCnt;
				break;
			case 1:
				// 진루 밀어내기 코드
				for (int i = 3; i > -1; i--) {
					if (roo[i]) {
						roo[i] = false;
						if (i == 3) {
							++score;
						} else {
							roo[i + 1] = true;
						}
					}
				}
				break;
			case 2:
				for (int i = 3; i > -1; i--) {
					if (roo[i]) {
						roo[i] = false;
						if (i == 3 || i == 2) {
							++score;
						} else {
							roo[i + 2] = true;
						}
					}
				}
				break;
			case 3:
				for (int i = 3; i > -1; i--) {
					if (roo[i]) {
						roo[i] = false;
						if (i == 3 || i == 2 || i == 1) {
							++score;
						} else {
							roo[i + 3] = true;
						}
					}
				}
				break;
			case 4:
				for (int i = 0; i < 4; i++) {
					if (roo[i]) {
						++score;
						roo[i] = false;
					}
				}
				break;
			}
			nextPlayer();
		}
	}

}
