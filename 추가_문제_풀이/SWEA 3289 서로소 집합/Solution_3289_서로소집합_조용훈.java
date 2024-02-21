package algo0221;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3289_서로소집합_조용훈 {
	static int[] map;
	static int N, M;

	public static void main(String[] args) throws IOException {	// 104,436kb 메모리, 500ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder builder = new StringBuilder();
		int t = Integer.parseInt(stringTokenizer.nextToken());

		for (int T = 1; T <= t; T++) {
			StringBuilder builder2 = new StringBuilder();
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			N = Integer.parseInt(stringTokenizer.nextToken());
			M = Integer.parseInt(stringTokenizer.nextToken());

			map = new int[N + 1];
			for (int i = 1; i < N + 1; i++) {
				map[i] = i;
			}
			for (int i = 0; i < M; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				int command = Integer.parseInt(stringTokenizer.nextToken());
				int a = Integer.parseInt(stringTokenizer.nextToken());
				int b = Integer.parseInt(stringTokenizer.nextToken());
				if (command == 1) {
					if (find(a) == find(b)) {
						builder2.append(1);
					} else {
						builder2.append(0);
					}
				} else {
					union(a, b);
				}

			}

			builder.append("#").append(T).append(" ").append(builder2).append("\n");
		}
		System.out.println(builder);

	}

	static void union(int a, int b) {
		int aP = find(a);
		int bP = find(b);

		if (aP == bP) {
			return;
		}
		map[bP] = aP;
	}

	static int find(int a) {
		if (map[a] != a) {
			map[a] = find(map[a]);
		}
		return map[a];
	}

}
