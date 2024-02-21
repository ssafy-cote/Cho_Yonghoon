package algo240221;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
/**
 * 메모리:19400KB, 시간:232ms
 */
public class Main_B_13023_ABCDE_연민호_인접리스트_List버전 {

	static List<Integer>[] adjList;
	
	static boolean[] visited;
	
	static int N,M;	//사람 수, 친구 관계 수
	
	static int cnt;	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new List[N];
		for(int i=0; i<N; i++) adjList[i] = new ArrayList<>();
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjList[a].add(b);
			adjList[b].add(a);
		}
		
		visited = new boolean[N];
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
		for(int to : adjList[from]) {
			if(visited[to]) continue; //이미 방문
			
			dfs(to, cnt+1);
		}
		visited[from] = false;
	}
}