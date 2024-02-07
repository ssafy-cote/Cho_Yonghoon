package algo230131;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * 모자의 총합 구하기
 * 
 * 7명을 뽑는 것이 아닌 2명을 뽑아 
 * 모자의 총합 - 2명의 모자합 = 100 이 되는 경우를 정답으로 생각하여 나머지 7명의 정보를 출력
 * 
 */
public class Main_B_3940_백설공주와일곱난쟁이_연민호_최적화_반복문조합 {

	static int[] hats;	//전체 모자 정보
	
	
	//N : 고려할 요소의 수 , R : 선택할 요소의 수
	static int N = 9, R = 7;
	
	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		
		int total = 0;
		
		//모자 정보 입력
		hats = new int[N];
		for(int i=0;i<N;i++) {
			hats[i] = s.nextInt();
			total+=hats[i];
		}
		StringBuilder sb = new StringBuilder();
		
		A:for(int i=0; i<N; i++) {	//i: 첫번째로 뽑은 난쟁이
			for(int j=i+1; j<N; j++) {	//j: 두번째로 뽑은 난쟁이
				if(total-hats[i]-hats[j]!=100) continue;
				
				//모자의 총합 - 2명의 모자합 = 100 이 되는 경우, 나머지 7난쟁이의 정보 출력
				for(int k=0; k<N; k++) {
					if(i==k || j==k) continue;	//두 명의 난쟁이는 제외
					
					sb.append(hats[k]).append('\n');
				}
				//더 이상 반복을 돌 필요 없으므로 종료
				break A;
			}
		}
		
		System.out.println(sb);
		
		
		s.close();
	}
	
}