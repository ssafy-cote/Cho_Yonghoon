package algo240216;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:20,680kb, 시간:124ms
 */
public class Solution_1238_Contact_연민호_BFS_레벨별탐색 {
	static int N = 100;	//번호 범위
	
	static int maxNumber;	//최대 연락횟수인 사람의 최대 번호 저장 변수
	
	static boolean[][] adjMatrix;	//간선 정보 담을 인접 행렬
	
	static boolean[] visited; 	//방문 여부
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;	//테스트케이스
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());	//입력 데이터 길이
			
			int start = Integer.parseInt(st.nextToken());	//시작 점
			
			//간선 정보 입력
			adjMatrix = new boolean[N+1][N+1];	
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[from][to] = true;
			}
			
			visited = new boolean[N+1];
			maxNumber = start;	//최대 번호 초기화
			
			bfs(start);
			
			
			sb.append('#').append(tc).append(' ').append(maxNumber).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();	//방문할 노드 번호 저장
		
		q.offer(start);
		visited[start] = true;	
		
		while(!q.isEmpty()) {
			int depthMaxNum = 0;	//같은 depth에서 가장 큰 번호를 저장
			
			//depth 별 탐색
			int size = q.size();
			while(size-- > 0) {
				
				int from = q.poll();	//노드 번호 받아오기
				depthMaxNum = Math.max(depthMaxNum, from);	//같은 depth에서 최대 번호 갱신
				
				//현재 정점에서 연락 가능한 번호 큐에 저장
				for(int to=0; to<=N; to++) {
					if(!adjMatrix[from][to]) continue;	//인접하지 않음
					if(visited[to]) continue;			//이미 방문
					
					q.offer(to);		//큐에 탐색 정보 저장
					visited[to] = true;	//방문 처리
				}
			}
			//현재 depth 탐색의 최대 번호를 저장
			maxNumber = depthMaxNum;
		}
	}
}