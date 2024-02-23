package algo240223;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 
 * 메모리:12976KB, 시간:92ms
 * 
 * @author SSAFY
 *
 */
public class Main_B_17471_게리맨더링_연민호_bfs {

	static int N;	//구역의 수
	
	static int[] popInfo;	//구역의 인구 정보
	
	static boolean[][] adjMatrix;	//인접 행렬
	
	static boolean[] isInA;	//선거구 체크 true : A, false : B

	static int minPopDiff;	//두 선거구 인구의 최솟값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		//인구 정보 입력
		popInfo = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			popInfo[i] = Integer.parseInt(st.nextToken());
		}
		
		//간선 정보 입력
		adjMatrix = new boolean[N+1][N+1];
		for(int from=1; from<=N; from++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int i=0; i<n; i++) {
				int to = Integer.parseInt(st.nextToken());
				adjMatrix[from][to] = true;
			}
		}

		minPopDiff=Integer.MAX_VALUE;
		isInA = new boolean[N+1];
		subset(1, 0);
		
		System.out.println(minPopDiff==Integer.MAX_VALUE ? -1 : minPopDiff);
	}

	/**
	 * 
	 * idx번째 구역을 A선거구에 포함/비포함여부를 결정하고 다음 구역의 A선거구 포함/비포함 여부는 재귀로 넘김
	 * @param idx A구역에 포함/비포함 여부를 결정할 구역의 index정보
	 * @param aCnt A구역에 포함된 구역의 개수
	 */
	private static void subset(int idx, int aCnt) {
		//step01. A선거구 결정 완료
		if(idx==N+1) {
			if(aCnt==0 || aCnt==N) return;	//A구역의 개수가 0개 또는 N개면 return
			
			//step 02. 각 선거구가 모두 연결되어있는지 체크
			if(!isConnected()) return;
			
			//step 03. 두 선거구의 인구수 차이값 구하고 최솟값이라면 갱신
			int curPopDiff = getPopDiff();
			minPopDiff = Math.min(minPopDiff, curPopDiff);
			
			return;
		}
		
		//idx번째 구역을 A선거구에 포함
		isInA[idx] = true;
		subset(idx+1, aCnt+1);
		
		//idx번째 구역을 A선거구에 비포함
		isInA[idx] = false;
		subset(idx+1, aCnt);
	}

	/**
	 * A선거구, B선거구의 인구수 차이 반환
	 * @return
	 */
	private static int getPopDiff() {
		int sumA = 0;
		int sumB = 0;
		for(int i=1; i<=N; i++) {
			if(isInA[i]) sumA += popInfo[i];	//A선거구라면 sumA에 누적
			else sumB += popInfo[i];			//B선거구라면 sumB에 누적
		}
		return Math.abs(sumA-sumB);
	}

	/**
	 * 각 선거구가 모두 연결되어 있다면 true 반환
	 * @return
	 */
	private static boolean isConnected() {
		boolean[] visited = new boolean[N+1];

		//첫번째 FloodFill ( 1번 구역 기준 시작 )
		bfs(1, isInA[1], visited);
		
		//2~N구역 중(위에서 1을 시작점으로 FloodFill 했으므로 1은 두번째 FloodFill의 시작점이 될 수 없음)
		//첫번째 FloodFill에서 방문되지 않은 구역 하나를 골라 해당 구역 기준 FloodFill
		int second;	//두번째 FloodFill의 시작인덱스
		for(second=2; second<=N; second++) {
			if(!visited[second]) break;
		}
		//두번째 FloodFill ( second 구역 기준 시작 )
		bfs(second, isInA[second], visited);
		
		//두번의 FloodFill을 마쳤을 때,
		//방문배열에 false 값이 있다는 것은 연결되지 않은 구역이 있다는 것(false 리턴)
		for(int i=1; i<=N; i++) {
			if(!visited[i]) return false;
		}
		
		//모두 연결되었으므로 true 반환
		return true;
	}

	/**
	 * start 구역 기준 FloodFill
	 * @param start FloodFill의 시작점
	 * @param isStartA bfs탐색 시작점의 선거구 정보
	 * @param visited
	 */
	private static void bfs(int start, boolean isStartA, boolean[] visited) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			//출발지 뽑아오기
			int from = q.poll();
			
			for(int to=1; to<=N; to++) {
				if(!adjMatrix[from][to] ) continue;		//인접하지 않음
				if(visited[to]) continue;				//이미 방문
				if(isInA[to] != isStartA) continue;		//다른 선거구
				
				q.offer(to);
				visited[to] = true;
			}
		}
	}
}