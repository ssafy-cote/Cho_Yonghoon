package algo240223;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 
 * 메모리:11780KB, 시간:80ms
 * 
 * @author SSAFY
 *
 */
public class Main_B_17471_게리맨더링_연민호_최적화2_바이너리카운팅_dfs {

	static int N;	//구역의 수
	
	static int[] popInfo;	//구역의 인구 정보
	
	static boolean[][] adjMatrix;	//인접 행렬
	
	static int isInA;	//선거구 체크 비트1 : A, 비트0 : B

	static int minPopDiff;	//두 선거구 인구의 최솟값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		//인구 정보 입력
		popInfo = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			popInfo[i] = Integer.parseInt(st.nextToken());
		}
		
		//간선 정보 입력
		adjMatrix = new boolean[N][N];
		for(int from=0; from<N; from++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int i=0; i<n; i++) {
				int to = Integer.parseInt(st.nextToken())-1;
				adjMatrix[from][to] = true;
			}
		}
		/*
		 * N = 6인 경우
		 * 선거구를 뽑는 모든 경우의 수는 부분집합으로 볼 수 있음
		 * 부분집합은 조합의 모든경우를 포함한다고 볼 수 있음
		 * 2^N = 6C0 + 6C1 + 6C2 + 6C3 + 6C4 + 6C5 + 6C6
		 * 1. 한쪽에 하나도 뽑지 않는 6C0, 6C6 제외
		 * 2. 6C1 == 6C5 , 6C2 == 6C3
		 * 따라서, 6C1, 6C2만 구하면 됨
		 * 
		 * 1 ~ N/2까지 뽑는 조합만 시도
		 *
		 * 해당 경우의 수를 바이너리카운팅을 이용하여 반만 시도하여 구할 수 있음
		 *
		 */
		minPopDiff=Integer.MAX_VALUE;
		//step01. isInA의 bit가 (A선거구의 선택 정보 의미)
		for(isInA=1; isInA < (1<<N)/2; isInA++) {
			
			//step 02. 각 선거구가 모두 연결되어있는지 체크
			if(!isConnected()) continue;
			
			//step 03. 두 선거구의 인구수 차이값 구하고 최솟값이라면 갱신
			int curPopDiff = getPopDiff();
			minPopDiff = Math.min(minPopDiff, curPopDiff);
		}
		
		System.out.println(minPopDiff==Integer.MAX_VALUE ? -1 : minPopDiff);
	}

	/**
	 * A선거구, B선거구의 인구수 차이 반환
	 * @return
	 */
	private static int getPopDiff() {
		int sumA = 0;
		int sumB = 0;
		for(int i=0; i<N; i++) {
			if( (isInA & 1<<i ) !=0 ) sumA += popInfo[i];	//A선거구라면 sumA에 누적
			else sumB += popInfo[i];						//B선거구라면 sumB에 누적
		}
		return Math.abs(sumA-sumB);
	}

	/**
	 * 각 선거구가 모두 연결되어 있다면 true 반환
	 * @return
	 */
	private static boolean isConnected() {
		boolean[] visited = new boolean[N];

		//첫번째 FloodFill ( 1번 구역 기준 시작 )
		dfs(0, (isInA & 1<<0) != 0, visited);
		
		//2~N구역 중(위에서 1을 시작점으로 FloodFill 했으므로 1은 두번째 FloodFill의 시작점이 될 수 없음)
		//첫번째 FloodFill에서 방문되지 않은 구역 하나를 골라 해당 구역 기준 FloodFill
		int second;	//두번째 FloodFill의 시작인덱스
		for(second=1; second<N; second++) {
			if(!visited[second]) break;
		}
		//두번째 FloodFill ( second 구역 기준 시작 )
		dfs( second , (isInA & 1<<second) != 0, visited);
		
		//두번의 FloodFill을 마쳤을 때,
		//방문배열에 false 값이 있다는 것은 연결되지 않은 구역이 있다는 것(false 리턴)
		for(int i=0; i<N; i++) {
			if(!visited[i]) return false;
		}
		
		//모두 연결되었으므로 true 반환
		return true;
	}

	/**
	 * 현재 구역(from)에 대한 방문 처리 후,
	 * 현재 구역에서 이동 가능한 구역( 인접하고 같은 선거구 )에 대한 방문처리는 재귀에 넘김
	 * @param from	탐색중인 구역
	 * @param isStartA dfs탐색 시작점의 선거구 정보
	 * @param visited
	 */
	private static void dfs(int from, boolean isStartA, boolean[] visited) {
		visited[from] = true;	//from 구역 방문처리
		
		for(int to=0; to<N; to++) {
			if(!adjMatrix[from][to] ) continue;		//인접하지 않음
			if(visited[to]) continue;				//이미 방문
			
			boolean toIsInA = (isInA & 1<<to) != 0; //to구역이 A선거구에 포함됐다면 true 
			if( toIsInA != isStartA) continue;		//다른 선거구
			
			dfs(to, isStartA, visited);
		}
	}
}