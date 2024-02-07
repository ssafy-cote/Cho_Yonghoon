package algo240207;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 메모리:16432KB, 시간:304ms
 */
public class Main_B_16935_배열돌리기3_연민호_메모리효율UP {

	static int N,M,R; //배열의 크기와 명령횟수
	
	static int[][] map;		//배열 정보
	static int[][] temp;	//임시 배열
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		int size = Math.max(N, M);
		
		//3,4 번에 의한 회전 시에도 사용이 가능하도록 N,M 중 큰 값으로 행열크기를 만듦
		map = new int[size][size];
		temp = new int[size][size];
		
		//배열 정보 입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// print();
		
		//명령 정보 입력
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<R; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			switch (cmd) {
				case 1:	one(); break;
				case 2:	two(); break;
				case 3:	three(); break;
				case 4: four(); break;
				case 5:	five(); break;
				case 6:	six(); break;
			}
			//print();
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				sb.append(map[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	//상하 반전
	private static void one() {
		//열별로 요소 값을 상하로 교환
//		for(int c=0; c < M; c++) {
//			for(int r1=0, r2=N-1; r1 < r2; r1++, r2--) {
//				int temp = map[r1][c];
//				map[r1][c] = map[r2][c];
//				map[r2][c] = temp;
//			}
//		}
		
		//1차원 배열 자체를 교환
		for(int r1=0, r2=N-1; r1 < r2; r1++, r2--) {
			int[] temp = map[r1];
			map[r1] = map[r2];
			map[r2] = temp;
		}
		
		
	}
	//좌우 반전
	private static void two() {
		//행별로 요소값을 좌우로 교환
		for(int r=0; r < N; r++) {
			for(int c1=0, c2=M-1; c1 < c2; c1++, c2--) {
				int temp = map[r][c1];
				map[r][c1] = map[r][c2];
				map[r][c2] = temp;
			}
		}
	}
	//오른쪽으로 90도
	private static void three() {
		for(int r=0; r < N; r++) {
			for(int c=0; c < M; c++) {
				temp[c][N - 1 - r] = map[r][c];
			}
		}
		
		//참조만 변경함으로써 배열을 새로 만들 필요가 없게 됨
		int[][] t = map;
		map = temp;
		temp = t;
		
		//M, N 값 바꾸기
		int temp = M;
		M = N;
		N = temp;
	}
	//왼쪽으로 90도
	private static void four() {
		
		for(int r=0; r < N; r++) {
			for(int c=0; c < M; c++) {
				temp[M - 1 - c][r] = map[r][c];
			}
		}
		//참조만 변경함으로써 배열을 새로 만들 필요가 없게 됨
		int[][] t = map;
		map = temp;
		temp = t;
		
		//M, N 값 바꾸기
		int temp = M;
		M = N;
		N = temp;
	}
	
	private static void five() {
		for(int r=0; r < N/2; r++) {
			for(int c=0; c < M/2; c++) {
				//4=>1
				temp[r][c] = map[r+N/2][c];
				//3=>4
				temp[r+N/2][c] = map[r+N/2][c+M/2];
				//2=>3
				temp[r+N/2][c+M/2] = map[r][c+M/2];
				//1=>2
				temp[r][c+M/2] = map[r][c];
			}
		}
		//참조만 변경함으로써 배열을 새로 만들 필요가 없게 됨
		int[][] t = map;
		map = temp;
		temp = t;
	}
	
	private static void six() {
		for(int r=0; r < N/2; r++) {
			for(int c=0; c < M/2; c++) {
				//2=>1
				temp[r][c] = map[r][c+M/2];
				//3=>2
				temp[r][c+M/2] = map[r+N/2][c+M/2];
				//4=>3
				temp[r+N/2][c+M/2] = map[r+N/2][c];
				//1=>4
				temp[r+N/2][c] = map[r][c];
			}
		}
		//참조만 변경함으로써 배열을 새로 만들 필요가 없게 됨
		int[][] t = map;
		map = temp;
		temp = t;
	}
	//배열 출력
	private static void print() {
		for(int[] arr: map) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println();
	}
}