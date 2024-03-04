package algo240229;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리:27908KB, 시간:532ms
 */
public class Main_B_10971_외판원순회2_연민호_비트마스킹순열_인접리스트 {
	static int N;
	
	static List<Node>[] adjList;	//인접 리스트
	
	static int minCost;			//최소 비용
	
	static class Node{
		int to;		//도착점
		int weight;	//가중치
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		adjList = new List[N];
		for(int i=0; i<N; i++) {
			adjList[i] = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val!=0) adjList[i].add( new Node(j, val) );
			}
		}

		minCost = Integer.MAX_VALUE;
		
		for(int number=0; number<N; number++) {	//number : 도시번호
			permutation(1, number, 0, number, 0 | 1<<number);
		}
		
		System.out.println(minCost);
	}
	/**
	 * cnt번째 방문할 도시 뽑고 다음 도시 선택은 재귀에 넘김
	 * @param cnt	현재까지 선택한 도시의 개수
	 * @param prev	이전에 뽑았던 도시 번호
	 * @param cost	현재까지 선택한 도시 방문 비용
	 * @param start	맨처음 선택한 도시의 번호
	 */
	private static void permutation(int cnt, int prev, int cost, int start, int flag) {
		if(cnt==N ) {
			//마지막 도시에서 처음 도시로 이동가능한 경우
			for(Node n : adjList[prev]) {
				if(n.to!=start) continue;	//시작점정보 아님
				
				cost+= n.weight;
				minCost = Math.min(minCost, cost);
			}
			return;
		}
		
		for(Node n : adjList[prev]) {
			if((flag & 1<<n.to)!=0) continue;			//이미 선택
			
			permutation(cnt+1, n.to ,cost+n.weight, start, flag | 1<<n.to);
		}
	}
}
