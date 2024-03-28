package algo0328;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리: 12168KB, 시간:92ms
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
 * 1. 가스관의 시작점 찾기
 * 	=> M 탐색
 * 
 * 2. 가스관 시작점을 따라 빈 칸으로 이동
 * 
 * 	a. 시작점에서부터 이동할 방향 정하기
 * 	b. 정해진 방향대로 이동하며 블록에 따라 방향정보를 변경
 * 		=> 이 때, 파이프를 따라 이동하므로 다음 방향이 경계를 넘어갈 일이 없음!
 * 		- '|','+','-' 의 경우 해당 좌표로 이동하고 방향은 유지
 * 		- '1' 인 경우
 * 			if(dir==0) dir=3;	//'1'을 위(0)로 가면서 만난 경우 (우로 변경)
 *			else dir=1;			//'1'을 좌(2)로 가면서 만난 경우 (하로 변경)
 * 		- '2' 인 경우
 * 			if(dir==1) dir=3;	//'2'를 하(1)로 가면서 만난 경우 (우로 변경)
 *			else dir=0;			//'2'를 좌(2)로 가면서 만난 경우 (상으로 변경)
 * 		- '3' 인 경우
 * 			if(dir==1) dir=2;	//'3'을 하(1)로 가면서 만난 경우 (좌로 변경) 
 *			else dir=0;			//'3'을 우(3)로 가면서 만난 경우 (상으로 변경)
 * 		- '4' 인 경우
 *			if(dir==3) dir=1;	//'4'를 우(3)로 가면서 만난 경우 (하로 변경)
 *			else dir=2;			//'4'를 위(0)로 가면서 만난 경우 (좌로 변경)
 * 
 * 
 * 3. 빈 칸에 놓을 수 있는 블록을 찾아 좌표 함께 출력
 * 
 * 
 * 어떤 파이프를 놓아야하는지 결정하는 방법
 * 
 * 1. 상(0) 하(1) 좌(2) 우(3) 연결 여부를 비트로 체크
 *  => 빈 칸 기준 왼쪽이 연결되있다면?
 *  	int connectInfo=0
 *  	connectInfo |= 1<<2;	//좌 연결여부 체크
 * 2. 사방에 관한 연결 여부를 모두 체크했을 때
 *  	아래와 같은 경우에 따라 처리
 * 
 * connectInfo의 값에 따라 놓을 파이프 결정
 * 3(우) 2(좌) 1(하) 0(상)
 * 
 * 0 0 1 1 - (3) 상하가 연결됨 '|' 놓아야 함
 * 1 1 0 0 - (12)좌상이 연결됨 '-' 놓아야 함
 * 1 1 1 1 - (15)사방이 연결됨 '+' 놓아야 함
 * 1 0 1 0 - (10)우하가 연결됨 '1' 놓아야 함
 * 1 0 0 1 - (9) 우상이 연결됨 '2' 놓아야 함
 * 0 1 0 1 - (5) 상좌가 연결됨 '3' 놓아야 함
 * 0 1 1 0 - (6) 좌하가 연결됨 '4' 놓아야 함
 * 
 * 
 * @author SSAFY
 *
 */
public class Main_BJ_2931_가스관_연민호_가스관을따라가며빈칸찾기 {

	//상하좌우
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int R,C;
	
	static char[][] map;
	
	static int[] start;	//M의 좌표 정보
	
	static int[] index;
	
	//0b는 이진수를 만들 때 사용됨
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
				//step01. 시작점의 좌표정보 저장
				if(map[i][j]=='M') start = new int[]{i,j};
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
		
		//step 02. 시작점에서부터 파이프를 따라가며 빈칸 탐색
		int[] blank = getBlank();
		//체크
//		System.out.println("탐색한 빈 칸의 좌표 : "+Arrays.toString(blank));
		
		
		//step 03. 빈 칸에 블록 설치해보기
		setPipe(blank[0], blank[1]);
	}

	/**
	 * 블록을 설치해야할 빈 칸의 좌표를 반환
	 * @return
	 */
	private static int[] getBlank() {
		int r = start[0];
		int c = start[1];
		
		int dir;	//파이프를 따라 이동할 방향
		//1) 시작점에서 파이프의 방향 찾기
		for(dir=0; dir<4; dir++) {
			int nr = r+dr[dir];
			int nc = c+dc[dir];
			
			if(!isInRange(nr, nc)) continue;						//경계 벗어남
			char block = map[nr][nc];
			if(block=='.' || block=='Z' || block=='M') continue;	//빈 칸, Z, M
			if((isPossible[dir] & 1<<index[block]) == 0) continue;	//현재 칸과 현재 방향의 다음 칸이 연결되어 있지 않음
			
			break;
		}
		//체크
//		System.out.printf("시작 파이프 좌표 (r, c) : (%d, %d), 방향:%d%n",r, c, dir);
		
//		System.out.println("탐색 시작");
		//2) 해당 방향으로 파이프를 따라가며 빈 칸 탐색
		while(true) {
			int nr = r+dr[dir];
			int nc = c+dc[dir];
//			System.out.printf("(nr, nc):(%d, %d), map[nr][nc]:%c:%n ", nr, nc, map[nr][nc]);

			char block = map[nr][nc];
			if(block=='.') return new int[] {nr, nc};	//빈 칸인 경우, 블록을 설치해야할 공간이므로 반환
			
			//'1'~'4'블록은 방향이 변경되므로 turn()을 이용해 방향 변경(나머지 블록은 방향 변하지 않음)
			dir = turn(block, dir);
			
			//이동
			r = nr;
			c = nc;
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
		if(connectInfo==3) alpha='|';			//상하
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

	/**
	 * block 정보와 방향이 주어졌을 때 해당 블록을 지나면 바뀌는 방향값을 반환
	 * @param block
	 * @param dir
	 * @return
	 */
	private static int turn(char block, int dir) {
		if(block=='1') {		//블록 1 만난 경우
			if(dir==0) dir=3;		//위(0)로 이동하다 만나면 우(3)로 변경
			else dir=1;				//좌(2)로 이동하다 만나면 하(1)로 변경
		}else if(block=='2') {	//블록 2 만난 경우
			if(dir==2) dir=0;		//좌(2)로 이동하다 만나면 상(0)으로 변경
			else dir=3;				//하(1)로 이동하다 만나면 우(3)로 변경
		}else if(block=='3') {	//블록 3 만난 경우
			if(dir==3) dir=0;		//우(3)로 이동하다 만나면 상(0)으로 변경
			else dir=2;				//하(1)로 이동하다 만나면 좌(2)로 변경
		}else if(block=='4') {					//블록 4 만난 경우
			if(dir==3) dir=1;		//우(3)로 이동하다 만나면 하(1)로 변경
			else dir=2;				//상(0)으로 이동하다 만나면 좌(2)로 변경	
		}
		return dir;
	}
	
	private static boolean isInRange(int r, int c) {
		return r>=1 && r<=R && c>=1 && c<=C;
	}
}