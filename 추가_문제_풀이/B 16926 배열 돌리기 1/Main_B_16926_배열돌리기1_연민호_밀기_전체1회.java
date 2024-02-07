package algo240206;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:32,196kb, 시간:612ms
 */
public class Main_B_16926_배열돌리기1_연민호_밀기_전체1회 {

	static int N,M,R;	//배열의 행,열 크기와 로테이션 횟수
	static int[][] map;	//배열
	
	// 하 우 상 좌	델타 값
	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};
	
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
		
		//회전 수(R) 만큼 반복
		for(int i=0;i<R;i++) {
//			print();
			rotate();	//전체 1 회전
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	//전체 1회전 메소드
	private static void rotate() {
		
		//시작점의 수만큼 반복
		int cnt = Math.min(N,M)/2;	//회전할 테두리 개수
		for(int s=1; s<=cnt; s++) {
			//현재 위치 좌표
			int r = s;
			int c = s;
			int dir = 0;	//초기 방향

			int temp = map[r][c];	//이전 위치의 값을 담아 놓을 변수
			int temp2 = 0;	//담길위치의 원래 값 받을 변수
			
			//방향이 4번 바뀐다는 것은 한 바퀴를 돌았다는 의미
			while(dir<4) {
				
				//이동할 좌표
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				//이동할 좌표가 경계를 벗어나지 않는 경우
				if(nr >= s && nr <= N-s+1 && nc >= s && nc <= M-s+1)  {
					//실제 이동
					r += dr[dir];
					c += dc[dir];
					
					//값 변경 작업
					temp2 = map[r][c];	//이동한 값 temp에 받아놓기
					map[r][c] = temp;
					temp = temp2;
				}
				//이동할 좌표가 경계를 벗어나는 경우
				else dir++;	//방향전환
			}
		}
	}

	//배열 출력
	private static void print() {
		for (int i=1;i<=N;i++) {
			for(int j=1;j<=M;j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}