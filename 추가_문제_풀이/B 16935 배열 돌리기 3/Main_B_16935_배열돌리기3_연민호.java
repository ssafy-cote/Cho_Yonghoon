package algo240207;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * 메모리:45632kb, 시간:380ms
 */
public class Main_B_16935_배열돌리기3_연민호 {

	static int N,M,R; //배열의 크기와 명령횟수
	
	static int[][] map;		//배열 정보
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
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
		int[][] result = new int[M][N];
		
		for(int r=0; r < N; r++) {
			for(int c=0; c < M; c++) {
				result[c][N - 1 - r] = map[r][c];
			}
		}
		
		map = result;
		
		//M, N 값 바꾸기
		int temp = M;
		M = N;
		N = temp;
	}
	//왼쪽으로 90도
	private static void four() {
		int[][] result = new int[M][N];
		
		for(int r=0; r < N; r++) {
			for(int c=0; c < M; c++) {
				result[M - 1 - c][r] = map[r][c];
			}
		}
		map = result;
		
		//M, N 값 바꾸기
		int temp = M;
		M = N;
		N = temp;
	}
	
	private static void five() {
		int[][] result = new int[N][M];
		
		// 1번 => 2번 
		for(int r=0; r < N/2; r++) {
			for(int c=0; c < M/2; c++) {
				//열크기의 반만큼 오른쪽 이동
				result[r][c + M/2] = map[r][c];
			}
		}
		// 2번 => 3번
		for(int r=0; r < N/2; r++) {
			for(int c = M/2; c < M; c++) {
				//행크기의 반만큼 아래로 이동
				result[r + N/2][c] = map[r][c];
			}
		}
		// 3번 => 4번
		for(int r=N/2; r < N; r++) {
			for(int c=M/2; c < M; c++) {
				//열크기의 반만큼 왼쪽 이동
				result[r][c - M/2] = map[r][c];
			}
		}
		// 4번 => 1번 
		for(int r=N/2; r < N; r++) {
			for(int c=0; c < M/2; c++) {
				//행크기의 반만큼 위로 이동
				result[r - N/2][c] = map[r][c];
			}
		}
		map = result;
	}
	
	private static void six() {
		int[][] result = new int[N][M];
		
		// 1번 => 4번 
		for(int r=0; r < N/2; r++) {
			for(int c=0; c < M/2; c++) {
				//행의 반만큼 아래로 이동
				result[r + N/2][c] = map[r][c];
			}
		}
		// 4번 => 3번
		for(int r=N/2; r < N; r++) {
			for(int c=0; c < M/2; c++) {
				//열의 반만큼 오른쪽 이동
				result[r][c + M/2] = map[r][c];
			}
		}
		// 3번 => 2번
		for(int r=N/2; r < N; r++) {
			for(int c=M/2 ; c < M ; c++) {
				//행의 반만큼 위로 이동
				result[r - N/2][c] = map[r][c];
			}
		}
		// 2번 => 1번 
		for(int r=0; r < N/2; r++) {
			for(int c=M/2; c < M; c++) {
				//열의 반만큼 왼쪽 이동
				result[r][c - M/2] = map[r][c];
			}
		}
		map = result;
	}
	//배열 출력
	private static void print() {
		for(int[] arr: map) {
			System.out.println(Arrays.toString(arr));
		}
		System.out.println();
	}
}