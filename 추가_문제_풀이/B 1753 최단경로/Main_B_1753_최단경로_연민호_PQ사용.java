package algo240227;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/**
 * 메모리:117,632KB, 시간:692ms
 */
public class Main_B_1753_최단경로_연민호_PQ사용 {

	static int V, E, K; //정점과 간선의 개수, 시작점
	
	static List<Node>[] adjList;	//인접리스트
	
	static int[] distance;	//시작점으로부터 정점과의 최단거리 담는 배열
	
	static class Node{
		int to, weight;	//도착점, 가중치
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		adjList = new List[V + 1];
		for(int i=1; i<=V; i++) adjList[i] = new ArrayList<>();
		
		//인접 리스트 만들기
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Node(to, weight));
		}
		dijkstra();	//특정 시작점으로 부터 각 정점까지의 최단 거리 구하기
		
		//출력
		for(int i=1; i<=V; i++) {
			sb.append(distance[i]==Integer.MAX_VALUE?"INF":distance[i]).append('\n');
		}
		System.out.println(sb);
	}

	
	//다익스트라 알고리즘 사용시 정점에 대한 정보담을 클래스
	static class Vertex implements Comparable<Vertex>{
		int num;
		int weight;	//시작정점(K)에서 자기 자신까지의 거리
		
		public Vertex(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}
		
		//시적정
		@Override
		public int compareTo(Vertex o){
			return this.weight - o.weight;
		}
	}
	
	private static void dijkstra() {
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		boolean[] visited = new boolean[V + 1];
		distance = new int[V + 1];
		
		//step 01. 최단거리 정보 무한대로 채워넣기
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		//step 02. //최단 거리를 구할 기준 정점의 최단거리정보 0으로
		distance[K] = 0;	
		pq.add(new Vertex(K, 0));
		
		int cnt = 0;	//정점의 개수만큼 처리할 경우 종료
		while(!pq.isEmpty()) {
			//step 3. 방문하지 않았고 최단거리가 가장 작은 정보 꺼내기
			Vertex current = pq.poll();	//정점 정보 받아오기(시작정점에서 자기 자신으로의 거리가 최소인 것을 꺼내옴)
			int num = current.num;
			int weight = current.weight;
			
			if(visited[num]) continue;	//이미 최단거리가 확정된 정점이면 다음 정점
			
			//step 4. 해당 정점 방문체크 - K => current 최단거리 확정 
			visited[num]= true;
			
			if(++cnt == V) break;	//정점의 개수만큼 처리되면 끝냄
			
			//num 정점과 인접한 모든 정점들의 최소비용 갱신
			for(Node n : adjList[num]) {
				if(visited[n.to]) continue;		//이미 방문
				if(	distance[n.to] <= weight + n.weight  ) continue;	//n.to정점까지의 최소비용 갱신 필요 없음
					
				distance[n.to] = weight + n.weight;	//시작점으로부터 to까지의 거리 갱신
				pq.add(new Vertex( n.to , distance[n.to]));	//이후 뽑아낸 정점을 current로 위와 같은 처리를 해야하므로 큐에 저장
			}
		}
	}
}