package algo230130;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 
 *  1. 마지막 행에서 2를 탐색하여 열(col) 정보 저장
	2. 위로 이동 -> 99행 ~ 0행까지 이동
	  2-1) 왼쪽이 1인 경우 왼쪽으로 이동
	     => 다음 칸이 경계를 넘어가거나 0일 때까지 이동
	  2-2) 오른쪽 1인 경우 오른쪽으로 이동
	     => 다음 칸이 경계를 넘어가거나 0일 때까지 이동
	3. 이 때의 열(col)을 출력
 * 
 * @author SSAFY
 *
 */

public class Solution_1210_Ladder1_연민호1 {
	static int[][] ladder;
	final static int N = 100; // 사다리 크기
	static int col; // 열 위치

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("ladder_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		
		for (int tc = 1; tc <= 10; tc++) {
			col=0;
			tc = Integer.parseInt(br.readLine());

			ladder = new int[N][N];

			// ladder 값 입력
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// step 01. 마지막 행에서 2를 탐색하여 열(col) 정보 저장
			for(int c=0; c<N; c++) {
				if( ladder[N-1][c]==2 ) col = c;
			}
			
			
			//step 02. 위로 이동 -> 99행 ~ 0행까지 이동
			for(int row = N-1; row>=0; row--) {
//				  2-1) 왼쪽이 1인 경우 왼쪽으로 이동
				if(col!=0 && ladder[row][col-1]==1) moveLeft(row);

//		 		 2-2) 오른쪽 1인 경우 오른쪽으로 이동
				else if(col!=N-1 && ladder[row][col+1]==1) moveRight(row);
//		     => 다음 칸이 경계를 넘어가거나 0일 때까지 이동
			}
			
			//step 03. 이 때의 열(col)을 출력
			System.out.println("#"+tc+" "+col);
		}
	}

    private static void moveRight(int row) {
    	while( col+1<N && ladder[row][col+1]==1) col++;
	}

	//  다음 칸이 경계를 넘어가거나 0일 때까지 이동
	//다음칸이 경계를 넘어가지 않고 1이라면 이동
	private static void moveLeft(int row) {
		while( col-1>=0 && ladder[row][col-1]==1 ) col--;
	}
}