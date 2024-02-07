package algo240206;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 메모리:21,500kb, 시간:2,009ms
 */
public class Solution_1247_최적경로_연민호_매개변수X {

	static int N;	//고객의 수
	
	static Node[] houses;	//모든 집 정보
	
	static boolean[] isSelected; //해당 고객의 집 선택 여부
	static Node[] client;	//고객의 집 방문 순서를 담을 배열
	
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
			
			client = new Node[N];
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void permutation(int cnt) {
		//step 01. 고객 방문 순서 정하기 완료
		if(cnt == N) {
			//step 02. 이동거리 계산
			int distance = getTotalDis();
			
			//step 03. 이동거리 최솟값 비교
			min = Math.min(min, distance);
			
			return;
		}
		
		//2번 인덱스부터 고객의 집
		for(int i=2; i<N+2; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true;
			client[cnt] = houses[i];
			
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
	
	//현재 고객 방문 순서일 때의 누적 이동거리 반환
	private static int getTotalDis() {
		int distance = 0;
		
		//회사에서 첫번째 고객 이동거리  누적
		distance += houses[0].getDis(client[0]);
		
		for(int i=0; i<N-1; i++) {
			//현재 고객과 다음 고객 간의 이동거리 +
			distance += client[i].getDis(client[i+1]);
		}
		
		//마지막 고객에서 집까지 이동거리 누적
		distance += houses[1].getDis(client[N-1]);
		
		return distance;
	}

}