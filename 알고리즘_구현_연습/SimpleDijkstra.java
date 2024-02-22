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
        int n = sc.nextInt(); // 노드 개수
        int m = sc.nextInt(); // 간선 개수
        int startNodeNumber = sc.nextInt(); // 탐색 시작 노드

        List<List<Node>> graph = new ArrayList<>();
        // 그래프 초기화
        for (int i = 0; i < n + 1; i++) { // 인덱스로 1 ~ n 까지 사용
            graph.add(new ArrayList<>());
        }

        // 인접 간선 정보 입력
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int distance = sc.nextInt();

            graph.get(from).add(new Node(to, distance));
        }

        sc.close();

        // 풀이 시작
        boolean[] visited = new boolean[n + 1]; // 방문 여부 저장
        int[] distances = new int[n + 1]; // 최단 거리 테이블
        dijkstra(graph, visited, distances, startNodeNumber);

        // 각 노드의 최단 경로 출력
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
        // 최초에는 최단 거리를 모두 무한으로 설정
        Arrays.fill(distances, INF);

        // 시작 노드에 대해 초기화
        distances[startNodeNumber] = 0;
        visited[startNodeNumber] = true;
        // 시작 노드의 인접 노드 거리 갱신
        for (Node neighbor : graph.get(startNodeNumber)) {
            distances[neighbor.number] = neighbor.distance;
        }

        // 시작 노드를 제외한 나머지 노드들에 대해 선택, 갱신 과정 반복
//        for (int i = 1; i < graph.size() - 1; i++) {
//            // 현재 방문하지 않은 정점 중 가장 거리가 짧은 노드의 number 찾기
//            int minNodeNumber = IntStream.range(1, distances.length)
//                .filter(index -> !visited[index])
//                .boxed()
//                .collect(toMap(index -> index, index -> distances[index]))
//                .entrySet().stream()
//                .min(Comparator.comparingInt(Entry::getValue))
//                .map(Entry::getKey).get();
//
//            // 현재 최단 거리 노드 방문 처리
//            visited[minNodeNumber] = true;
//            for (Node neighbor : graph.get(minNodeNumber)) {
//                int newCost = distances[minNodeNumber] + neighbor.distance;
//
//                // 현재 최단 거리 노드를 거쳐서 인접 노드에 방문하는게 더 최단 경로이면
//                // 테이블 값을 갱신
//                if (newCost < distances[neighbor.number]) {
//                    distances[neighbor.number] = newCost;
//                }
//            }
//        }
    }

}