package algo240207;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_B_11286_절댓값힙_연민호_Scanner {
	
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->{
			int diff = Math.abs(a)-Math.abs(b);
			if(diff==0) return a-b;
			return diff; 
		});
		StringBuilder sb = new StringBuilder();
		
		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();

		while(N-->0) {
			
			int num = s.nextInt();
			if(num==0) sb.append(pq.peek()==null?0:pq.poll()).append('\n');
			
			else pq.add(num);
		}
		
		System.out.println(sb);
		s.close();
	}

}