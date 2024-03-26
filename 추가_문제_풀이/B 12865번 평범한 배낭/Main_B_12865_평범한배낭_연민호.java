package algo240326;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 메모리:12188KB, 시간:112ms
 * N : 물건의 개수
 * 
 * W : 무게, V : 가치
 * 
 * K : 최대 배낭 무게
 * 
 * 배낭을 채웠을 때 최대가치 구하기
 * 
 * @author minho
 *
 */
public class Main_B_12865_평범한배낭_연민호 {

	static int N,K;	//물건 개수, 배낭 최대 무게
	
	static Thing[] things;	//물건 정보
	
	static class Thing{
		int W, V;	// 무게, 가치
		
		public Thing(int W, int V) {
			this.W = W;
			this.V = V;
		}
	}
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		things = new Thing[N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int W = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			things[i] = new Thing(W, V);
		}
		
		//0~K 무게를 배낭의 최대 무게로 고려했을 때, 배낭을 채울 수 있는 최대 가치
		int[] d = new int[K+1];
		
		//i : 고려한 물건의 개수
		//i=2 인 경우, 0~2번까지의 물건을 고려했을 때
		A : for(int i=0; i<N; i++) {
			int w = things[i].W;
			int v = things[i].V;
			
			//j : 임시 배낭의 무게
			for(int j=K; j>=1; j--) {
				//임시 배낭의 무게보다 물건의 무게가 큰 경우는 다음 무게
				if( j < w ) continue A;
				
				//j무게의 배낭에 i번째무게를 포함하지 않은 경우와 포함한 경우를 비교했을 때 큰 가치를 배열에 담음
				d[j] = Math.max( d[j], d[j-w] + v );
			}
		}
		
		System.out.println(d[K]);
		
	}

}