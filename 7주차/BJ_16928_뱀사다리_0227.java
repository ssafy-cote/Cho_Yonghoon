package algo0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16928_뱀사다리_0227 {

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

		ans = Integer.MAX_VALUE;
		go(1);
		System.out.println(ans);

	}

	static void go(int nowPos) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(nowPos);
		visit = new boolean[101];
		int pos = nowPos;
		int cnt = 0;
		A: while (!queue.isEmpty()) {
			int size = queue.size();
			while (size-- > 0) {
				pos = queue.poll();
				if (pos + 6 >= 100) {
					ans = cnt + 1;
					break A;
				}
				for (int i = pos + 1; i <= pos + 6; i++) {
					if (!visit[i]) {
						visit[i] = true;
						if (map[i] != 0) {
							if (map[i] > 0) {
								queue.add(ladder[map[i]].end);
							} else {
								queue.add(snake[Math.abs(map[i])].end);
							}
						} else {
							queue.add(i);
						}
					}
				}
			}
			++cnt;
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
