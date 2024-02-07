package algo240207;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:29,316kb, 시간:261ms
 */
public class Solution_1861_정사각형방_연민호_재귀 {

	static int[][] map; // 방 정보
	static int N; // 방의 행열 크기
	
	static int start; 	//방의 시작점(현재 방번호)
	static int curCnt;	//현재 탐색중인 것의 방문 횟수
	static int maxNum; //최대이동가능횟수인 방번호
	static int maxCnt; //최대이동가능횟수
	
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
			
			maxNum = Integer.MAX_VALUE;	//최대이동가능횟수인 방번호 초기화
			maxCnt = 0;	//최대이동가능횟수 초기화
			
			//방정보 입력
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//step 01. 모든 정점을 시작점으로 이동가능횟수 탐색
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					start = map[i][j];	//시작점의 방번호
					curCnt = 1;
					move(i, j);
					
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
	 * 사방 탐색을 하며 이동가능한 곳이 있다면 해당 지점을 기준으로 다시 사방탐색하도록 재귀 호출
	 * 이동가능한 곳이 없다면 return됨
	 * @param r 행 좌표
	 * @param c 열 좌표
	 */
	private static void move(int r, int c) {
		
		//이동할 방향의 좌표값 담아놓을 변수
		for(int d=0; d<4; d++) {
			//이동할 방향의 좌표
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if( nr<0 || nr>=N || nc<0 || nc>=N ) continue; 	//경계 넘어감
			if(map[r][c]+1 != map[nr][nc]) continue;		//1크지 않음(이동 불가)
			
			curCnt++;
			move(nr, nc); 
			return; //1만큼 큰 곳은 한 곳 밖에 없기 때문에 다른 방향 탐색 불필요
			//break;로 하던 return으로 하던 같은 동작 - break 또한 반복문을 끝낸 후, 더이상 수행될 코드가 없어 return되기 때문
		}
	}

}