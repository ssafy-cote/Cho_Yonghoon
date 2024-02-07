package algo240202;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 *  메모리:20,964KB, 시간: 747ms
 */
public class Solution_5215_햄버거다이어트_연민호_재귀_매개변수X {

	static int N, L; //재료의 수, 칼로리 제한
	static int maxPref; //최고 선호도 저장
	
	static int[][] ingredient; //식재료 정보 저장 0:선호도 , 1:칼로리
	static boolean[] isSelected;	//식재료 선택 여부 저장 - true:선택, false:비선택
	
	public static void main(String[] args) throws IOException {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=T;tc++) {
			
			maxPref = 0; //최고 선호도 초기화
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());	//재료수
			L = Integer.parseInt(st.nextToken());	//칼로리 제한
			
			ingredient = new int[N][2];
			
			//식재료 정보 입력
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken());
				ingredient[i][1] = Integer.parseInt(st.nextToken());
			}
			
			isSelected = new boolean[N];
			
			subSet(0);
			
			
			System.out.println("#"+tc+" "+maxPref);
		}

	}
	/**
	 * 식재료 선택 메소드
	 * @param cnt 선택/비선택 결정할 요소의 인덱스
	 */
	private static void subSet(int cnt) {
		
		//기저 조건
		//step 01. N-1인덱스까지의 모든 요소 처리 완료 후, 햄버거 재료의 조합이 완성됨
		if(cnt==N) {
			
			//step 02. 햄버거의 선호도와 총 칼로리 구하기
			int score = 0; 	//햄버거 선호도
			int kcal = 0;	//총 칼로리
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					score += ingredient[i][0];
					kcal += ingredient[i][1];
				}
			}
			
			
			//step 03. 총칼로리가 L이하이고 햄버거 선호도가 최댓값이라면 갱신
			if(kcal <= L) maxPref = Math.max(score, maxPref);
			
			return;
		}
		
		
		//cnt의 인덱스의 요소를 선택
		isSelected[cnt] = true;
		subSet(cnt+1);	//다음 인덱스의 재료 선택할 재귀함수 호출
		
		//cnt의 인덱스의 요소를 비선택
		isSelected[cnt] = false;
		subSet(cnt+1);
		
	}

	
}