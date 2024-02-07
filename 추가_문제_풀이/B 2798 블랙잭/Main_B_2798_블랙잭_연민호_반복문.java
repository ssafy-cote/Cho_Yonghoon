package algo240206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:11600kb, 시간:88ms
 */
public class Main_B_2798_블랙잭_연민호_반복문 {
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
		
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				for(int k=j+1; k<N; k++) {
					int sum = cards[i] + cards[j] + cards[k];
					if(sum > M) continue;
					answer = Math.max(answer, sum);
				}
			}
		}
		
		System.out.println(answer);
	}
}
