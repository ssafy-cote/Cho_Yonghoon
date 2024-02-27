package algo240227;
import java.util.Scanner;
/**
 * 메모리:32892KB, 시간:136ms
 */
public class Main_B_1463_1로만들기_연민호_하향식 {
	static int[] d;
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		
		d = new int[n+1];	//d[i] : i를 1로 만드는 최소 연산의 수
		
		System.out.println(f(n));
		s.close();
		
	}
	
	private static int f(int n) {
		//기저 조건 1을 만드는 경우의 수는 0회의 연산 필요
		if(n==1) return 0;
		if(d[n]!=0) return d[n];	//저장된 값이 있다면 저장된 값 반환
		
		//최소 경우의 수 저장할 변수
		int min = Integer.MAX_VALUE;
		
		//직전에 3을 곱해서 만들어진 경우의 수
		if(n%3==0) min = Math.min( f(n/3) + 1 , min);
		
		//직전에 2를 곱해서 만들어진 경우의 수
		if(n%2==0) min = Math.min( f(n/2) + 1 , min);
		
		//직전에 1을 더해서 만들어진 경우의 수
		min = Math.min( f(n-1) + 1, min);
		
		//3가지 중 최소 연산수를 n을 만드는 최소 연산 수로 저장
		return d[n] = min;
	}
}