package com.edu.ssafy_0216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1260_DFS와BFS_조용훈_인접리스트 {

	static int N, M, V;
	static ArrayList<Integer>[] list;
	static boolean[] visit;
	static StringBuilder builder;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {	// 22128kb 메모리, 360ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		V = Integer.parseInt(stringTokenizer.nextToken());

		list = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int r = Integer.parseInt(stringTokenizer.nextToken());
			int c = Integer.parseInt(stringTokenizer.nextToken());
			list[c].add(r);
			list[r].add(c);
		}
		
		for(int i = 1; i <= N; i++) {
			list[i].sort((o1, o2) -> {
				return o1 - o2;
			});
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
		for (int num : list[start]) {
			if (!visit[num]) {
				visit[num] = true;
				builder.append(num + " ");
				dfs(num);
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
			for (int num : list[index]) {
				if (visit[num])
					continue;
				visit[num] = true;
				builder.append(num + " ");
				queue.add(num);
			}
		}

	}
}
