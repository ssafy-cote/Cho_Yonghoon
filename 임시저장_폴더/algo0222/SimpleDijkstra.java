package algo0222;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.IntStream;

public class SimpleDijkstra {

    private static final int INF = 1_000_000_000;

    static class Node {

        int number;
        int distance;

        public Node(int number, int distance) {
            this.number = number;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // ��� ����
        int m = sc.nextInt(); // ���� ����
        int startNodeNumber = sc.nextInt(); // Ž�� ���� ���

        List<List<Node>> graph = new ArrayList<>();
        // �׷��� �ʱ�ȭ
        for (int i = 0; i < n + 1; i++) { // �ε����� 1 ~ n ���� ���
            graph.add(new ArrayList<>());
        }

        // ���� ���� ���� �Է�
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int distance = sc.nextInt();

            graph.get(from).add(new Node(to, distance));
        }

        sc.close();

        // Ǯ�� ����
        boolean[] visited = new boolean[n + 1]; // �湮 ���� ����
        int[] distances = new int[n + 1]; // �ִ� �Ÿ� ���̺�
        dijkstra(graph, visited, distances, startNodeNumber);

        // �� ����� �ִ� ��� ���
        for (int i = 1; i < distances.length; i++) {
            int distance = distances[i];
            if (distance == INF) {
                System.out.println("INFINITY");
            } else {
                System.out.println(distance);
            }
        }
    }

    public static void dijkstra(List<List<Node>> graph, boolean[] visited, int[] distances, int startNodeNumber) {
        // ���ʿ��� �ִ� �Ÿ��� ��� �������� ����
        Arrays.fill(distances, INF);

        // ���� ��忡 ���� �ʱ�ȭ
        distances[startNodeNumber] = 0;
        visited[startNodeNumber] = true;
        // ���� ����� ���� ��� �Ÿ� ����
        for (Node neighbor : graph.get(startNodeNumber)) {
            distances[neighbor.number] = neighbor.distance;
        }

        // ���� ��带 ������ ������ ���鿡 ���� ����, ���� ���� �ݺ�
//        for (int i = 1; i < graph.size() - 1; i++) {
//            // ���� �湮���� ���� ���� �� ���� �Ÿ��� ª�� ����� number ã��
//            int minNodeNumber = IntStream.range(1, distances.length)
//                .filter(index -> !visited[index])
//                .boxed()
//                .collect(toMap(index -> index, index -> distances[index]))
//                .entrySet().stream()
//                .min(Comparator.comparingInt(Entry::getValue))
//                .map(Entry::getKey).get();
//
//            // ���� �ִ� �Ÿ� ��� �湮 ó��
//            visited[minNodeNumber] = true;
//            for (Node neighbor : graph.get(minNodeNumber)) {
//                int newCost = distances[minNodeNumber] + neighbor.distance;
//
//                // ���� �ִ� �Ÿ� ��带 ���ļ� ���� ��忡 �湮�ϴ°� �� �ִ� ����̸�
//                // ���̺� ���� ����
//                if (newCost < distances[neighbor.number]) {
//                    distances[neighbor.number] = newCost;
//                }
//            }
//        }
    }

}