package algo240227;
import java.util.Scanner;
/**
 * f(n) : n층을 칠하기 위한 경우의 수
 * 
 * 점화식
 * f(n) = f(n-1) + f(n-2)
 * 
 * 동적 배열
 * - n층을 칠하기위한 경우의 수 저장
 * @author SSAFY
 *
 */
public class 아파트색칠하기_연민호_1차원동적배열 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();	//구하고자 하는 층수
		
		//동적 배열
		//d[3] : 3층을 칠할 수 있는 경우의 수
		int[] d = new int[N+1];
		d[1] = 2;
		d[2] = 3;
		
		//동적 배열 채우기
		for(int i=3; i<=N; i++) {
			//f(n) = f(n-1) + f(n-2) 
			//f(n) == d[n]
			d[i] = d[i-1]+d[i-2];
		}
		System.out.println(d[N]);
	}
}