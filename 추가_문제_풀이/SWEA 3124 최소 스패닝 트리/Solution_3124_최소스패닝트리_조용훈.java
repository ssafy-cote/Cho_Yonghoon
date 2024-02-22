package algo0222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_3124_최소스패닝트리_조용훈 {
	static long ans;
	static int V, E;
	static int[] map;
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int T = Integer.parseInt(stringTokenizer.nextToken());
		StringBuilder builder = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			ans = 0;
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			V = Integer.parseInt(stringTokenizer.nextToken());
			E = Integer.parseInt(stringTokenizer.nextToken());
			map = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				map[i] = i;
			}
			nodes = new Node[E];
			for (int i = 0; i < E; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
				int a = Integer.parseInt(stringTokenizer.nextToken());
				int b = Integer.parseInt(stringTokenizer.nextToken());
				int v = Integer.parseInt(stringTokenizer.nextToken());
				nodes[i] = new Node(a, b, v);
			}
			Arrays.sort(nodes);
			int count = 0;
			for (Node node : nodes) {
				if (union(node.start, node.end)) {
					ans += node.value;
					++count;
				}
				if (count == V - 1) {
					break;
				}
			}
			builder.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(builder);
	}

	static boolean union(int a, int b) {
		int aP = find(a);
		int bP = find(b);

		if (aP == bP) {
			return false;
		}
		if (aP < bP) {
			map[bP] = aP;
		} else {
			map[aP] = bP;
		}
		return true;
	}

	static int find(int a) {
		if (map[a] == a)
			return a;
		return map[a] = find(map[a]);
	}

	static class Node implements Comparable<Node> {
		int start;
		int end;
		long value;

		public Node(int start, int end, int value) {
			this.start = start;
			this.end = end;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.value, o.value);
		}
	}
}
