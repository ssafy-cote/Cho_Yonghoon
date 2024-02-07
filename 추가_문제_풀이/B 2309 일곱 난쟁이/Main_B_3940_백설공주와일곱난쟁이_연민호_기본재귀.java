package algo230131;
import java.io.IOException;
import java.util.Scanner;

public class Main_B_3940_백설공주와일곱난쟁이_연민호_기본재귀 {

	static int[] hats;	//전체 모자 정보
	
	static int[] selectedHats;	//선택된 모자
	
	//N : 고려할 요소의 수 , R : 선택할 요소의 수
	static int N = 9, R = 7;
	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		
		//모자 정보 입력
		hats = new int[N];
		for(int i=0;i<N;i++) {
			hats[i] = s.nextInt();
		}
		
		selectedHats = new int[R];	//선택된 모자 정보가 채워질 배열
		
		combination(0, 0);
		
		s.close();
	}
	
	/**
	 * 
	 * @param cnt 현재 선택된 요소의 수
	 * @param start 현재 선택시 고려할 요소의 시작 인덱스 (start ~ 끝 요소가 선택 범위)
	 */
	private static void combination(int cnt, int start){
		//step 01. 7 난쟁이 선택 완료
		if(cnt == R) {
			//step 02. 모자 총합이 100이라면 선택된 7난쟁이 모자 출력 후 종료
			if( getSum()==100) {
				StringBuilder sb = new StringBuilder();
				for(int i: selectedHats) sb.append(i).append('\n');
				System.out.println(sb);
				System.exit(0);
			}
			return;
		}
		
		for(int i=start; i<N; i++) {
			selectedHats[cnt] = hats[i];
			combination(cnt+1, i+1);
		}
	}
	//선택된 모자 총합 계산
	private static int getSum() {
		int sum = 0;
		for (int i : selectedHats) sum += i;
		return sum;
	}
}