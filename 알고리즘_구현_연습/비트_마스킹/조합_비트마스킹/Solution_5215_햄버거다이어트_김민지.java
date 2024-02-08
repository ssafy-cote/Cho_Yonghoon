package algo0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 메모리 25244kb
// 시간 1297ms
public class Solution_5215_햄버거다이어트_김민지 {
	static int N, L; // 재료의 수, 제한 칼로리
	static int[] scores, cals; // 민기의 점수와 칼로리
	static int result; // 정답
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			result = 0;
			scores = new int[N]; // 점수와
			cals = new int[N]; // 칼로리 담을 배열 초기화
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<(1<<N); i++) { // 바이너리카운팅으로 부분집합 생성
				int tempCal = 0;
				int tempScore = 0;
				for(int j=0; j<N; j++) {
					if((i & (1<<j))!=0) { 
						tempCal+=cals[j];
						tempScore+=scores[j];
					}
				}
				if(tempCal<=L) {
					result = Math.max(result, tempScore);
				}
			}
			sb.append("#"+testCase+" "+result+"\n");
		}
		System.out.println(sb);
	}
}
