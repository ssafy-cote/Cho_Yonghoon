package algo240227;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리:19012KB, 시간:252ms
 * 
 * @author SSAFY
 *
 */
public class Main_B_15683_감시_연민호_3차원배열 {
	//상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	//각 cctv 번호의 방향 정보 (여기서 숫자값은 델타정보의 인덱스 값을 의미)
	//dir[2] : [[0, 2], [1, 3]] 
	//위의 의미는 2번 cctv에서 감시 가능한 상하, 좌우 두 가지의 경우의 수를 저장해놓은 것
    static int[][][] dir = {
            null,	//미사용인덱스
            { {0},         {1},       {2},       {3} 		}, 	// 1번 cctv 경우의 수 - (상), (우), (하), (좌)
            { {0, 2},      {1, 3} 							}, 	// 2번 cctv 경우의 수 - (상,하), (우,좌) 
            { {0, 1},      {1, 2},    {2, 3},    {3, 0} 	}, 	// 3번 cctv 경우의 수 - (상,우), (우,하), (하,좌), (좌,상)
            { {0, 1, 2},   {1, 2, 3}, {2, 3, 0}, {3, 0, 1}	} , // 4번 cctv 경우의 수 - (상,우,하), (우,하,좌), (하,좌,상), (좌,상,우)
            { {0, 1, 2, 3} 									}, 	// 5번 cctv 경우의 수 - (상,우,하,좌)
    };

	static int N,M;	//행열 크기
	
	static int[][] origin; 	//초기 정보 저장
	static int[][] map; 	//실제 사용할 배열
	
	static List<CCTV> cctvs = new ArrayList<>();	//cctv 정보
	
	static int minBlindCnt;	//사각지대 최솟값
	
	static class CCTV{
		int r, c;
		int number;	//cctv번호
		
		int dirCnt = 4;	//변경해봐야 할 방향의 개수	(1, 3, 4번 카메라는 4개 방향의 경우의 수를 가짐)
		int dir;	//결정된 방향
		
		public CCTV(int r, int c, int number) {
			this.r = r;
			this.c = c;
			this.number = number;
			if(number==2) dirCnt = 2;		//2번 카메라는 2개의 방향을 결정해야 함
			else if(number==5)	dirCnt=1;	//5번 카메라는 방향 변경해볼 필요없음
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		origin = new int[N][M];
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int val = Integer.parseInt(st.nextToken());
				
				origin[i][j] = val;
				//cctv 정보 입력
				if(val!=0 && val !=6) cctvs.add( new CCTV(i, j, val) );
				
			}
		}
		
		minBlindCnt = Integer.MAX_VALUE;	//사각지대 개수를 충분히 큰 값으로 초기화
		
		selectDir( 0 );
		
		System.out.println(minBlindCnt);//결과 출력
	}

	/**
	 * idx번째 cctv의 방향을 설정하고 다음 cctv의 방향설정은 재귀로 넘김
	 * @param idx 방향을 설정할 cctv의 인덱스
	 */
	private static void selectDir(int idx) {
		//step 01. 모든 cctv의 방향 설정 완료
		if(idx == cctvs.size()) {
			arrayCopy();	//map 배열 초기화
			
			//step 02. 설정된 방향대로 감시영역 체크
			for(CCTV cctv : cctvs) watch(cctv);
			
			//step 03. 사각지대 개수 cnt
			int curBlindCnt = getCurBlindCnt();
			
			//step 04. 구한 사각지대 개수 최소값이라면 갱신
			minBlindCnt = Math.min(minBlindCnt, curBlindCnt);
			
			return;
		}
		
		CCTV cctv = cctvs.get(idx);	//방향을 결정할 cctv 정보
		
		//dir은 idx번째의 cctv가 바라볼 방향
		for(int dir=0; dir < cctv.dirCnt; dir++) {
			cctv.dir = dir;		//cctv 방향 결정
			
			selectDir( idx+1 );	//다음 cctv의 방향 설정
		}
	}



	/**
	 * 사각지대 개수 반환
	 * @return 사각지대 개수
	 */
	private static int getCurBlindCnt() {
		int cnt = 0;	//사각지대 개수
		for(int i=0; i< N;i ++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	/**
	 * 현재 cctv 감시 체크
	 * @param cctv
	 */
	private static void watch(CCTV cctv) {
		//dirCase[ cctv번호 ][ 선택한 방향 ]
		// 2번 cctv에 0번 방향이라면 상, 하 델타 인덱스를 뽑아냄
		for(int dir : dir[ cctv.number ][ cctv.dir ]) {
			
			//cctv의 좌표값 받아오기
			int r = cctv.r;
			int c = cctv.c;
			
			//해당 방향으로 한 칸씩 이동하며 감시영역 체크
			while(true) {
				//한 칸 이동
				r+= dr[dir];
				c+= dc[dir];
				
				//경계를 벗어나거나 벽(6)인 경우 해당 방향의 검사 마침
				if(r<0 || r>=N || c<0 || c>=M || map[r][c]==6) break;
				
				//위의 조건을 만족하지 않는다면 감시 영역 체크(-1로 체크)
				map[r][c] = -1;
			}
		}
	}

	/**
	 * map 배열을 초기상태로 되돌리기
	 */
	private static void arrayCopy() {
		for(int i=0; i<N;i++) {
			System.arraycopy(origin[i], 0, map[i], 0, M);
		}
	}
}