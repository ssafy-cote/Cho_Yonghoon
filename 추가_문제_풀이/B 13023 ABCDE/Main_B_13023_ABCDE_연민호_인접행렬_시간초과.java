package algo240221;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * [시간 초과 발생 이유]
 * 
 * 인접행렬이기 때문에 각 정점을 기준으로 인접한 정점에 대한 정보를 구할 때,
 * for문을 N번 돌아야 함
 * 		=> if 조건에 의해 걸러지긴 하지만 어쨌든 for문을 돌긴하기 때문
 * 
 * 그렇기 때문에 만약 모두 5번 깊이의 탐색을 한번한다고 가정했을 때,
 * 
 * 1번째 정점으로부터 인접한 정점 구할 때, 2000
 * 2번째  "								   2000
 * 					...
 * 5번째 "								   2000
 * 따라서 2000^5 = 32,000,000,000,000,000 = 3해 2000조
 * 
 * 3번 깊이까지만 이어간다고 하더라도
 * 2000^3 = 8,000,000,000 = 80억
 * 
 * 따라서, 인접행렬로는 시간초과로 정답을 구할 수 없음
 */
public class Main_B_13023_ABCDE_연민호_인접행렬_시간초과 {

	static boolean[][] adjMatrix;
	
	static boolean[] visited;
	
	static int N,M;	//사람 수, 친구 관계 수
	
	static int cnt;	
	
	static class Node{
		int cur;	//정점 번호
		Node next;	//다음 정점 객체 참조
		
		public Node(int cur, Node next) {
			this.cur = cur;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjMatrix = new boolean[N][N];
		visited = new boolean[N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = true;
			adjMatrix[b][a] = true;
		}
		
		for(int from=0; from<N; from++) {
			dfs(from, 1);
		}
		//조건에 부합하지 않으므로 0출력
		System.out.println(0);
	}

	/**
	 * from 정점을 방문하고 from 정점으로부터 인접한 정점에 대한 방문 처리는 재귀로 넘김
	 * @param from 	현재 방문하는 정점
	 * @param cnt	dfs탐색의 깊이
	 */
	private static void dfs(int from, int cnt) {
		//5번의 탐색을 이어갔다면 조건에 부합하므로 1출력후 종료
		if(cnt==5) {
			System.out.println(1);
			System.exit(0);
		}
		
		visited[from] = true;	//from 정점 방문 처리
		
		//from정점과 인접한 모든 정점에 대한 방문처리는 재귀로 넘김
		for(int to=0; to<N; to++) {
			if(!adjMatrix[from][to]) continue;	//인접한 정점 아님
			if(visited[to]) continue;			//이미 방문
			
			dfs(to, cnt+1);
		}
		visited[from] = false;
	}
}