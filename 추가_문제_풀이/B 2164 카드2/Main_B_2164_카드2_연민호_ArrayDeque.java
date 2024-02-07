package algo240202;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * 메모리:32,896KB,시간:164ms
 */
public class Main_B_2164_카드2_연민호_ArrayDeque {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		Queue<Integer> q = new ArrayDeque<>();

		int n = s.nextInt();
		// 자료구조 세팅
		for (int i = 1; i <= n; i++) {
			q.offer(i);
		}

		while (q.size() > 1) {
			q.poll(); // 맨 위의 카드 버리기

			// 맨 위의 카드 맨 아래로
			int card = q.poll();
			q.offer(card);
		}

		System.out.println(q.peek());
		s.close();
	}

}