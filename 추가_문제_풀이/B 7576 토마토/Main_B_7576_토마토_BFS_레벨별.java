package algo240216;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * 메모리:101344kb, 시간:580ms
 */
public class Main_B_7576_토마토_BFS_레벨별 {
	
	//상하좌우 델타값
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int M, N; //상자 가로, 세로 크기
	static int[][] map;	//토마토 정보
	
	static int unripeCnt; //익지않은 토마토 개수
	
	static Queue<Tomato> q = new ArrayDeque<>();	//탐색정보 저장
	
	static class Tomato{
		int r,c;

		public Tomato(int r, int c) {
			this.r = r;
			this.c = c;
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
					q.offer(new Tomato(i,j));	//익은 토마토부터 시작
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
		
		int day = 0;
		while(!q.isEmpty()) {
			//depth별 탐색( 여기서 레벨은 날짜 의미 )
			int size = q.size();
			while(size-- > 0) {
				Tomato t = q.poll();
				
				for(int d=0; d<4 ;d++) {
					int nr = t.r + dr[d];
					int nc = t.c + dc[d];
					
					if(nr<0 || nr>=N || nc<0 || nc>=M) continue;	//경계 벗어남
					if(map[nr][nc]!=0) continue;	//이미 익은 토마토
					
					q.offer(new Tomato(nr,nc));
					map[nr][nc] = 1;	//토마토 익힘 처리
					unripeCnt--;		//익지 않은 토마토 개수 -1
				}
			}
			day++;	//해당 depth(날짜) 탐색 끝나면 depth(날짜) 증가
			//depth 탐색이 끝났을 때 익지않은 토마토가 0개라면? 날짜 출력하고 끝내기
			if(unripeCnt==0) {
				System.out.println(day);
				return;
			}
		}
		//익지 않은 토마토가 있는 경우 -1 출력
		System.out.println(-1);
	}

}