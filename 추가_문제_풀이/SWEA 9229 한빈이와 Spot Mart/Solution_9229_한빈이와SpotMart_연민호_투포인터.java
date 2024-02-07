package algo240221;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * 문제 해결 프로세스
 * 1. 과자 무게 오름차순 정렬
 * 2. 두 개의 포인터 사용
 * 	p1 = 0
 * 	p2 = N-1
 *  - 가장 가벼운 과자(0), 가장 무거운 과자(N-1) 의 무게 총합을 비교
 * 
 * a. 무게 총합이 M보다 큰 경우
 * 	- 더 가벼운 무게로 시도해야 함
 * 		=> N-1과자에서 N-2과자를 선택해야 함. 즉, p2--;
 * b. 무게 총합이 M보다 작은 경우
 * 	- 현재 값이 최대라면 갱신
 * 	- 현재무게가 M이하인 최댓값이라는 보장이 없으므로 더 무게운 무게도 시도해야 함
 * 		=> 0과자에서 1과자를 선택. 즉, p1++;
 * c. 무게 총합이 M인 경우
 * 	- 선택할 수 있는 최대 무게이므로 더이상 선택할 필요 없음
 * 		=> 출력 후 종료
 * 
 * 
 * @author minho
 *
 */
public class Solution_9229_한빈이와SpotMart_연민호_투포인터 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	//과자 개수
			int M = Integer.parseInt(st.nextToken());	//무게 제한

			int[] snacks = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(snacks);
			
			int p1=0;
			int p2=N-1;
			int max = -1;
			while(p1<p2) {
				int sum = snacks[p1] + snacks[p2];
				if(sum > M) p2--;
				else if(sum < M) {
					max = Math.max(max, sum);
					p1++;
				}
				else {
					max = sum;
					break;
				}
				
			}
			sb.append('#').append(tc).append(' ').append(max).append('\n');
			
		}
		System.out.println(sb);
		
		
	}

}