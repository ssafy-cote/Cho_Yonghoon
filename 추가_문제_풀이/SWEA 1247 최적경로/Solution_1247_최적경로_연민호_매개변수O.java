package algo240206;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:20,100kb, 시간:1,440ms
 */
public class Solution_1247_최적경로_연민호_매개변수O {

	static int N;	//고객의 수
	
	static Node[] houses;	//모든 집 정보
	
	static boolean[] isSelected; //해당 고객의 집 선택 여부
	
	static int min;	//최소 이동거리
	
	static class Node{
		int r,c;	//행열
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
		//현재 노드와 다른 노드간의 거리 반환
		public int getDis(Node n) {
			return Math.abs(this.r - n.r) + Math.abs(this.c - n.c); 
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			
			
			houses = new Node[N+2];	//0: 회사, 1: 집, 나머지 고객
			isSelected = new boolean[N+2];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N+2; i++) {
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				houses[i] = new Node(r, c);
			}
			
			min = Integer.MAX_VALUE;	//최소이동거리를 충분히 큰 값으로 초기화
			
			permutation(0, houses[0], 0);
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	/**
	 * 
	 * @param cnt	방문할 고객 순서
	 * @param prev 이전에 선택한 고객 집 정보
	 * @param distance	현재까지 방문했을 때의 거리
	 */
	private static void permutation(int cnt, Node prev, int distance) {
		//step 01. 고객 방문 순서 정하기 완료
		if(cnt == N) {
			
			//step 02. 마지막 고객집까지의 이동거리에 마지막 고객집에서 집까지의 거리 더하기
			distance += prev.getDis(houses[1]);
			
			//step 03. 이동거리 최솟값 비교
			min = Math.min(min, distance);
			
			return;
		}
		
		//2번 인덱스부터 고객의 집
		for(int i=2; i<N+2; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			
			Node pick = houses[i];	//결정한 고객의 집
			permutation(cnt+1, pick , distance+prev.getDis(pick) );
			
			isSelected[i] = false;
		}
	}
}