package algo0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimPQTest_ {
	static class Node implements Comparable<Node> {
		int no, value;

		public Node(int no, int value) {
			this.no = no;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return this.value - o.value;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");

		int V = Integer.parseInt(stringTokenizer.nextToken());
		int[][] adjMatrix = new int[V][V]; // 인접 행렬
		boolean[] visit = new boolean[V]; // 트리정점 여부
		int[] minEdge = new int[V]; // 비트리정점 기준으로 트리 정점들과 연결했을 경우 최소 간선 비용
		for (int i = 0; i < V; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		Arrays.fill(minEdge, Integer.MAX_VALUE);
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		minEdge[0] = 0; // 0번부터 시작
		priorityQueue.offer(new Node(0, minEdge[0]));
		int result = 0; // 최소 신장트리 비용
		int c = 0;
		while (!priorityQueue.isEmpty()) {

			Node node = priorityQueue.poll();
			
			if (visit[node.no])
				continue;
			
			visit[node.no] = true;
			
			result += node.value;
			
			if (++c == V)
				break;
			
			for (int i = 0; i < V; i++) {
				if (!visit[i] && adjMatrix[node.no][i] != 0 && minEdge[i] > adjMatrix[node.no][i]) {
					minEdge[i] = adjMatrix[node.no][i];
					priorityQueue.offer(new Node(i, minEdge[i]));
				}
			}
		}
		System.out.println(c == V ? result : -1);
	}
}
