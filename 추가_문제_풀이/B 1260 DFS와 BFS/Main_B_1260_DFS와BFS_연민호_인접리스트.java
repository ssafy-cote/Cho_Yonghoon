package algo240216;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 인접 리스트로 구현 해당 문제의 경우 작은 번호부터 탐색해야 함 Node 배열로 할 경우 불가능 상당히 코드가 복잡해짐 따라서
 * ArrayList를 여러개 만드는 방식으로 코딩
 * 
 * @author minho
 * 메모리:17852KB, 시간:208ms
 */
public class Main_B_1260_DFS와BFS_연민호_인접리스트 {
	static int N, M, V;
	
	static boolean[] visited;
	
	static List<Integer>[] adjList; // 관계들을 저장 (relation[x] 에 저장되는 ArrayList<int>는 x 정점에 연결된 점들)

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		V = Integer.parseInt(st.nextToken()); // 시작할 정점

		adjList = new List[N+1];

		// 각 정점마다 ArrayList 만들기
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		// 간선 정보 저장
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 양방향 가능하므로 반대쪽 넣어주기.
			adjList[x].add(y);
			adjList[y].add(x);
		}

		for (int i = 1; i <= N; i++) {
			Collections.sort(adjList[i]); // 작은 점 먼저 방문하기 위해 오름차순 정렬
		}

		visited = new boolean[N+1];
		dfs(V);
		sb.append('\n');
		visited = new boolean[N+1];
		bfs();
		
		System.out.println(sb);
	}

	/**
	 * 현재 정점(cur)을 방문하고 인접한 정점에 대한 방문은 재귀로 넘김 
	 * @param cur
	 */
    private static void dfs(int cur) {
    	visited[cur] = true;	//시작점 방문처리
		sb.append(cur).append(' ');	//방문 번호 출력
		
		//cur정점에서 이동가능한 정점 중
		for(int to : adjList[cur]) {
			if(visited[to]) continue;	//방문한 정점은 넘기기 
			
			dfs(to);		//다음 깊이 탐색
		}
    }

    //너비 우선 탐색
    private static void bfs() {
    	Queue<Integer> q = new ArrayDeque<>();	//탐색에 사용할 큐
    	
    	q.offer(V);	//시작점 큐에 담기
    	visited[V] = true;	//시작점 방문 처리
    	
    	//탐색할 번호가 없을 때까지 반복
    	while(!q.isEmpty()) {
    		int cur = q.poll();	//탐색할 정점 번호 받아오기
    		sb.append(cur).append(' ');	//탐색한 번호 출력

    		//cur정점에서 이동가능한 정점 중
    		for(int to : adjList[cur]) {
    			if(visited[to]) continue;	//방문한 정점은 넘어감
    			
				q.offer(to);	//탐색할 번호 저장
				visited[to] = true;	//방문 처리
    		}
    	}
    }

}