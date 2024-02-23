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
		int[][] adjMatrix = new int[V][V]; // ���� ���
		boolean[] visit = new boolean[V]; // Ʈ������ ����
		int[] minEdge = new int[V]; // ��Ʈ������ �������� Ʈ�� ������� �������� ��� �ּ� ���� ���
		for (int i = 0; i < V; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
			for (int j = 0; j < V; j++) {
				adjMatrix[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		Arrays.fill(minEdge, Integer.MAX_VALUE);

		minEdge[0] = 0; // 0������ ����

		int result = 0; // �ּ� ����Ʈ�� ���
		int c;
		for (c = 0; c < V; c++) {

			// step1: ��Ʈ�� ������ �ּ� ������� ���� ã��
			int minIndex = -1;
			int min = Integer.MAX_VALUE;
			// �ּҰ� ã����
			for (int i = 0; i < V; i++) {
				if (!visit[i] && minEdge[i] < min) {
					min = minEdge[i];
					minIndex = i;
				}
			}
			// ��ȯ �Ǵ� �����Ұ� ������ Ż��
			if (minIndex == -1)
				break;

			visit[minIndex] = true;
			result += min;

			// step2: ���Ӱ� Ʈ�� ������ Ȯ��� ���� �������� ��Ʈ�� ��������� ������� ��� ���� ������Ʈ
			for (int i = 0; i < V; i++) {
				if (!visit[i] && adjMatrix[minIndex][i] != 0 && minEdge[i] > adjMatrix[minIndex][i]) {
					minEdge[i] = adjMatrix[minIndex][i];
				}
			}

		}
		System.out.println(c == V ? result : -1);
	}
}
