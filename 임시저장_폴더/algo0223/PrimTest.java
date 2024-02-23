package algo0223;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimTest {

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

		minEdge[0] = 0; // 0번부터 시작

		int result = 0; // 최소 신장트리 비용
		int c;
		for (c = 0; c < V; c++) {

			// step1: 비트리 정점중 최소 간선비용 정점 찾기
			int minIndex = -1;
			int min = Integer.MAX_VALUE;
			// 최소값 찾는중
			for (int i = 0; i < V; i++) {
				if (!visit[i] && minEdge[i] < min) {
					min = minEdge[i];
					minIndex = i;
				}
			}
			// 순환 또는 선택할게 없으면 탈출
			if (minIndex == -1)
				break;

			visit[minIndex] = true;
			result += min;

			// step2: 새롭게 트리 정점에 확장된 정점 기준으로 비트리 정점들과의 간선비용 고려 최적 업데이트
			for (int i = 0; i < V; i++) {
				if (!visit[i] && adjMatrix[minIndex][i] != 0 && minEdge[i] > adjMatrix[minIndex][i]) {
					minEdge[i] = adjMatrix[minIndex][i];
				}
			}

		}
		System.out.println(c == V ? result : -1);
	}
}
