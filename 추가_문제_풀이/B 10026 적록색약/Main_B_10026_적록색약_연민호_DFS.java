package algo240220;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 메모리:12512KB, 시간:84ms
 */
public class Main_B_10026_적록색약_연민호_DFS {
	//상 하 좌 우 델타 값
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static char[][] painting;	//RGB 정보
	
	static int N;
	
	static boolean[][] visited;	//방문체크
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		//RGB 정보 입력
		painting = new char[N][];
		for(int i=0; i<N; i++) {
			painting[i] = br.readLine().toCharArray();
		}
		
		//적록색약 아닌 사람
		int cnt = 0;	//적록색약 아님 사람 count
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					dfs(i, j, painting[i][j]);
					cnt++;
				}
			}
		}
		//적록색약인 사람
		int cnt2 = 0;	//적록색약인 사람 count
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					char color = painting[i][j];
					if(color=='B') dfs(i, j, color);
					else dfs2(i, j);	//적록색약 R,G 전용 dfs
					cnt2++;
				}
			}
		}
		System.out.println(cnt+" "+ cnt2);
	}
	/**
	 * 현재 좌표에서 사방의 좌표 중 같은 색의 좌표를 골라 탐색을 이어감
	 * @param sr	탐색의 시작점 행 값
	 * @param sc	탐색의 시작점 열 값
	 * @param color	현재 탐색의 시작점의 색 같은 색의 정점을 체크하기 위한 매개변수
	 */
	private static void dfs(int r, int c, int color) {
		visited[r][c] = true;	//방문체크
		
		//사방
		for(int d=0; d<4; d++) {
			//다음 좌표
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;	//경계 벗어남
			if(visited[nr][nc]) continue;		//이미 방문
			
			//다음 좌표가 같지 않음(탐색 이어갈 수 없음)
			if(color != painting[nr][nc]) continue;
			
			dfs(nr, nc, painting[nr][nc]);
		}
	}
	
	/**
	 * 현재 좌표에서 사방의 좌표 중 R,G인 좌표를 골라 탐색을 이어감
	 * 적록색약을 위한 탐색
	 * @param r	현재 탐색 중인 좌표의 행 값
	 * @param c	현재 탐색 중인 좌표의 열 값
	 * @param color	현재 탐색의 시작점의 색 같은 색의 정점을 체크하기 위한 매개변수
	 */
	private static void dfs2(int r, int c) {
		visited[r][c] = true;	//방문체크
		
		//사방
		for(int d=0; d<4; d++) {
			//다음 좌표
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr<0 || nr>=N || nc<0 || nc>=N) continue;	//경계 벗어남
			if(visited[nr][nc]) continue;		//이미 방문
			
			//다음 좌표가 R,G 모두 아닌 경우(탐색이어 갈 수 없음)
			if(painting[nr][nc]!='R' && painting[nr][nc]!='G') continue; 
			
			dfs2(nr, nc);	
		}
	}
}