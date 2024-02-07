package algo240207;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:29,344kb, 시간:249ms
 */
public class Solution_1861_정사각형방_연민호_반복문 {

	static int[][] map = new int[100][100];	//방 정보
	static int N;
	
	static int maxNum;	//최대이동한 방의 번호
	static int maxCnt;	//최대 이동 수
	
	//상 하 좌 우 델타
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {
			N = Integer.parseInt(br.readLine());
			maxNum = 0;	//최대이동한 방의 번호 초기화
			maxCnt = 0;	//최대 이동 수 초기화
			map = new int[N][N];	//방정보 초기화
			
			//방정보 입력
			for(int i=0;i<N;i++) {
				StringTokenizer st= new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					move(i,j, map[i][j] );	//이동
				}
			}
			sb.append('#').append(tc).append(' ').append(maxNum).append(' ').append(maxCnt).append('\n');
		}
		System.out.println(sb);
	}
	
	/**
	 * 사방 탐색을 반복하며 이동횟수 구하는 메소드
	 * @param r 행좌표
	 * @param c 열좌표
	 */
	private static void move(int r, int c, int start ) {
		
		int cnt=1;
		//사방 중 이동가능한 좌표를 발견하면 해당 좌표로 이동하며 이동횟수 cnt 후, 또 다시 같은 작업을 반복
		A : while(true) {
			for(int d=0; d<4; d++) {
				//이동할 위치
				int nr = r+dr[d];
				int nc = c+dc[d];
				
				if(nr<0 || nr>=N || nc<0 || nc>=N) continue;	//경계 벗어남
				if(map[r][c]+1 != map[nr][nc]) continue;		//1차이가 아님
				
				//실제 이동
				r = nr;
				c = nc;
				cnt++;	//이동횟수 증가
				continue A;
			}
			
			//이동하지 못했다면? ( continue A 구문을 만난적이 없다면 더 이상 이동 불가능 )
			//현재이동가능횟수가 최대 이동가능횟수보다 크다면? 또는
			if(cnt > maxCnt 
					//현재이동가능횟수가 최대이동가능횟수와 같고
					//현재 방번호가 최대이동가능횟수인 방번호보다 작다면?
					|| (cnt == maxCnt && start < maxNum)) {
				maxCnt = cnt;	//최대 이동횟수 변경
				maxNum = start;	//최대이동가능한 방번호 변경
			}
			return;	//해당 시작좌표에서 탐색 끝났으므로 return
		}
	}	

}