package algo0328;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution_2115_벌꿀채취_조용훈 {	// 26,240kb 메모리, 126ms 실행시간

	static int N, W, L;

	static int[][] map;
	static int[][] ansMap;

	static int[] result;

	static int combiMax;
	static int combiMax2;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int t = Integer.parseInt(stringTokenizer.nextToken());

		StringBuilder builder = new StringBuilder();

		for (int T = 1; T <= t; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			W = Integer.parseInt(stringTokenizer.nextToken());
			L = Integer.parseInt(stringTokenizer.nextToken());

			map = new int[N][N];
			ansMap = new int[N][N];

			for (int i = 0; i < N; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= (N - W); j++) {
					findMax(i, j);
				}
			}

			int ans = 0;

			for (int i = 0; i < (N * N) - 1; i++) {
				for (int j = i; j < (N * N); j++) {

					int aX = i / N;
					int aY = i % N;
					int bX = j / N;
					int bY = j % N;
					if (aX == bX && Math.abs(aY - bY) <= W) {
						continue;
					}
					ans = (ansMap[aX][aY] + ansMap[bX][bY] > ans) ? ansMap[aX][aY] + ansMap[bX][bY] : ans;
				}
			}
			builder.append("#").append(T).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	private static void findMax(int x, int y) {

		int[] templist = new int[W];

		int sum = 0;
		for (int i = y; i < (y + W); i++) {
			templist[i - y] = map[x][i];
			sum += map[x][i];
		}
		int result = 0;
		if (L < sum) {
			combiMax2 = 0;
			ArrayList<Integer> maxList = new ArrayList<>();
			for (int i = W - 1; i > -1; i--) {
				maxList.add(makeCombi(i, templist));
			}
			Collections.sort(maxList, Collections.reverseOrder());
			ansMap[x][y] = maxList.get(0);
		} else {
			for (int i = 0; i < W; i++) {
				result += (templist[i] * templist[i]);
			}
			ansMap[x][y] = result;
		}
	}

	static int makeCombi(int i, int[] list) {

		result = new int[i];
		combiMax = -1;
		combi(0, 0, i, list);
		return combiMax;
	}

	static void combi(int start, int index, int depth, int[] list) {

		if (depth == index) {
			int sum = 0;
			int s = 0;
			for (int i = 0; i < result.length; i++) {
				sum += (result[i] * result[i]);
				s += result[i];
			}
			if (s > L) {
				return;
			}
			combiMax = (combiMax < sum) ? sum : combiMax;
			return;
		}

		for (int i = start; i < W; i++) {
			result[index] = list[i];
			combi(i + 1, index + 1, depth, list);
		}

	}

	static class Pos {
		int x;
		int y;
		int sum;

		public Pos(int x, int y, int sum) {
			this.x = x;
			this.y = y;
			this.sum = sum;
		}
	}
}
