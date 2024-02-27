package algo240227;
import java.util.Scanner;
/**
 * 메모리:16824KB, 시간:132ms
 */
public class Main_B_1463_1로만들기_연민호_상향식 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();
		
		//d[i] - i를 1로 만드는 최소 연산 수
		int[] d = new int[N+1];
		if(N>1) d[2] = 1;		//N이 1인 경우엔 Index에러 발생
		
		for(int i=3; i<=N; i++) {
			
			//-1 연산 시
			int min = d[i-1];
			
			// 나누기 3연산 시
			if(i%3==0) min = Math.min(min, d[i/3]);
			
			// 나누기 2연산 시 
			if(i%2==0) min = Math.min(min,  d[i/2]);
			
			d[i] = min+1;
		}
		
		System.out.println(d[N]);
		s.close();
	}
}