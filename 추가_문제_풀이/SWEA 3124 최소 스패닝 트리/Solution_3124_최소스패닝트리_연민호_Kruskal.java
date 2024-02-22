package algo240222;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 메모리:115,340kb, 시간:1,988ms
 */
public class Solution_3124_최소스패닝트리_연민호_Kruskal {
	static int[] parents;   //서로소 집합

	static int V,E;

	static Edge[] edges;    //간선 리스트

	static class Edge implements Comparable<Edge>{
		int from, to, weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());

			//간선 정보 입력
			edges = new Edge[E];
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				edges[i] = new Edge(from, to, weight);
			}

			sb.append("#").append(tc).append(" ").append(getMSTCost()).append("\n");
		}
		System.out.println(sb);
	}

	//최소신장트리 비용 반환(Kruskal 알고리즘)
	private static long getMSTCost() {

		long result = 0;    //최소 신장트리 비용

		int unionCnt = 0;   //합집합 연산 횟수(간선의 개수라고 볼 수 있음)

		make(); //서로소 집합 생성

		Arrays.sort(edges); //간선을 가중치 기준 오름차순 정렬

		for (Edge edge: edges) {
			//합집합 연산에 성공한다면?
			if(union(edge.from, edge.to)){
				unionCnt++;
				result += edge.weight;  //간선의 비용 count
			}
			//(정점의 개수 - 1)번 합집합 연산을 했다는 것은
			//임의의 정점으로 시작하여 나머지 모든 정점을 이었다.( 최소신장트리완성)
			if(unionCnt == V - 1)break;
		}

		return result;
	}

	//서로소 집합 만들기
	private static void make() {
		parents = new int[V+1];
		for(int i=1; i<=V; i++) {
			parents[i] = i;
		}
	}
	//a원소 포함 집합의 대표원소 반환 메소드
	private static int find(int a) {
		//대표원소라면? 반환
		if(parents[a] == a) return a;

		return parents[a] = find(parents[a]);	//Path Compression
	}       

	//a포함 집합, b원소 포함 집합 합집합 메소드 
	private static boolean union(int a, int b) {

		int rootA = find(a);    //a원소 포함 집합의 대표원소
		int rootB = find(b);    //b원소 포함 집합의 대표원소

		//이미 같은 집합일 경우 합집합 연산 X
		if(rootA==rootB) return false;

		parents[rootB] = rootA; //b의 대표원소였던 녀석이 a포함 집합의 대표원소를 가리키도록 함(합집합)
		return true;    //합집합 연산 성공
	}
}