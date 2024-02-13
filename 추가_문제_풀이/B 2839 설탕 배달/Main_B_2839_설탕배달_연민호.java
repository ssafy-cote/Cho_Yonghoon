package algo240213;
import java.util.Scanner;
/**
 * 메모리:12,860kb, 시간:112ms
 */
public class Main_B_2839_설탕배달_연민호 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();
		
		int cnt = 0;
		
		while(N>0) {
			//5로 나눈나머지가 0이라면?
			if(N%5==0) {
				cnt+= N/5;
				break;
			}
			//5로 나눈 나머지가 0이 아니라면?
			//3키로로 하나 포장
			N-=3;	//3키로 만큼 설탕무게 감소
			cnt++;	//봉지 개수 추가
		}
		
		System.out.println(N<0?-1:cnt);
	}

}