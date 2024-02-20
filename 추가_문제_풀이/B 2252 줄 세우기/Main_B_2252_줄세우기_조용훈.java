package algo2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_2252_줄세우기_조용훈 {

	static int n, m;
	static List<Integer>[] map;
	static List<Integer> removeList;
	static int[] degree;

	public static void main(String[] args) throws IOException { // 52872kb 메모리, 416ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		StringBuilder stringBuilder = new StringBuilder();

		n = Integer.parseInt(stringTokenizer.nextToken());
		m = Integer.parseInt(stringTokenizer.nextToken());

		map = new ArrayList[n + 1];
		degree = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			map[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int value = Integer.parseInt(stringTokenizer.nextToken());
			int root = Integer.parseInt(stringTokenizer.nextToken());
			map[value].add(root);
			degree[root] += 1;
		}

		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (degree[i] == 0) {
				queue.add(i);
				stringBuilder.append(i + " ");
			}
		}
		
		while (!queue.isEmpty()) {
			int number = queue.poll();
			for (int i = 0; i < map[number].size(); i++) {
				degree[map[number].get(i)] -= 1;
				if (degree[map[number].get(i)] == 0) {
					queue.add(map[number].get(i));
					stringBuilder.append(map[number].get(i) + " ");
				}
			}
		}
		System.out.println(stringBuilder);
	}
}
