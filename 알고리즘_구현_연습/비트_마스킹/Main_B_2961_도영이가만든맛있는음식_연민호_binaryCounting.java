package algo240221;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:11512KB, 시간:80ms
 */
public class Main_B_2961_도영이가만든맛있는음식_연민호_binaryCounting {

	static int N;	//재료의 개수
	static Ingredient[] ingredients;	//재료 정보
	
	static int minDiff;	//신맛과 쓴맛 차이의 최솟값
	
	static class Ingredient{
		int S;	//신맛
		int B;	//쓴맛
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
		
		minDiff = Integer.MAX_VALUE;	//최솟값을 담을 것이므로 충분히 큰 값으로 초기화
		
		//재료 정보 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int S= Integer.parseInt(st.nextToken());
			int B= Integer.parseInt(st.nextToken());
			ingredients[i] = new Ingredient(S, B);
		}
		
		subSet();
		
		System.out.println(minDiff);
	}

	private static void subSet() {
		/**
		 * flag - arr 요소의 선택/비선택 정보 담고 있음(flag 자체가 곧 하나의 경우의 수를 의미)
		 * 하나도 선택하지 않는 경우(...0000) 제외해야 하므로 1부터
		 */
		for(int flag=1; flag < 1<<N; flag++) {
			
			//step01. flag 비트를 이용해 선택된 재료의 신맛 쓴맛 구하기
			int s=1;	//신 맛
			int b=0; //쓴 맛
			/**
			 * j변수를 활용해 flag 비트를 0번자리부터 탐색(1인 경우 해당 식재료를 선택한 것이므로 맛 cnt)
			 */
			for(int j=0; j<N; j++) {
				if((flag & 1<<j)!=0) {
					s *= ingredients[j].S;
					b += ingredients[j].B;
				}
			}
			//step 02. 신맛 쓴맛의 차이 구하기
			int diff = Math.abs(s-b);
			
			//step 03. 차이값의 최솟값 갱신
			minDiff = Math.min(minDiff, diff);
		}
		
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