package algo240222;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:34296KB, 시간:176ms
 */
public class Main_BJ_17135_캐슬디펜스_연민호_depth별BFS {
	//좌 => 상 => 우		순서 중요!
	static int[] dr = {0, -1, 0};
	static int[] dc = {-1, 0, 1};
	
	static int[][] origin;	//원본 정보
	static int[][] map;	//사용할 정보
	static int[] hunters; //궁수의 열 위치 정보
	
	static int N, M, D; //배열 크기정보, 궁수의 공격 범위
	
	static int kill;	//현재 궁수 조합의 킬수 
	static int maxKill;	//현재까지의 조합 중 최대 킬수
	
	static class Node{
		int r,c;	//해당 노드의 좌표
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		origin  = new int[N][M];
		map = new int[N][M];
		hunters = new int[3];
		
		maxKill=0;
		
		//배열 정보 입력
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		combination(0, 0);
		
		System.out.println(maxKill);
		
	}

	private static void combination(int cnt, int start) {
		//step 01. 궁수 3명 배치 완료
		if(cnt==3) {
			// kill 값 초기화
			kill = 0;
			arrayCopy();
			//N만큼 적이 이동하면 모든 적이 사라지므로 N만큼 반복
			for(int i=0; i<N; i++) {
				//step 02. 활쏴서 적 죽이기
				shoot();
				
				//step 03. 적 이동
				move();
			}
			
			//step 04. 현재 궁수 배치 상태에서 죽인 수가 최댓값이라면 갱신
			maxKill = Math.max(maxKill, kill);
			return;
		}
		
		for(int i=start; i<M; i++) {
			hunters[cnt] = i;	//궁수 배치
			combination(cnt+1, i+1);	//다음 궁수 배치는 재귀 호출
		}
	}

	/**
	 * 적 아래로 이동
	 * 전체 행을 아래로 1칸씩 내리고 맨 윗행의 배열엔 빈 배열 할당
	 */
	private static void move() {
		for(int i=N-2; i>=0; i--) {
			map[i+1] = map[i];
		}
		map[0] = new int[M];
	}

	private static void shoot() {
		// 1) 세 명의 궁수가 죽일 적 위치 찾기
		List<Node> enemies = new ArrayList<>(); //죽일 적 좌표 정보 담기
		
		//궁수 세 명 bfs탐색
		A : for(int hunterC : hunters) {
			
			boolean[][] visited = new boolean[N][M];
			Queue<Node> q = new ArrayDeque<>();
			
			//시작 정보 큐에 넣기
			q.offer(new Node(N-1, hunterC));
			visited[N-1][hunterC] = true;
			
			int depth = 1;	//궁수로부터의 거리
			while(!q.isEmpty()) {
				//depth 별 탐색
				int size = q.size();
				while(size-- > 0) {
					//좌표 정보 뽑아오기
					Node n = q.poll();
					int r = n.r;
					int c = n.c;
					
					//적정보를 뽑아 왔다면?
					if(map[r][c] == 1) {
						//적 좌표정보 추가 후 다음 궁수 bfs 탐색으로
						enemies.add(n);
						continue A;
					}
					//좌 => 상=> 우 탐색
					for(int d=0; d<3; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if(nr<0 || nr>=N || nc<0 || nc>=M) continue;	//경계 벗어나는 경우
						if(visited[nr][nc]) continue;	//이미 방문한 경우
						if(depth+1 >D) continue;		//궁수로부터의 거리(depth+1)가 D를 초과
						
						
						q.offer(new Node(nr,nc));
						visited[nr][nc] = true;
					}
				}
				depth++;
			}
		}
		// 2) 적 죽이기
		for(Node n : enemies) {
			if(map[n.r][n.c]!=1) continue;	//이미 죽인 적
			
			map[n.r][n.c] = 0;	// 죽이기
			kill++;				//죽인 수 카운트
		}
	}

	/**
	 * origin 배열 map에 copy
	 */
	private static void arrayCopy() {
		for(int i=0; i<N; i++) {
			System.arraycopy(origin[i], 0, map[i], 0, M);
		}
	}
}