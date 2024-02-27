package algo0227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.xml.transform.Templates;

public class Main_B_1753_최단경로_조용훈 {
	static int start;
	static int N, M;
	static int[] map;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		N = Integer.parseInt(stringTokenizer.nextToken());
		M = Integer.parseInt(stringTokenizer.nextToken());
		stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
		start = Integer.parseInt(stringTokenizer.nextToken());
		list = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			int a = Integer.parseInt(stringTokenizer.nextToken());
			int b = Integer.parseInt(stringTokenizer.nextToken());
			int value = Integer.parseInt(stringTokenizer.nextToken());
			list[a].add(new Node(b, value));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visit = new boolean[N + 1];
		map = new int[N + 1];
		Arrays.fill(map, Integer.MAX_VALUE);
		map[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node nowNode = pq.poll();

			if (visit[nowNode.end]) {
				continue;
			}
			
			visit[nowNode.end] = true;
			
			for (Node endNode : list[nowNode.end]) {
				if (map[endNode.end] > map[nowNode.end] + endNode.value) {
					map[endNode.end] = map[nowNode.end] + endNode.value;
					pq.add(new Node(endNode.end, map[endNode.end]));
				}
			}
			break;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			if (map[i] == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			} else {
				sb.append(map[i]).append("\n");
			}
		}
		System.out.println(sb);
		
		System.out.println(Arrays.toString(map));
	}

	static class Node implements Comparable<Node> {
		int end;
		int value;

		public Node(int end, int value) {
			this.end = end;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}
	}
}
