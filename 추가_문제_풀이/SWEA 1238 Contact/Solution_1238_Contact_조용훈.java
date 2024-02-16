package com.edu.ssafy_0216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_1238_Contact_조용훈 {
	static StringBuilder builder = new StringBuilder();
	static int start, n;
	static boolean[] visit;
	static ArrayList<Integer>[] list;
	static int[] val;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {	// 18,256kb 메모리, 105ms 시간
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		for (int T = 1; T <= 10; T++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

			n = Integer.parseInt(stringTokenizer.nextToken());
			start = Integer.parseInt(stringTokenizer.nextToken());
			list = new ArrayList[101];
			for (int i = 0; i < 101; i++) {
				list[i] = new ArrayList<>();
			}
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int i = 0; i < n / 2; i++) {
				int r = Integer.parseInt(stringTokenizer.nextToken());
				int c = Integer.parseInt(stringTokenizer.nextToken());
				list[r].add(c);
			}
			val = new int[101];
			visit = new boolean[101];
			bfs();
			int temp = 0;
			int answer = 0;
			for (int i = 0; i < 101; i++) {
				if (temp <= val[i]) {
					temp = val[i];
					answer = i;
				}
			}
			builder.append("#").append(T).append(" ").append(answer).append("\n");
		}
		System.out.println(builder);
	}

	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		visit[start] = true;

		while (!queue.isEmpty()) {
			int index = queue.poll();
			for (int n : list[index]) {
				if (visit[n]) {
					continue;
				}
				visit[n] = true;
				val[n] = val[index] + 1;
				queue.add(n);

			}
		}

	}

}
