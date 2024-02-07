package algo240205;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * 메모리:293780KB, 시간:676ms
 */
public class Main_B_요세푸스문제_연민호_큐_LinkedList {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int N = s.nextInt();	//숫자의 개수
		int K = s.nextInt();	//K번째
		
		Queue<Integer> q = new LinkedList<>();
		
		//숫자 세팅
		for(int i=1;i<=N;i++) {
			q.offer(i);
		}
		
		sb.append('<');
		int num;	//숫자
		for(int i=1;i<=N*K;i++) {
			
			num = q.poll();
			if(i%K!=0) q.offer(num); 
			else {
				sb.append(num);
				if(!q.isEmpty()) sb.append(", ");
			}
		}
		sb.append('>');
		
		System.out.println(sb);
	}

}
