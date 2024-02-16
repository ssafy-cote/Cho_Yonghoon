package com.edu.ssafy_0216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1260_DFS와BFS_조용훈_인접행렬 {

	static int N, M, V;
	static boolean[][] map;
	static boolean[] visit;
	static StringBuilder builder;

	public static void main(String[] args) throws Exception { // 18700kb 메모리, 212ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		V = Integer.parseInt(stringTokenizer.nextToken());

		map = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int r = Integer.parseInt(stringTokenizer.nextToken());
			int c = Integer.parseInt(stringTokenizer.nextToken());
			map[r][c] = true;
			map[c][r] = true;
		}

		builder = new StringBuilder();
		visit = new boolean[N + 1];
		visit[V] = true;
		builder.append(V + " ");
		dfs(V);
		builder.append("\n");
		bfs(V);
		System.out.println(builder);
	}

	static void dfs(int start) {
		for (int i = 1; i <= N; i++) {
			if (map[start][i]) {
				if (!visit[i]) {
					visit[i] = true;
					builder.append(i + " ");
					dfs(i);
				}
			}
		}
	}

	static void bfs(int start) {
		visit = new boolean[N + 1];
		visit[start] = true;
		builder.append(start + " ");
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		while (!queue.isEmpty()) {
			int index = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (map[index][i]) {
					if (!visit[i]) {
						visit[i] = true;
						builder.append(i + " ");
						queue.add(i);
					}
				}
			}
		}

	}
}
