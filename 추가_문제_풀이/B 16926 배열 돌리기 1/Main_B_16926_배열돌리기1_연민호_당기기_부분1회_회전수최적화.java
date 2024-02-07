package algo240206;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:32,240kb, 시간:528ms
 */
public class Main_B_16926_배열돌리기1_연민호_당기기_부분1회_회전수최적화 {

	static int N,M,R;	//배열의 행,열 크기와 로테이션 횟수
	static int[][] map;	//배열
	
	// 우 하 좌 상 델타 값
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	//배열의 행크기 입력
		M = Integer.parseInt(st.nextToken());	//배열의 열크기 입력
		R = Integer.parseInt(st.nextToken());	//로테이션 횟수 입력
		
		map = new int[N+1][M+1];	//배열 생성
		
		//배열 정보 입력
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//원본배열 출력
//		print();
		
		//회전할 테두리 개수 만큼 반복
		int cnt = Math.min(N,M)/2;
		for(int s=1; s<=cnt; s++) {	//s는 테두리의 왼쪽 상단 꼭지점(회전시 시작점) 의미
			rotate(s);
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	/**
	 * 테두리 하나를 R번 회전 시키는 메소드
	 * @param s (s, s)를 시작점으로 하는 테두리 회전
	 */
	private static void rotate(int s) {
		
		int optR  = R % ( 2*(N + M) -8*s + 4 );	//제자리로 돌아오는 회전을 제거한 회전 횟수
		
		for(int i=0; i<optR; i++) {
			//좌표값
			int r = s;
			int c = s;
			//방향
			int dir = 0;	//초기엔 오른쪽 이동
			
			//step 01. 시작점의 값을 저장
			int temp = map[r][c];
			
			//방향이 4번 바뀐다는 것은 한 바퀴를 돌았다는 의미
			while( dir<4 ) {
				//step 02. 다음 좌표값 받아오기
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				//이동할 좌표가 테두리를 벗어나지 않는 경우
				if( s<=nr && nr<=N-s+1 && s<=nc && nc<=M-s+1) {
					//값 당겨오기
					map[r][c] = map[nr][nc];	
					
					//다음 위치로 이동
					r = nr;
					c = nc;
				}
				//이동할 좌표가 테두리를 벗어나는 경우
				else dir++; //방향 전환
			}
			//step 03. 저장해놨던 첫번째 값을 시작점 아래 요소에 넣기
			map[s+1][s] = temp;
		}
	}
	
	//배열 출력
	private static void print() {
		System.out.println();
		for (int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}