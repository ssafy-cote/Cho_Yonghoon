package algo0220;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4803_Æ®¸®_0219 {
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		int testCase = 1;
		while (true) {
			stringTokenizer = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(stringTokenizer.nextToken());
			int m = Integer.parseInt(stringTokenizer.nextToken());

			if (n == 0 && m == 0)
				break;

			graph = new ArrayList<>();
			for (int i = 0; i < n + 1; i++) {
				graph.add(new ArrayList<>());
			}

			for (int i = 0; i < m; i++) {
				stringTokenizer = new StringTokenizer(br.readLine(), " ");
				int u = Integer.parseInt(stringTokenizer.nextToken());
				int v = Integer.parseInt(stringTokenizer.nextToken());
				graph.get(u).add(v);
				graph.get(v).add(u);
			}

			visited = new boolean[n + 1];

			int count = 0;
			for (int i = 1; i < n + 1; i++) {
				if (!visited[i]) {
					if (bfs(i))
						count += 1;
				}
			}

			if (count == 0) {
				sb.append("Case " + testCase++ + ": No trees.");
			} else if (count == 1) {
				sb.append("Case " + testCase++ + ": There is one tree.");
			} else {
				sb.append("Case " + testCase++ + ": A forest of " + count + " trees.");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static boolean bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		int node = 0, edge = 0;

		while (!q.isEmpty()) {
			int cur = q.poll();
			node += 1;
			visited[cur] = true;

			for (int next : graph.get(cur)) {
				edge += 1;
				if (!visited[next]) {
					q.add(next);
				}
			}
		}
		return 2 * (node - 1) == edge ? true : false;
	}
}