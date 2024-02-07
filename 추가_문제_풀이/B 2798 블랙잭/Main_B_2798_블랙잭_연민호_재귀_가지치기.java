package algo240206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:11576kb, 시간:84ms
 */
public class Main_B_2798_블랙잭_연민호_재귀_가지치기 {

	static int N,M;	//카드의 개수와 카드의 합
	static int[] cards;	//카드 정보
	
	static int answer;	//M과 가장 가까운 카드의 합
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		cards = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) cards[i] = Integer.parseInt(st.nextToken());
		
		combination(0, 0, 0);
		
		System.out.println(answer);
	}

	private static void combination(int idx, int start, int curSum) {
		//가지치기
		if(curSum > M) return;	//카드를 고르는 중 현재까지의 합이 M을 넘어가는 경우, 시도하지 않음
		
		//step 01. 카드 3장을 뽑음
		if(idx==3) {
			//step 02. curSum이 M을 넘어가는 경우는 위에서 걸렀으므로 answer보다 curSum이 큰 지만 체크하면 됨
			answer = Math.max(answer, curSum);
			
			return;
		}
		
		for(int i=start; i<N; i++) {
			combination(idx+1, i+1, curSum+cards[i]);
		}
	}
}
