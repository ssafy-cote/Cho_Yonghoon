package algo0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16928_뱀사다리_0227_dfs시간초과 {

	static int N, M;
	static int[] map = new int[101];
	static Move[] ladder;
	static Move[] snake;
	static int ans;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());

		visit = new boolean[101];
		ladder = new Move[16];
		snake = new Move[16];
		// 양수: 사다리 시작 부분 (해당 사다리 인덱스)
		// 음수: 뱀 시작 부분 (절대값이 해당 뱀 인덱스)
		// 0 인덱스는 사용 X
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			map[start] = cnt;
			ladder[cnt++] = new Move(start, end);
		}

		cnt = 1;
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int start = Integer.parseInt(stringTokenizer.nextToken());
			int end = Integer.parseInt(stringTokenizer.nextToken());
			map[start] = -cnt;
			snake[cnt++] = new Move(start, end);
		}

//		System.out.println(Arrays.toString(map));

		ans = Integer.MAX_VALUE;
		visit[1] = true;
		go(1, 0);

		System.out.println(ans);

	}

	static void go(int nowPos, int cnt) {
		if (nowPos + 6 >= 100) {
			ans = Math.min(ans, cnt + 1);
			return;
		}
		for (int i = nowPos + 1; i <= nowPos + 6; i++) {
			if (!visit[i]) {
				if (map[i] > 0) {
					visit[i] = true;
					go(ladder[map[i]].end, cnt + 1);
					visit[i] = false;
				} else if (map[i] < 0) {
					visit[i] = true;
					go(snake[Math.abs(map[i])].end, cnt + 1);
					visit[i] = false;
				}
			}
		}
		int index = nowPos + 6;
		while (index > nowPos) {
			if (!visit[index]) {
				visit[index] = true;
				go(index, cnt + 1);
				visit[index] = false;
				break;
			}
			--index;
		}
	}

	static class Move {
		int start;
		int end;

		public Move(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}
