package algo240220;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * 메모리:12888KB, 시간:92ms
 */
public class Main_B_10026_적록색약_연민호_BFS_중복코드제거 {
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
					bfs(i, j, painting[i][j]);
					cnt++;
				}
				//해당 정점이 처리된 후 초록색을 빨강색으로 변경(최적화)
				if(painting[i][j] == 'G') painting[i][j] = 'R';
			}
		}
		
		//적록색약인 사람
		int cnt2 = 0;	//적록색약인 사람 count
		visited = new boolean[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					bfs(i, j, painting[i][j]);
					cnt2++;
				}
			}
		}
		System.out.println(cnt+" "+cnt2);
	}
	
	/**
	 * 시작 정점에서부터 사방의 좌표 중 같은 색의 좌표를 골라 탐색을 이어감
	 * 적록색약을 위한 탐색
	 * @param sr	탐색의 시작점 행 값
	 * @param sc	탐색의 시작점 열 값
	 * @param color	현재 탐색의 시작점의 색 같은 색의 정점을 체크하기 위한 매개변수
	 */
	private static void bfs(int sr, int sc, int color) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;	//방문체크
		
		while(!q.isEmpty()) {
			int[] v = q.poll();
			int r = v[0];
			int c = v[1];
			
			//사방
			for(int d=0; d<4; d++) {
				//다음 좌표
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;	//경계 벗어남
				if(visited[nr][nc]) continue;		//이미 방문
				
				//다음 좌표가 같지 않음(탐색 이어갈 수 없음)
				if(color != painting[nr][nc]) continue;
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
}