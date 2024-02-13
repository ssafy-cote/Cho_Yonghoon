package algo240213;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 맵 정보를 받아놓고 문제를 풀다보니 맵 정보는 필요가 없음. 
 * 필요한 좌표 값들은 따로 저장해놓았기 때문
 * @author minho
 *
 * 메모리:13060kb, 164ms
 *
 */
public class Main_B_15686_치킨배달_연민호_기본재귀조합 {
	static int N,M;	//도시의 행열크기, 치킨집 최대 개수

	static int ans;	//최소 도시 치킨 거리

	static List<Node> houses = new ArrayList<>();	//집 정보
	static List<Node> chickenHouses = new ArrayList<>();	//치킨집 정보

	static Node[] selectedChicken;	//	선택된 치킨집 정보

	static class Node{
		int r;
		int c;
		public Node(int r, int c) {
			this.r =  r;
			this.c =  c;
		}
		//다른 노드와의 거리계산 메소드
		public int getDist(Node n) {
			return Math.abs(this.r-n.r)+Math.abs(this.c-n.c);
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());	//도시의 행렬 크기 입력
		M = Integer.parseInt(st.nextToken());	//치킨집 최대 개수 입력

		ans=Integer.MAX_VALUE;	//최소 도시 치킨거리 초기화

		selectedChicken = new Node[M];

		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int info = Integer.parseInt(st.nextToken());
				if(info==1) houses.add(new Node(i,j));	// 집정보 받아놓기
				else if(info==2) chickenHouses.add(new Node(i,j));	//치킨집 정보 받아놓기
			}
		}
		combination(0, 0);

		System.out.println(ans);
	}

	/**
	 * cnt번째 치킨 집 선택 후, 다음 치킨 집 선택 넘기기
	 * @param cnt	현재까지 선택한 치킨집의 개수
	 * @param start	cnt번째 치킨집 선택 시 고려할 요소들의 시작인덱스 
	 */
	private static void combination(int cnt, int start) {
		//step 01. M개의 치킨 집 선택완료
		if(M == cnt) {
			//step 02. 도시 치킨 거리 구하기
			int dis = getCityChick();
			//step 03. 구한 도시 치킨 거리가 최소값이라면 갱신
			ans = Math.min(ans, dis);
			return;
		}


		for(int i=start; i<chickenHouses.size(); i++) {
			selectedChicken[cnt] = chickenHouses.get(i);
			combination(cnt+1, i+1);
		}
	}
	//현재 선택된 치킨집들의 도시 치킨거리 구해서 반환
	private static int getCityChick() {
		//1. 각 집들의 치킨 거리 구하기
		int cityChick = 0; //도시 치킨거리 담을 변수
		//집들의 개수 만큼 반복
		for(Node house : houses) {
			int chickDis=Integer.MAX_VALUE;	//해당 집의 치킨 거리 담을 변수
			//선택한 치킨집 만큼 반복
			for(Node chicken : selectedChicken) {
				chickDis = Math.min(chickDis, chicken.getDist(house));	// 거리구하기
			}
			//2. 해당 집의 치킨 거리를 도시 치킨거리에 누적
			cityChick += chickDis;
		}
		return cityChick;
	}
}