package algo240221;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:11536KB, 시간:76ms
 */
public class Main_B_2961_도영이가만든맛있는음식_연민호_기본재귀_클래스사용 {

	static int N;	//재료의 개수
	static Ingredient[] ingredients;	//재료 정보
	
	static boolean[] isSelected;	//재료 선택/비선택 여부 - true:선택
	
	static int minDiff;	//신맛 쓴맛 차이의 최솟값
	
	static class Ingredient{
		int S;	//신 맛
		int B;	//쓴 맛
		
		public Ingredient(int S, int B) {
			this.S = S;
			this.B = B;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;
		
		N = Integer.parseInt(br.readLine());
		ingredients = new Ingredient[N];
		
		isSelected = new boolean[N];
		
		minDiff = Integer.MAX_VALUE;	//최솟값을 담을 것이므로 충분히 큰 값으로 초기화
		
		//재료 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S= Integer.parseInt(st.nextToken());
			int B= Integer.parseInt(st.nextToken());
			ingredients[i] = new Ingredient(S, B);
		}
		
		subSet(0 , 0);
		
		System.out.println(minDiff);
	}

	/**
	 * 
	 * @param cnt 비선택/선택을 완료한 개수 or 현재 선택/비선택을 고려할 요소의 인덱스
	 * @param selNum 선택된 재료 수 - 아무것도 선택하지 않은 경우를 거르기 위해
	 */
	private static void subSet(int cnt, int selNum) {
		//step 01. 모든 재료의 선택/비선택 완료
		if(cnt == N) {
			//선택한 재료가 1개 이상의 경우
			if(selNum>=1) {
				//step 02. 선택된 재료의 신맛 쓴맛 차이 계산
				int diff = calcDiff();
				
				//step 03. 차이값의 최솟값 갱신
				minDiff = Math.min(minDiff, diff);
			}
			return;
		}
		
		//cnt번째 재료 선택하는 경우
		isSelected[cnt] = true;
		subSet(cnt+1, selNum+1);
		
		//cnt번째 재료 선택하지 않는 경우
		isSelected[cnt] = false;
		subSet(cnt+1, selNum);
		
	}
	
	//음식의 신맛 쓴맛 차이 계산
	private static int calcDiff() {
		int s=1;	//음식의 신 맛
		int b=0;	//음식의 쓴 맛
		for(int i=0;i<N;i++) {
			if(isSelected[i]) {
				s*=ingredients[i].S;
				b+=ingredients[i].B;
			}
		}
		return Math.abs(s-b);
	}

}
/* 
입력1
1
3 10

출력1
7

입력2
2
3 8
5 8

출력2
1

입력3
4
1 7
2 6
3 8
4 9

출력3
1
*/