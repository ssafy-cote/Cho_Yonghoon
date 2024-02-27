package algo240227;
import java.util.Scanner;
/**
 * f(n) : n층을 칠하기 위한 경우의 수
 * 
 * b(n) : n층을 칠할 때 1층을 파랑색으로 칠하는 경우의 수
 * y(n) : n층을 칠할 때 1층을 노랑색으로 칠하는 경우의 수
 * 
 * f(n) = y(n) + b(n)
 * y(n) = f(n-1)
 * b(n) = y(n-1)
 * 
 * 동적 배열
 * - y(n)과 b(n)을 저장
 * 
 * @author SSAFY
 *
 */
public class 아파트색칠하기_연민호_2차원동적배열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	//구하고자 하는 층수
		
		//동적 배열
		//d[1][3] : 3층을 칠할 때, 1층을 파랑색으로 칠하는 경우의 수
		//d[0][3] : 3층을 칠할 때, 1층을 노랑색으로 칠하는 경우의 수
		int[][] d = new int[2][N+1];
		d[0][1] = 1;
		d[1][1] = 1;
		d[0][2] = 2;
		d[1][2] = 1;
		
		//동적 배열 채우기
		for(int i=3; i<=N; i++) {
			//y(n) = f(n-1) == d[0][n] 
			d[0][i] = d[0][i-1]+d[1][i-1];	
			
			//b(n) = y(n-1) == d[1][n]
			d[1][i] = d[0][i-1];
		}
		System.out.println(d[0][N]+d[1][N]);
	}
}