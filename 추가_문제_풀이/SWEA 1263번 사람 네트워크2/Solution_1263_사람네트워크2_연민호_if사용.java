package algo240328;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 * 메모리:105,024kb, 시간:4,036ms
 */
public class Solution_1263_사람네트워크2_연민호_if사용 {
    static final int INF = 2000;    //정점A에서 정점 B를 가는데 올 수 있는 최대 비용 보다 큰 값   
    static int N,dist[][];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
 
            dist = new int[N+1][N+1];
            for(int from=1; from<=N; ++from) {
                for(int to=1; to<=N; ++to) {
                    int val = Integer.parseInt(st.nextToken());
                    if(val==0 && from!=to)dist[from][to] = INF; //자기자신으로의 인접 정보가 아니고 인접해있지 않다면 INF로 채우기
                    else dist[from][to] = val;
                }
            }
             
            //경유지-->출발지-->도착지
            for(int k=1; k<=N; k++) {				//k : 경유지
                for(int from=1; from<=N; from++) {  //from : 출발지
                    for(int to=1; to<=N; to++) {    //to : 도착지
                        //0~k-1까지 고려한 (from=>to)의 최소비용과 (from=>k=>to)의 최소비용을 비교
                    	if(dist[from][to] <= dist[from][k]+dist[k][to]) continue;
                    	
                    	dist[from][to] = dist[from][k]+dist[k][to];
                    }
                }
            }
            //이번 문제에서 k==from 또는 from==to 또는 k==to인 경우를 따로 처리하지 않아도 되는 이유?
            //자기 자신의 정점(i)까지의 거리(dist[i][i])가 0이기 때문에 결과를 바꾸지 않음
            sb.append("#").append(tc).append(" ").append(getMinCC()).append("\n");
        }
        System.out.println(sb);
    }
     
    //최소 CC 값 반환
    private static int getMinCC() {
        int minCC = Integer.MAX_VALUE;
         
        //각 정점으로부터 다른 정점까지의 최소거리의 총합 중 최솟값 구하기
        for(int from=1; from<=N; from++) {
            int curCC = 0;
            for(int to=1; to<=N; to++) {
                curCC+=dist[from][to];
            }
            minCC = Math.min(minCC, curCC);
        }
        return minCC;
    }
    
    private static void print() {
    	for(int i=0; i<N; ++i) {
    		for(int j=0; j<N; ++j) {
    			System.out.print(dist[i][j]+"\t");
    		}
    		System.out.println();
    	}
    	System.out.println("=====================================");
    }
}