package algo230130;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2805_농작물수확하기_연민호_gap {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

//		System.setIn(new FileInputStream("input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for( int tc=1; tc<=T ; tc++) {
			
			int N = Integer.parseInt(br.readLine());
			
			//농장
			int[][] farm = new int[N][N];
			//농작물의 가치 입력
			for(int i=0;i<N;i++) {
				String s = br.readLine();
				for(int j=0;j<N;j++) {
					farm[i][j] = s.charAt(j)-'0';	//char 숫자 => int 
				}
			}
			
			int center = N/2;	//중앙 위치값
			int gap = 0; 		//중앙으로부터 수확범위
			
			int value = 0;	//총 가치
			//행의 크기 만큼 반복
			for(int i=0; i<N ;i++) {
				
				//수확 범위만큼 농작물 수확
				for(int j=center-gap; j<=center+gap; j++) {
					value += farm[i][j];	//가치 합하기
				}
				
				//가운데 행 이전엔 수확물의 범위 -1
				if( i < center ) gap++;
				//가운데 행부터 수확물의 범위 +1
				else gap--;
			}
			System.out.println("#"+tc+" "+value);
		}
	}
}