package algo240328;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:12136kb, 시간:96ms
 * 
 * [해결에 대한 고민]
 * 
 * 빈 칸에 어떤 파이프를 놓아야 할 지 결정할 때, 빈 칸으로부터 
 * 상하좌우에 위치한 블록이 빈 칸과 연결되어 있는지 판단해야 함.
 * 이를 위해 비트마스킹을 활용
 * 
 * 6 5 4 3 2 1 0	인덱스
 * 4 3 2 1 + = |	블록
 * 
 * 1 0 0 1 1 0 1	현재 칸 기준 상단의 블록이 현재 칸쪽으로 연결된 블록 정보 
 * 0 1 1 0 1 0 1	현재 칸 기준 하단의 블록이 현재 칸쪽으로 연결된 블록 정보 
 * 0 0 1 1 1 1 0	현재 칸 기준 좌단의 블록이 현재 칸쪽으로 연결된 블록 정보 
 * 1 1 0 0 1 1 0	현재 칸 기준 우단의 블록이 현재 칸쪽으로 연결된 블록 정보 
 * 

 * [문제 해결 프로세스]
 * 
 * 1. 빈 칸 찾기
 * 
 * 2. 찾은 빈 칸에 파이프를 놓아보기
 * 
 * 
 * 어떤 파이프를 놓아야하는지 결정하는 방법
 * 
 * 1. 상(0) 하(1) 좌(2) 우(3) 연결 여부를 비트로 체크
 *  => 빈 칸 기준 왼쪽이 연결되있다면?
 *  	int isConnected=0
 *  	isConnected |= 1<<2;	//좌 연결여부 체크
 * 2. 사방에 관한 연결 여부를 모두 체크했을 때
 *  	아래와 같은 경우에 따라 처리
 * 
 * 비트이니 인덱스와 거꾸로 생각
 * 우3 좌2 하1 상0
 * 0   0   0   0 - (0) 놓을 수 없음
 * 0   0   1   1 - (3) 상하가 연결됨 '|' 놓아야 함
 * 1   1   0   0 - (12)좌상이 연결됨 '-' 놓아야 함
 * 1   1   1   1 - (15)사방이 연결됨 '+' 놓아야 함
 * 1   0   1   0 - (10)우하가 연결됨 '1' 놓아야 함
 * 1   0   0   1 - (9) 우상이 연결됨 '2' 놓아야 함
 * 0   1   0   1 - (5) 상좌가 연결됨 '3' 놓아야 함
 * 0   1   1   0 - (6) 좌하가 연결됨 '4' 놓아야 함
 * 
 * 메모리:12196KB, 시간:92ms
 * 
 * @author SSAFY
 *
 */
public class Main_BJ_2931_가스관_연민호_모든빈칸을탐색 {

	//상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int R,C;
	static char[][] map;
	
	static int[] start;	//M의 좌표 정보
	
	static int[] index;
	
	static int[] isPossible = {
			0b1001101,	//상	현재 칸 기준 상단의 블록이 현재 칸쪽으로 연결된 블록 정보, 1인 경우 연결됨
			0b0110101,	//하	현재 칸 기준 하단의 블록이 현재 칸쪽으로 연결된 블록 정보 
			0b0011110,	//좌	현재 칸 기준 좌단의 블록이 현재 칸쪽으로 연결된 블록 정보 
			0b1100110	//우	현재 칸 기준 우단의 블록이 현재 칸쪽으로 연결된 블록 정보 
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R+1][C+1];
		for(int i=1;i<=R; i++) {
			String str = br.readLine();
			for(int j=1; j<=C; j++) {
				map[i][j] = str.charAt(j-1);
			}
		}
		
		//입력 체크
//		for(int i=1;i<=R; i++) {
//			for(int j=1; j<=C; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();
		//비트 마스킹 사용을 위해 인덱스값 저장
		index = new int['|'+1];
		index['|'] = 0;
		index['-'] = 1;
		index['+'] = 2;
		index['1'] = 3;
		index['2'] = 4;
		index['3'] = 5;
		index['4'] = 6;
		
		//step 01. 빈 칸 탐색
		for(int r=1; r<=R; r++) {
			for(int c=1; c<=C; c++) {
				if(map[r][c]=='.') {
					//step 02. 빈 칸인 경우 파이프를 놓아보기
					setPipe(r, c);
				}
			}
		}
	}

	/**
	 * 빈 칸 (r,c) 좌표에 파이프 설치
	 * @param r 빈칸의 행 좌표
	 * @param c 빈칸의 열 좌표
	 * @return
	 */
	private static void setPipe(int r, int c) {
		//step 03. 빈 칸에 블록 설치해보기
		int connectInfo = 0;	//빈 칸과 연결되어 있는 방향에 대한 정보
		for(int dir=0; dir<4; dir++) {
			int nr = r+dr[dir];
			int nc = c+dc[dir];

			if(!isInRange(nr, nc)) continue;						//경계 벗어남
			char block = map[nr][nc];
			if(block=='.' || block=='Z' || block=='M') continue;	//빈 칸, Z, M
			if((isPossible[dir] & 1<<index[block]) == 0) continue;	//dir방향의 다음 칸(nr, nc)이 현재 칸쪽으로 연결되어 있지 않음
			
			//연결 정보에 해당 방향(dir)의 정보 추가
			connectInfo |= (1<<dir);
		}
		
		char alpha;
		if(connectInfo==0) return;				//설치 불가 return;
		else if(connectInfo==3) alpha='|';		//상하
		else if(connectInfo==12) alpha='-';		//좌우
		else if(connectInfo==15) alpha='+';		//상하좌우
		else if(connectInfo==10) alpha='1';		//하우
		else if(connectInfo==9) alpha='2';		//상우
		else if(connectInfo==5) alpha='3';		//좌상
		else alpha='4';							//좌하
		
		//step 03. 설치 가능 시 출력 후 종료
		System.out.printf("%d %d %c", r, c, alpha);
		System.exit(0);
	}

	private static boolean isInRange(int r, int c) {
		return r>=1 && r<=R && c>=1 && c<=C;
	}
}