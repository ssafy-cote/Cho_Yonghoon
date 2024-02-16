package algo240216;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:100784kb, 시간:532ms
 */
public class Main_B_7576_토마토_BFS {

	//상하좌우 델타값
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	static int M, N; //상자 가로, 세로 크기
	static int[][] map;	//토마토 정보

	static int unripeCnt; //익지않은 토마토 개수

	static Queue<Tomato> q = new ArrayDeque<>();	//탐색정보 저장

	static class Tomato{
		int r,c;
		int day;	//익은 날짜

		public Tomato(int r, int c, int day) {
			this.r = r;
			this.c = c;
			this.day = day;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					q.offer(new Tomato(i,j,0));	//익은 토마토부터 시작
				}
				else if(map[i][j]==0) unripeCnt++;	//익지 않은 토마토 개수 새기
			}
		}
		//익지 않은 토마토가 0개라면 bfs 돌릴필요 없음
		if(unripeCnt==0) {
			System.out.println(0);
			return;
		}

		bfs();

	}

	private static void bfs() {

		while(!q.isEmpty()) {
			Tomato t = q.poll();

			for(int d=0; d<4 ;d++) {
				int nr = t.r + dr[d];
				int nc = t.c + dc[d];

				if(nr<0 || nr>=N || nc<0 || nc>=M) continue;	//경계 벗어남
				if(map[nr][nc]!=0) continue;	//이미 익은 토마토

				q.offer(new Tomato(nr, nc, t.day+1));
				map[nr][nc] = 1;	//토마토 익힘 처리
				unripeCnt--;		//익지 않은 토마토 개수 -1
			}
			//해당 토마토에 대한 탐색이 끝났을 때, 익지 않은 토마토가 0개라면 날짜 출력 후 종료
			if(unripeCnt==0) {
				System.out.println(t.day+1);
				return;
			}
		}
		// 익지않은 토마토가 0개라면? 날짜 출력하고 끝내기
		//익지 않은 토마토가 있는 경우 -1 출력
		System.out.println(-1);
	}

}