package algo240227;
import java.util.Scanner;

public class 막대색칠하기_연민호 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] d = new int[N+1];
		d[1] = 2;
		d[2] = 5;
		
		for(int i=3; i<=N; i++) {
			d[i] = d[i-1]*2 + d[i-2];
		}
		System.out.println(d[N]);
	}
	
}