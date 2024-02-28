package algo240228;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:51012KB, 시간:532ms
 */
public class Main_B_1600_말이되고픈원숭이_연민호 {
	//원숭이 이동과 말 이동 델타 값 (시계방향)
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
																														// 8개
	static int[] hdr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] hdc = { 1, 2, 2, 1, -1, -2, -2, -1 };
	
	//말이동 횟수와 map 가로 세로 크기
	static int K, W, H;
	
	static int[][] map;
	
	static public class Monkey {
		int r, c;	//원숭이 좌표
		int k;		//사용한 말 점프 횟수

		public Monkey(int r, int c, int k ) {
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		// 맵 입력
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
	}
	
	private static void bfs() {
		boolean[][][] visited = new boolean[H][W][K + 1];
		Queue<Monkey> q = new ArrayDeque<>();
		
		//초기 원숭이 정보 큐에 넣기
		q.add(new Monkey(0, 0, 0));
		visited[0][0][0] = true; // 방문체크

		int cnt=0;	//레벨 초기화
		
		while(!q.isEmpty()) {
			//레벨별 탐색
			int size = q.size();
			while(size-- > 0) {
				Monkey m = q.poll();	//원숭이 정보 추출
				
				//목표점(왼쪽 하단)에 도달한 경우 도달했을 때의 동작 횟수 출력
				if (m.r == H-1 && m.c == W-1) {
					System.out.println(cnt);
					return;
				}
				
				// 원숭이 이동
				for (int dir=0; dir<4; dir++) {
					int nr = m.r + dr[dir];
					int nc = m.c + dc[dir];
					
					int k = m.k;
					
					if (!isInRange(nr,nc)) continue;	//경계 벗어남
					if( map[nr][nc] == 1 ) continue;	//벽
					if(visited[nr][nc][k]) continue;	//이미 방문
					
					//그 외의 경우, 큐에 넣고 방문 처리
					q.add(new Monkey(nr, nc, k));
					visited[nr][nc][k] = true;
					
				}
				
				// 원숭이가 말이동을 모두 사용한 경우? 말 이동 사용 불가
				if (m.k == K) continue;
				
				//말 이동
				for (int dir=0; dir<8; dir++) {
					int nr = m.r + hdr[dir];
					int nc = m.c + hdc[dir];
					
					int k = m.k + 1; //말 이동 사용했으므로 +1
					
					if(!isInRange(nr,nc) ) continue;	//경계 벗어남
					if(map[nr][nc] == 1  ) continue;	//벽
					if(visited[nr][nc][k]) continue;	//이미 방문
					
					//그 외의 경우
					q.add(new Monkey(nr, nc, k));	
					visited[nr][nc][k] = true ; // 방문체크
				}
			}
			//레벨 탐색 끝났으니 다음레벨로
			cnt++;
		}
		//레벨별 BFS 탐색이 무사히 끝났다면 목표점에 도달하지 못했다는 것
		System.out.println(-1);
	}
	
	//경계 체크
	private static boolean isInRange(int r, int c) {
		return r>=0 && r<H && c>=0 && c<W;
	}
}