package algo240329;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B_17472_다리만들기2_연민호_dfs_MST_kruskal {
	//우 하
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	
	static int N, M;
	static int[][] map;
	
	static int labelCnt;	//라벨링된 섬의 개수
	
	static PriorityQueue<Edge> edgeList = new PriorityQueue<>((e1,e2)->e1.weight-e2.weight); //간선리스트(가중치 오름차순)
	
	static int[] parents;	//서로소 집합 배열
	
	static class Edge{
		int from, to;
		int weight;
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		//지도값 입력
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val==1 ? -1 : 0;	//섬을 1대신 -1로 입력
			}
		}
		
		//step 01. 섬 라벨링(FloodFilll)
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]!=-1) continue;	//라벨링할 섬이 아님
				
				//라벨링
				labelCnt++;
				labeling(i, j);
			}
		}
//		print(map);
		
		//step 02. 다리 만들기(간선리스트 만들기)
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				//라벨링 된 땅( >=1) 를 만나는 경우
				if(map[i][j]>=1) makeBridge(i, j);
			}
		}
		//간선 잘만들어지는지 체크
//		while(!edgeList.isEmpty()) {
//			System.out.println(edgeList.poll());
//		}
		
		//step 03. 모든 섬을 연결하는 비용 구하기 (MST 비용)
		System.out.println(getMSTCost());
	}

	private static int getMSTCost() {
		makeSet();	//서로소 집합 만들기
		
		int cost=0;			//MST 비용
		int unionCnt=0;		//합집합 연산 성공 횟수( 사용한 간선의 개수 )
		
		while(!edgeList.isEmpty()) {
			Edge edge = edgeList.poll();
			
			//합집합 연산시도
			if(union(edge.from, edge.to)) {
				
				//합집합 성공(union() 결과가 true)
				cost += edge.weight;	//비용 누적
				unionCnt++;			//합집합 연산 성공 횟수 +1
				
				//모든 섬을 이었다면 비용반환
				if(unionCnt==labelCnt-1) return cost;
			}
		}
		return -1;	//모든 섬 연결이 불가능한 경우 -1반환
	}
	
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		//같은 집합의 경우, 합집합 연산 불가
		if(rootA==rootB) return false;
		
		parents[rootB] = rootA; //합집합 연산
		return true;		//합집합 연산 성공했으니 true 반환
	}
	
	private static int find(int a) {
		//자기자신이 대표원소라면 자기자신 값 반환
		if(a==parents[a]) return a;
		return parents[a] = find(parents[a]);	//Path Compression
	}
	
	private static void makeSet() {
		parents = new int[labelCnt+1];
		for(int i=1; i<=labelCnt; i++) parents[i] = i;
	}

	/**
	 * 기준 위치(sr,sc)로부터 우, 하 방향에 다리 만들어보기
	 * @param sr 다리를 놓을 기준 위치의 행
	 * @param sc 다리를 놓을 기준 위치의 열
	 */
	private static void makeBridge(int sr, int sc) {
		//2방
		for(int dir=0; dir<2; dir++) {
			
			int length = 0;		//다리의 길이
			int r = sr;
			int c = sc;
			
			//현재 방향으로 한 칸씩 이동하면서 좌표체크
			while(true) {
				r += dr[dir];	//한 칸 이동
				c += dc[dir];
				
				if(r>=N || c>=M) break;		//a. 경계 벗어남(다리 못 만듦)
				if(map[r][c]==0) length++;	//b. 바다 => 다리 길이+1
				else {						//c. 땅
					if(length<2) break;					//다리 길이 2미만
					if(map[sr][sc]==map[r][c]) break;	//(sr,sc)땅의 섬라벨과 도착한(r,c) 땅의 섬라벨이 같음
					
					edgeList.add(new Edge(map[sr][sc], map[r][c], length));	//다리 정보 저장
					break;
				}
			}
		}
	}


	/**
	 * 현재 좌표(r,c) 를 라벨링하고 (r,c) 기준 사방의 라벨링은 재귀에 넘김
	 * @param r
	 * @param c
	 */
	private static void labeling(int r, int c) {
		map[r][c] = labelCnt;	//라벨링
		
		//(r,c) 기준 사방의 라벨링은 재귀에 넘김
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			
			if(!isInRange(nr, nc)) continue;	//경계 벗어남
			if(map[nr][nc]!=-1) continue;	//이미 라벨링
			
			labeling( nr, nc);
		}
	}
	/**
	 * 해당 좌표가 경계를 벗어나지 않으면 true 반환
	 * @param r
	 * @param c
	 * @return
	 */
	private static boolean isInRange(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	private static void print(int[][] array) {
		for(int i=0; i<array.length; i++ ) {
			for(int j=0; j<array[0].length;j++) {
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}