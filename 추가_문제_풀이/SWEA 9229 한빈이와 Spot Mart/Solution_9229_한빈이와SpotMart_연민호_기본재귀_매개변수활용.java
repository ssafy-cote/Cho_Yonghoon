package algo240221;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_9229_한빈이와SpotMart_연민호_기본재귀_매개변수활용 {

	static int N, M; 	//과자 개수, 무게 제한
	static int[] snacks;	//과자 정보
	
	static int max;	//최대가능한 봉지무게
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			max = -1;	//과자봉지 무게 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());	//과자 봉지 개수
			M = Integer.parseInt(st.nextToken());	//무게 제한
			
			st = new StringTokenizer(br.readLine());
			
			snacks = new int[N];
			
			//과자 정보 입력
			for(int i=0;i<N;i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}
			combination(0, 0, 0);
			
			//max값이 0이라는 것은 제한 무게 이하로 과자 2개를 선택할 수 없었다는 것이므로 -1 출력
			sb.append('#').append(tc).append(' ').append(max).append('\n');
		}
		System.out.println(sb);
	}

	private static void combination(int cnt, int start, int weight) {
		// 두 개의 과자 선택이 완료되었을 때
		if(cnt == 2) {
			//과자 무게의 총합이 제한무게 이하일 때
			if(weight <= M) {
				max = Math.max(max, weight);	//최대값 체크
			}
			return;
		}
		for(int i=start; i<N; i++) {
			combination(cnt+1, i+1, weight+snacks[i]);
		}
	}
}