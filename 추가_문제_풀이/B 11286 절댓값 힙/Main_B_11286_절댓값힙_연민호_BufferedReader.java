package algo240207;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main_B_11286_절댓값힙_연민호_BufferedReader {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b)->{
			int diff = Math.abs(a)-Math.abs(b);
			if(diff==0) return a-b;
			return diff; 
		});
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());

		while(N-->0) {
			
			int num = Integer.parseInt(br.readLine());
			if(num==0) sb.append(pq.peek()==null?0:pq.poll()).append('\n');
			
			else pq.add(num);
		}
		
		System.out.println(sb);
	}

}