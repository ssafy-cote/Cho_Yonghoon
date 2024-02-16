package algo240216;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:20,956kb, 시간:119ms
 */
public class Solution_1238_Contact_연민호_BFS {
	static int N = 100;	//번호 범위
	
	static int maxCnt;	//최대 연락횟수
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
			
			maxCnt = 0;
			maxNumber = start;
			
			bfs(start);
			
			sb.append('#').append(tc).append(' ').append(maxNumber).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();	//방문할 노드번호와 연락 순서 저장할 큐
		
		q.offer(new int[] {start, 0});	//시작 노드의 번호와 연락 순서 저장
		visited[start] = true;	//시작 노드 방문 체크
		
		while(!q.isEmpty()) {
			
			int[] node = q.poll();	//노드 정보
			int from = node[0];
			int cnt = node[1];	//몇 번째 연락인지
			
			//연락 순서가 더 크거나
			//연락 순서가 같고 노드 번호가 큰 경우
			if(cnt > maxCnt || (cnt == maxCnt && maxNumber < from)) {
				
				maxCnt = cnt;	//최대 연락 순서 갱신
				maxNumber = from;	//노드 번호 변경
			}
			
			for (int to = 0; to <= N; to++) {
				if(!adjMatrix[from][to]) continue;	//인접하지 않음
				if(visited[to]) continue;			//이미 방문
				
				q.offer(new int[] {to, cnt+1});	//다음 방문 번호와 연락순서(현재노드의 연락순서+1) 저장
				visited[to] = true;	//다음 방문 번호 체크
			}
		}
	}
}