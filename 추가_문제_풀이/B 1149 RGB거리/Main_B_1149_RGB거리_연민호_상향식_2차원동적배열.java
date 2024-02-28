package algo240228;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:12120KB, 시간88ms
 * 
 * red(n) : n개 집을 칠할 때 n번째 집을 빨강색으로 칠하는 최소 비용
 * n번째 집을 빨강색으로 칠하기 위해선
 * n-1번째 집을 초록색 or 파랑색으로 칠하는 경우 밖에 없음
 * 
 * green(n) : n개 집을 칠할 때 n번째 집을 초록색으로 칠하는 최소 비용
 * blue(n) : n개 집을 칠할 때 n번째 집을 파랑색으로 칠하는 최소 비용
 * 
 * 위와 같다고 했을 때,
 * 
 * red(n) :
 * n-1번째 집을 초록색으로 칠하는 최소비용과 
 * n-1번째 집을 파랑색으로 칠하는 최소비용 중 작은것 비용에
 * n번째 집을 빨강색으로 칠하는 비용을 합산 한 것으로 볼 수 있음
 * 
 * 즉, 
 * red(n) = min( green(n-1) + blue(n-1) ) + n번째 집을 빨강색으로 칠하는 비용
 * 위와 같은 점화식을 도출할 수 있음
 * 
 * green(n), blue(n) 또한 같은 방식으로 생각할 수 있음
 * 
 * red(n) = min( green(n-1) + blue(n-1) ) + n번째 집을 빨강색으로 칠하는 비용
 * green(n) = min( red(n-1) + blue(n-1) ) + n번째 집을 초록색으로 칠하는 비용
 * blue(n) = min( red(n-1) + green(n-1) ) + n번째 집을 파랑색으로 칠하는 비용
 * 
 * f(n) : n개의 집을 칠하는 최소비용
 * f(n) = min( red(n), green(n), blue(n) )
 * 
 * @author SSAFY
 *
 */
public class Main_B_1149_RGB거리_연민호_상향식_2차원동적배열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		//각 번호의 집을 칠할 때 색에 따른 비용 입력
		int[][] cost = new int[n+1][3];
		for(int i=1; i<=n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//동적 테이블
		//0 : 빨강, 1: 초록, 2:파랑
		//d[3][0] : 3개 집을 칠할 때 3번째 집을 빨강색으로 칠하는 최소 비용
		//d[3][1] : 3개 집을 칠할 때 3번째 집을 초록색으로 칠하는 최소 비용
		//d[3][2] : 3개 집을 칠할 때 3번째 집을 파랑색으로 칠하는 최소 비용
		int[][] d = new int[n+1][3];
				
		for(int i=1; i<=n; i++) {
			//i개 집을 칠할 때 i번째 집을 빨강으로 칠할 때 최소비용
			d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + cost[i][0];
			
			//i개 집을 칠할 때 i번째 집을 초록으로 칠할 때 최소비용
			d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + cost[i][1];
			
			//i개 집을 칠할 때 i번째 집을 파랑으로 칠할 때 최소비용
			d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + cost[i][2];
		}
		
		//f(n) =  min( d[n][0], d[n][1], d[n][2]   )
		int min = Math.min(  Math.min(d[n][0], d[n][1]),  d[n][2]);
		
		System.out.println(min);
	}
}