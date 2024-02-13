package algo240213;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:23,392kb, 시간:239ms
 */
public class Solution_4012_요리사_연민호_기본재귀조합 {

	static int N;	// 식재료의 개수

	static int[][] synergy;	//식재료 간의 시너지 정보

	static boolean[] isFoodA;	//A음식의 식재료 여부 - true:A식재료, false:B식재료

	static int minDiff;	//맛 차이의 최솟값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());

			//시너지 정보 입력
			synergy = new int[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			isFoodA = new boolean[N];

			minDiff = Integer.MAX_VALUE;	//최소 맛차이 충분히 큰 값으로 초기화

			combination(0, 0);

			sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
		}
		System.out.println(sb);
	}
	/**
	 * 
	 * @param cnt A식재료로 선택한 수
	 * @param start 현재 A재료 선택 시 고려할 요소의 시작인덱스(start ~ 
	 */
	private static void combination(int cnt, int start) {

		//step 01. A음식의 식재료 선택 완료
		if(cnt == N/2) {

			//step 02. A, B음식의 맛 차이 구하기
			int diff = getDiff();

			//step 03. 맛 차이의 최솟값 비교
			minDiff = Math.min(diff, minDiff);

			return;
		}

		for(int i=start; i<N; i++) {

			isFoodA[i] = true;	//i행의 식재료를 A음식의 식재료로 설정
			combination( cnt+1, i+1 );
			isFoodA[i] = false;	//다음 선택을 위해 선택정보 되돌리기

		}

	}
	//A,B 음식의 각 맛을 구하고 맛 차이를 반환
	private static int getDiff() {
		int scoreA = 0;	//A음식의 맛
		int scoreB = 0; //B음식의 맛

		//i는 행의 식재료, j는 열의 식재료 의미
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				//둘 다 A음식의 식재료라면?
				if(isFoodA[i] && isFoodA[j]) scoreA += synergy[i][j];	//해당 시너지를 A음식의 맛에 cnt

				//둘 다 B음식의 식재료라면?
				else if(!isFoodA[i] && !isFoodA[j]) scoreB += synergy[i][j];	//해당 시너지를 B음식의 맛에 cnt
			}
		}

		//맛 차이 반환
		return Math.abs(scoreA - scoreB);
	}
}