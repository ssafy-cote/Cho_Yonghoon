package algo240227;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리:21308KB, 시간:236ms
 * @author SSAFY
 *
 */
public class Main_B_15683_감시_연민호_메소드분리 {

	static int N, M;
	
	//상 우 하 좌
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int[][] origin; 	//초기 정보 저장
	static int[][] map; 	//실제 사용할 배열

	static int minBlindCnt;	//블라인드 최소값
	
	static List<CCTV> cctvs = new ArrayList<>();	//cctv 정보
	
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
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				origin[i][j] = val;
				//cctv 정보 입력
				if(val!=0 && val !=6) cctvs.add( new CCTV(i, j, val) );
			}
		}

		minBlindCnt = Integer.MAX_VALUE;	//사각지대 개수를 충분히 큰 값으로 초기화
		
		selectDir(0);

		System.out.print(minBlindCnt);
	}
	
	/**
	 * idx번째 cctv의 방향을 설정하고 다음 cctv의 방향설정은 재귀로 넘김
	 * @param idx
	 */
	static void selectDir(int idx) {
		//step 01. 모든 cctv의 방향 설정 완료
		if (idx == cctvs.size()) {
			arrayCopy();	//map 배열 초기화
			
			//step 02. 설정된 방향대로 감시영역 체크
			for(CCTV cctv : cctvs) watch(cctv);
			
			//step 03. 사각지대 개수 cnt
			int curBlindCnt = getCurBlindCnt();
			
			//step 04. 구한 사각지대 개수 최소값이라면 갱신
			minBlindCnt = Math.min(minBlindCnt, curBlindCnt);
			
			return;
		}

		CCTV cctv = cctvs.get(idx);	// 방향을 결정할 카메라
		
		//dir은 idx번째의 cctv가 바라볼 방향
		for(int dir=0; dir < cctv.dirCnt; dir++) {
			cctv.dir = dir;		//cctv 방향 결정
			
			selectDir( idx+1 );	//다음 cctv의 방향 설정
		}
	}
	
	/**
	 * 현재 결정된 카메라 방향(dir)에 따라 (r,c) 좌표로부터 감시 진행
	 * @param r
	 * @param c
	 * @param dir	결정된 카메라 방향
	 */
	static void watch(CCTV cctv) {
		int r = cctv.r;
		int c = cctv.c;
		int number = cctv.number;
		int dir = cctv.dir;
		
		watchDir(r, c, dir);	//현재 방향 감시

		if (number == 2) {			//2번 카메라의 경우
			watchDir(r, c, (dir + 2) % 4);	// 현재방향(dir)과 반대 방향 감시
		} 
		else if (number == 3) {		//3번 카메라의 경우
			watchDir(r, c, (dir + 1) % 4);	// 현재방향(dir)에서 오른쪽90도 회전한 방향 감시
		} 
		else if (number == 4) {		//4번 카메라의 경우
			watchDir(r, c, (dir + 1) % 4);	// 현재방향(dir)에서 오른쪽90도 회전한 방향 감시
			watchDir(r, c, (dir + 2) % 4);	// 현재방향(dir)과 반대 방향 감시
		} 
		else if (number == 5){		//5번 카메라의 경우, 모든 방향 감시
			watchDir(r, c, (dir + 1) % 4);	// 현재방향(dir)에서 오른쪽90도 회전한 방향 감시
			watchDir(r, c, (dir + 2) % 4);	// 현재방향(dir)과 반대 방향 감시
			watchDir(r, c, (dir + 3) % 4);	// 현재방향(dir)에서 오른쪽 180도 회전한 방향 감시
		}
	}
	
	/**
	 * (r,c) 좌표로부터 dir 방향을 감시
	 * @param r
	 * @param c
	 * @param dir
	 */
	static void watchDir(int r, int c, int dir) {
		while(true) {
			//한 칸 이동
			r+= dr[dir];
			c+= dc[dir];
			
			//경계를 벗어나거나 벽(6)인 경우 해당 방향의 검사 마침
			if(r<0 || r>=N || c<0 || c>=M || map[r][c]==6) return;
			
			//위의 조건을 만족하지 않는다면 감시 영역 체크(-1로 체크)
			map[r][c] = -1;
		}
	}
	
	/**
	 * 사각지대 개수 반환
	 * @return 사각지대 개수
	 */
	private static int getCurBlindCnt() {
		int cnt = 0;	//사각지대 개수
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) cnt++;
			}
		}
		return cnt;
	}

	/**
	 * map 배열을 초기상태로 되돌리기
	 */
	private static void arrayCopy() {
		for(int i=0; i<N; i++) {
			System.arraycopy(origin[i], 0, map[i], 0, M);
		}
	}
}