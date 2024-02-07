package algo240206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:16,884kb, 시간:118ms
 */
public class Solution_1874_스도쿠검증_연민호_비트마스킹 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		int[][] numbers = new int[9][9];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		A:for(int tc=1; tc<=T; tc++) {
			//스도쿠 정보 입력
			for(int i=0; i<9; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<9; j++) {
					numbers[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			int[] rowCheck = new int[9];	//각 행의 유효성 검증을 위한 배열
			//rowCheck[1] : 1022인 경우
			//0000 0000 0000 0000 0000 0011 1111 1110
			//                           98 7654 321 가 해당 행에 존재한다는 의미이으로 1행은 유효하다 판단	
			int[] colCheck = new int[9];	//각 열의 유효성 검증을 위한 배열
			int[] squareCheck = new int[9];
			//9x9 배열을 3x3 단위로 아래와 같이 나누었다고 가정
			// 0   1   2
			// 3   4   5
			// 6   7   8
			
			//모두 비트 마스킹을  통해 체크했을 때, 1022라는 int값을 얻을 수 있음
			//모든 rowCheck, colCheck, squareCheck의 값이 1022인 경우 유효한 스도쿠
			//System.out.println(Integer.parseInt("1111111110", 2));	//이진수를 십진수로
			
			//step 01. 모든 스도쿠 숫자 정보를 rowCheck, colCheck, squareCheck에 체크
			for(int r=0; r<9; r++) {
				for(int c=0; c<9; c++) {
					int number = numbers[r][c];
					
					int checking = 1<<number;
					//만약 숫자가 3인 경우;
					//1을 왼쪽으로 세번 옮긴 아래와 같은 이진수 값을 만듦
					//checking 1 0 0 0
					//해당 checking을 통해 rowCheck,colCheck,squareCheck에 체킹
					
					rowCheck[r] |= checking;
					colCheck[c] |= checking;
					
					//스퀘어의 인덱스 얻어오기
					//(1,1)의 경우 0 인덱스에 속함
					//(4,1)의 경우 3 인덱스에 속함
					int idx = (r/3)*3 + c/3;
//					System.out.printf("r:%d, c:%d, idx:%d%n", r, c, idx);
					squareCheck[idx] |= checking;
					
					//체크되는 과정 확인
//					System.out.println(Integer.toBinaryString(rowCheck[r]));
//					System.out.println(Integer.toBinaryString(colCheck[c]));
//					System.out.println(Integer.toBinaryString(squareCheck[idx]));
//					System.out.println();
				}
			}
			
			//step 02. 모든 배열의 숫자 정보가 유효한지 체크
			sb.append("#").append(tc).append(" ");
			for(int i=0; i<9; i++) {
				//주석 풀어서 실행해보기
//				System.out.println(Integer.toBinaryString(rowCheck[i]));
//				System.out.println(Integer.toBinaryString(colCheck[i]));
//				System.out.println(Integer.toBinaryString(squareCheck[i]));
				if(rowCheck[i]!=1022 || colCheck[i]!=1022 || squareCheck[i]!=1022) {
					
					sb.append(0).append("\n");
					continue A; //다음 테케로
				}
			}
			//무사히 반복을 마쳤다면 모두 유효하므로 1 출력
			sb.append(1).append("\n");
		}
		System.out.println(sb);
	}
}
