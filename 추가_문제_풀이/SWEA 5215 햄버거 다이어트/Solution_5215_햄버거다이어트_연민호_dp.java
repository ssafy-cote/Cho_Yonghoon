package algo240202;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 *  메모리:21,428KB, 시간: 131ms
 */
public class Solution_5215_햄버거다이어트_연민호_dp {

	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("햄버거다이어트_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		StringBuilder sb = new StringBuilder();
		for(int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			//식재료 정보 입력
			//ingredients[2][1] : 2번 식재료의 칼로리
			int[][] ingredients = new int[N][2];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());
				ingredients[i][0] = score;
				ingredients[i][1] = kcal;
			}
			
			//칼로리의 제한이 0~L 으로 설정했을 때 각각의 최대 점수 저장 
			int[] d = new int[L+1];
			
			//i : 식재료 번호
			A : for(int i=0; i<N; i++) {
				int score = ingredients[i][0];
				int kcal = ingredients[i][1];
				
				//j : 임시 칼로리 제한
				for(int j=L; j>=1; j--) {
					//현재 식재료의 칼로리가 임시 칼로리 제한을 넘으면 다음 식재료 
					if(j < kcal) continue A;
					
					//j 칼로리를 채울 때,
					//i식재료를 포함했을 때의 최대 점수와
					//i식재료를 포함하지 않았을 때의 최대 점수를 비교
					//i식재료를 포함했을 때의 최대점수가 크다면 갱신
					if(d[j-kcal] + score > d[j]) d[j] = d[j-kcal]+score;
				}
			}
			sb.append('#').append(tc).append(' ').append(d[L]).append('\n');
		}
		System.out.println(sb);
		
	}

}