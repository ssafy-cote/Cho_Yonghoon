package algo240207;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:29,648kb, 시간:155ms
 */
public class Solution_1861_정사각형방_연민호_재귀_중복체크 {

	static int[][] map; // 방 정보
	static int N; // 방의 행열 크기
	
	static boolean[][] visited;	//중복 탐색 방지
	
	static int start; 	//방의 시작점(현재 방번호)
	static int curCnt;	//현재 탐색중인 것의 방문 횟수
	static int maxNum; 	//최대이동가능횟수인 방번호
	static int maxCnt; 	//최대이동가능횟수
	
	//상하좌우 델타
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());	//방의 행열 크기
			map = new int[N][N];	//방 입력 공간 생성
			
			maxNum=Integer.MAX_VALUE;	//최대이동가능횟수인 방번호 초기화
			maxCnt = 0;	//최대이동가능횟수 초기화
			
			//방정보 입력
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			visited = new boolean[N][N];
			
			//step 01. 모든 정점을 시작점으로 이동가능횟수 탐색
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(visited[i][j]) continue;
					
					start = map[i][j];		//시작점의 방번호
					visited[i][j] = true;	//(i,j) 방문 처리
					curCnt = 1;				//(i,j)를 시작점으로 한 이동횟수 저장
					move(i, j);				//탐색 시작
					
					//step 02. 탐색 완료 후, 이동횟수에 따라 결과 갱신
					//이동횟수가 갱신돼야하는 경우
					if(curCnt > maxCnt 
							//이동횟수 같으나
							//현재 방번호가 최대이동가능횟수인 방번호보다 작은 경우
							|| (curCnt == maxCnt && start < maxNum)) {
						maxNum = start;
						maxCnt = curCnt;
					}
				}
			}
			sb.append('#').append(tc).append(' ').append(maxNum).append(' ').append(maxCnt).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * (r,c) 좌표 기준 사방을 탐색하며 이동 가능여부 체크 후, 이동
	 * @param r 행 좌표
	 * @param c 열 좌표
	 */
	private static void move(int r, int c) {
		//이동할 방향의 좌표값 담아놓을 변수
		for(int d=0; d<4; d++) {
			//이동할 방향의 좌표
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if( nr<0 || nr>=N || nc<0 || nc>=N ) continue; 	//이동할 방향에 방이 존재하고(경계체크)
			if(visited[nr][nc]) continue;						//(nr,nc) 좌표를 방문학 적이 있는 경우 return
			
			//-1, 1차이가 나는 경우 이동
			int diff = map[r][c] - map[nr][nc];
			if(Math.abs(diff)==1) {
				visited[nr][nc] = true;	//방문체크
				curCnt++;				//방문 개수 cnt
				
				if(diff==1) start = map[nr][nc];	//-1차이가 나는 곳으로 이동하는 경우, 출발 위치를 갱신
				
				move(nr, nc);
			}
		}
	}

}