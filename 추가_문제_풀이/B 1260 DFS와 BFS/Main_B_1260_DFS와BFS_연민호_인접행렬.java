package algo240216;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:18064KB, 시간:216ms
 */
public class Main_B_1260_DFS와BFS_연민호_인접행렬 {
	static int N, M, V;	//정점의 개수, 간선의 개수, 시작점
	
	static boolean[][] adjMatrix; // 인접 행렬
	static boolean[] visited;
	
    static StringBuilder sb = new StringBuilder();
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		M = Integer.parseInt(st.nextToken()); // 간선의 개수
		V = Integer.parseInt(st.nextToken()); // 시작할 정점
		
		//인접 행렬 만들기
		adjMatrix = new boolean[N+1][N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			//양방향 간선 처리
			adjMatrix[num1][num2] = true;
			adjMatrix[num2][num1] = true;
		}

		visited = new boolean[N+1];
		dfs(V);
		visited = new boolean[N+1];
		sb.append('\n');
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
		
		//인접 행렬에서 현재 정점에서 방문가능한 번호 탐색
		for(int to=1; to<=N; to++) {
			if(!adjMatrix[cur][to]) continue;	//인접하지 않은 정점
			if(visited[to]) continue; 			//방문한 정점
			
			dfs(to);	//다음 깊이 탐색
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
    		
    		//인접 행렬에서 현재 정점에서 방문가능한 번호 탐색
    		for(int to=1; to<=N; to++) {
    			if(!adjMatrix[cur][to]) continue;	//인접하지 않은 정점
    			if(visited[to]) continue; 			//방문한 정점
    			
				q.offer(to);	//탐색할 번호 저장
				visited[to] = true;	//방문 처리
    		}
    	}
    }
}