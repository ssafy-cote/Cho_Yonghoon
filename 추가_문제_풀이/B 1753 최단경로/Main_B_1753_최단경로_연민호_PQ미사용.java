package algo240227;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 메모리:116,388KB, 시간:1,936ms
 */
public class Main_B_1753_최단경로_연민호_PQ미사용 {
	
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		K = Integer.parseInt(br.readLine());
		
		//인접 리스트 만들기
		adjList = new List[V+1];
		for(int i=1; i<=V; i++) adjList[i] = new ArrayList<>();
		
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add( new Node(to, weight) );
		}
		
		dijkstra();
		
		//출력
		for(int i=1; i<=V; i++) {
			sb.append(distance[i]==Integer.MAX_VALUE? "INF" : distance[i]).append("\n");
		}
		System.out.println(sb);
	}

	private static void dijkstra() {
		boolean[] visited = new boolean[V + 1];
		distance = new int[V + 1];
		
		//step 01. 최단거리 정보 무한대로 채워넣기
		distance = new int[V+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		
		//step 02. //최단 거리를 구할 기준 정점의 최단거리정보 0으로
		distance[K] = 0;	
		
		for (int i = 0; i < V; i++) {
			//step 3. 방문하지 않았고 최단거리가 가장 작은 인덱스 꺼내기
			int num = -1;	//정점 번호 담을 변수
			int min = Integer.MAX_VALUE;	//distance가 최소인 정점을 비교하기 위해
			for(int j=1; j<=V; j++) {
				if(!visited[j] && min > distance[j]) {
					min = distance[j];
					num = j;
				}
			}
			//방문하지 않았고 거리가 최소인 정점을 찾지 못했다면 종료
			if(num == -1) break;	
			
			//step 4. 해당 정점 방문체크 - K => current 최단거리 확정 
			visited[num] = true;
			
			
			//num 정점과 인접한 모든 정점들의 최소비용 갱신
			for(Node n : adjList[num]) {
				if(visited[n.to]) continue;		//이미 방문
				if(	distance[n.to] <= distance[num] + n.weight  ) continue;	//n.to정점까지의 최소비용 갱신 필요 없음
				
				distance[n.to] = distance[num] + n.weight;	//시작점에서부터 to 정점까지의 거리 갱신
			}
		}
	}
}