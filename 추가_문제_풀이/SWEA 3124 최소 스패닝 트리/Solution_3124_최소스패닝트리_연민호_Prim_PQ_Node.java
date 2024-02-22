package algo240222;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 메모리:116,224kb, 시간:1,954ms
 */
public class Solution_3124_최소스패닝트리_연민호_Prim_PQ_Node{
	static int V, E;

	static Node[] adjList;  //인접리스트

	static class Node{
		int to, weight; //도착점, 가중치
		Node next;  //다른 간선정보를 가리킴

		public Node(int to, int weight, Node next) {
			this.to = to;
			this.weight = weight;
			this.next = next;
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

			adjList = new Node[V+1];

			//간선 리스트 만들기
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				adjList[from] = new Node(to, weight, adjList[from]);
				adjList[to] = new Node(from, weight,adjList[to]);
			}

			sb.append("#").append(tc).append(" ").append(getMSTCost()).append("\n");
		}
		System.out.println(sb);
	}

	static class Vertex{
		int no; //정점 번호
		int weight; //현재 트리에서 해당정점까지의 가중치
		public Vertex(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}

	}

	//최소신장트리 비용 반환(프림 알고리즘)
	private static long getMSTCost() {
		PriorityQueue<Vertex> pq = new PriorityQueue<>((a,b)->a.weight-b.weight);

		boolean[] visited = new boolean[V+1];
		long result = 0;    //최소 신장트리 비용
		int cnt = 0;    //처리한 정점의 개수

		pq.offer(new Vertex(1, 0)); //임의의 시작 정점 (1번 점을 시작점으로 설정)

		while(!pq.isEmpty()) {

			//step 01. 현재 트리에 포함되지 않은 정점 중 최소비용인 정점 선택
			Vertex current = pq.poll();
			if(visited[current.no]) continue;   //신장트리 포함 여부 체크

			visited[current.no] = true; //신장트리에 포함
			result += current.weight;   //신장트리에 포함시킨 정점의 가중치 더하기

			if(++cnt == V) break;   //모든 정점 처리하면 끝내기

			//step 02. 선택된 정점에서 신장트리에 포함되지 않은(visited에 체크되지않은) 정점 정보 PQ에 추가
			for(Node n=adjList[current.no]; n!=null; n=n.next) {
				if(!visited[n.to]) {
					pq.offer(new Vertex(n.to, n.weight));
				}
			}
		}

		return result;
	}
}