package algo240328;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:27,276KB , 시간:122ms
 * 
 * [문제 해석]
 * - N X N
 * 
 * 각 벌통엔 꿀의 양이 주어짐
 * 
 * 벌꿀 채취
 * 1. 두명의 일꾼 존재
 *  - 각 일꾼은 가로로 M개의 벌통을 선택 후, 선택한 벌통에서 꿀을 채취
 *  - 벌통이 겹치면 안됨.
 *  - 벌통에 있는 모든 꿀을 한 번에 채취해야 함.
 *  
 *   두 일꾼이 각각 채취할 수 있는 꿀의 최대 양은 C임.
 *   - 선택한 M개의 벌통 중 합이 C를 넘지 않는 최대 수익을 구함
 * 
 *  수익
 *  => 각 꿀양의 제곱의 합
 * 
 * => 두 일꾼이 꿀을 채취하여 얻을 수 있는 최대 수익을 출력
 * 
 * (N-(M-1))*NC2 * 2^M
 * = (10 - 4) * 10C2 * 32
 * = 60C2 * 32
 * = 60*59/2 * 32
 * => 완탐 문제 없음
 * 
 * 
 * [문제 해결 프로세스]
 * 1. M개의 벌통을 2번 선택
 * => 조합 2차원 배열을 1차원으로 바꿔서 풀이
 * => 다음 벌통을 뽑을 땐 현재 뽑은 것(i)의 M개 뒤부터 뽑도록 설계 (i+M부터)
 * => 현재 선택할 열이 N-M 보다 큰 경우는 건너 뛰기(M개를 선택할 수 없기 때문)
 * 
 * 2. 선택된 각 M개의 벌통 중 꿀의 합이 C를 넘지 않는 모든 수익 구하기 
 * => 재귀 부분 집합을 통해 구하기
 * => 매개변수에 꿀의 합을 넣고 중간에 C를 넘어간다면 가지치기
 * => 수익도 매개변수에 함께 넣기
 * 
 * 3. 구한 수익이 최댓값이라면 갱신
 *
 *
 * @author minho
 *
 */
public class Solution_2115_벌꿀채취_연민호_반복문조합 {
	static int N;   //벌통의 크기
	static int M;   //일꾼이 선택할 벌통 수
	static int C;   //한 일꾼이 채취할 수 있는 꿀의 최대 양

	static int[][] honey;   //꿀 정보

	static int maxRevenue;  //최대 수익

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			honey = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			maxRevenue = 0;

			//step 01. 두 일꾼의 벌통 선택 완료
			for(int i=0; i<N*N; i++) {			//i : 첫번째 선택한 벌통 그룹의 시작인덱스
				int oneR = i/N;
				int oneC = i%N;
				if( oneC > N-M ) continue;

				//step 02. 첫번째 선택한 벌통 그룹의 최대 이익 구하기
				int firstRevenue = getMaxRevenue(oneR, oneC, 0, 0, 0);

				//다음 선택은 현재 선택한 벌통의 M개 뒤 벌통부터 할 수 있음
				for(int j=i+M; j<N*N; j++) {	//j : 두번째 선택한 벌통 그룹의 시작인덱스
					int twoR = j/N;
					int twoC = j%N;
					if(twoC > N-M) continue; 

					//step 02. 두번째 선택한 벌통 그룹의 최대 이익 구하기
					int secondRevenue = getMaxRevenue(twoR, twoC, 0, 0, 0);

					//step 03. 현재 이익이 최댓값이라면 갱신
					maxRevenue = Math.max(maxRevenue, firstRevenue+secondRevenue);
				}
			}
			sb.append('#').append(tc).append(' ').append(maxRevenue).append('\n');
		}
		System.out.println(sb);
	}


    
	/**
	 * (sr, sc+idx)좌표의 벌통에 대한 채취/비채취 결정 후, 다음 좌표(sr, sc+idx+1)에 위치한 벌통에 대한 채취/비채취 결정은 재귀로 넘김
	 * @param sr
	 * @param sc
	 * @param idx		현재까지 채취/비채취를 고려한 벌통의 개수
	 * @param sum		현재까지 채취한 벌통의 꿀의 합
	 * @param revenue	현재까지 채취한 벌통의 꿀의 이익
	 * @return (sr,sc)좌표의 벌통을 시작점으로 M개의 벌통을 선택했을 때, 해당 벌통 중 채취한 꿀의 양이 C를 넘지 않는 최대 이익
	 */
	private static int getMaxRevenue(int sr, int sc, int idx, int sum, int revenue) {
		if(sum > C) return 0; 		//채취한 벌통들의 꿀의 합이 C를 넘어간다면 돌아가기(가지치기)
		if(idx==M) return revenue;	//M개의 벌통에 대한 채취/비채취를 모두 결정(기저조건)

		int curHoney = honey[sr][sc+idx];
		int A = getMaxRevenue(sr, sc, idx+1, sum+curHoney, revenue+curHoney*curHoney);	//(sr,sc+idx) 좌표의 벌통 채취

		int B = getMaxRevenue(sr, sc, idx+1, sum, revenue);								//(sr,sc+idx) 좌표의 벌통 비채취

		//두 경우의 이익 중 큰 값을 반환
		return Math.max(A, B);
	}
}