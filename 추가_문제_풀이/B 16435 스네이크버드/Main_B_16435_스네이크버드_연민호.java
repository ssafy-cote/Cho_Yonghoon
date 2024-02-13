package algo240213;
import java.util.Arrays;
import java.util.Scanner;
/**
 * 메모리:16,004kb, 시간:164ms
 */
public class Main_B_16435_스네이크버드_연민호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int length = sc.nextInt();
		
		int[] fruits = new int[N];
		for(int i=0; i<N; i++) {
			fruits[i] = sc.nextInt();
		}
		//작은 과일부터 먹어보기 위해 오름 차순 정렬
		Arrays.sort(fruits);

		for(int i=0; i<N; i++) {
			if(fruits[i] > length) break;
			
			length++;
		}
		System.out.println(length);
		sc.close();
	}

}