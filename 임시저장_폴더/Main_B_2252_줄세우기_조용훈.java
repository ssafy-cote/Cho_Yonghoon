package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B_2252_줄세우기_조용훈 {

	static int n, m;
	static List<Integer>[] map;
	static List<Integer> removeList;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder stringBuilder = new StringBuilder();

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new ArrayList[n + 1];
		visit = new boolean[n + 1];
		for (int i = 0; i < n + 1; i++) {
			map[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int value = Integer.parseInt(stringTokenizer.nextToken());
			int root = Integer.parseInt(stringTokenizer.nextToken());
			map[root].add(value);
		}
		int cnt = 1;
		while (true) {

			// 0 인놈들 줄세우기
			for (int i = 1; i < n + 1; i++) {
				if (map[i].size() == 0) {
					for (int k = 1; k < n + 1; k++) {
						map[k].remove(Integer.valueOf(i));
					}
					visit[i] = true;
				}
			}

			for (int i = 1; i < n + 1; i++) {
				if (map[i].size() == 0 && !visit[i]) {
					stringBuilder.append(i + " ");
				}
			}
			if (cnt == 0) {
				break;
			}

			cnt = 0;
			for (int i = 0; i < n + 1; i++) {
				if (map[i].size() != 0)
					++cnt;
			}
		}
		System.out.println(stringBuilder);
	}
}
