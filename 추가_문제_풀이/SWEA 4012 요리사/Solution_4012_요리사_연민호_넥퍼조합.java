package algo240213;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:26,460kb, 시간:238ms
 */
public class Solution_4012_요리사_연민호_넥퍼조합 {

	static int N;	// 식재료의 개수

	static int[][] synergy;	//식재료 간의 시너지 정보

	static int[] isFoodA;	//A음식의 식재료 여부 - 1:A식재료, 0:B식재료

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
			minDiff = Integer.MAX_VALUE;	//최소 맛차이 충분히 큰 값으로 초기화

			isFoodA = new int[N];
			//00001111 만들기
			for(int i=0; i<N/2;i++) {
				isFoodA[N-i-1] = 1;
			}

			do {

				//step 02. A, B음식의 맛 차이 구하기
				int diff = getDiff();

				//step 03. 맛 차이의 최솟값 비교
				minDiff = Math.min(diff, minDiff);

			} while (np(isFoodA));

			sb.append("#").append(tc).append(" ").append(minDiff).append("\n");
		}
		System.out.println(sb);
	}

	private static boolean np(int[] arr) {

		int N = arr.length;	//요소의 개수

		//1. 꼭대기 인덱스 찾기
		int i=N-1;	//꼭대기 인덱스 담을 변수
		while( i>0 && arr[i-1]>=arr[i] ) i--;

		//꼭대기 인덱스가 0이라면? 모든 수가 내려가는 구간이므로 이미 가장 큰 수
		if(i==0) return false;

		//2. (i-1)요소와 교환할 요소의 인덱스 찾기
		int j=N-1;
		while(arr[i-1] >= arr[j] ) j--;


		//3. (i-1) <=> j 요소 스왑
		swap(arr, i-1, j);


		//4. (i~ N-1) 요소들을 오름차순으로 정렬
		// i는 앞의 요소의 포인터, k는 뒤의 요소의 포인터
		int k= N-1;
		while(i<k) {
			swap(arr, i++, k--);
		}

		//큰 순열을 만들어내는데 성공했으므로 true 반환
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	//A,B 음식의 각 맛을 구하고 맛 차이를 반환
	private static int getDiff() {
		int scoreA = 0;	//A음식의 맛
		int scoreB = 0; //B음식의 맛

		//i는 행의 식재료, j는 열의 식재료 의미
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				//둘 다 A음식의 식재료라면?
				if(isFoodA[i]==1 && isFoodA[j]==1) scoreA += synergy[i][j];	//해당 시너지를 A음식의 맛에 cnt

				//둘 다 B음식의 식재료라면?
				else if(isFoodA[i]==0 && isFoodA[j]==0) scoreB += synergy[i][j];	//해당 시너지를 B음식의 맛에 cnt
			}
		}

		//맛 차이 반환
		return Math.abs(scoreA - scoreB);
	}
}