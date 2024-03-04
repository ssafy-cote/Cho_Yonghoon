package algo240229;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:11892KB, 시간:256ms
 */
public class Main_B_10971_외판원순회2_연민호_비트마스킹순열_인접행렬 {
	static int N;
	
	static int[][] adjMatrix;	//인접 행렬
	
	static int minCost;			//최소 비용
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
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
			if(adjMatrix[prev][start]!=0){
				cost+= adjMatrix[prev][start];
				minCost = Math.min(minCost, cost);
			}
			return;
		}
		
		for(int to=0; to<N; to++) {
			if((flag & 1<<to)!=0) continue;			//이미 선택
			if(adjMatrix[prev][to]==0) continue;	//prev => to 가 인접하지 않음
			
			permutation(cnt+1, to ,cost+adjMatrix[prev][to], start, flag | 1<<to);
		}
	}
}
