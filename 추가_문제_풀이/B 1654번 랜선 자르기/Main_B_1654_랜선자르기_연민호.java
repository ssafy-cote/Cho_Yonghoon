package algo240214;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N : 만들어야하는 랜선의 개수
 * K : 갖고 있는 랜선의 개수
 * 
 * K : 1 ~ 10,000
 * 
 * N : 1 ~ 1,000,000
 * 
 * K<=N
 * 
 * K개의 랜선으로 N개의 랜선을 만들 때, 가장 길게 만들 수 있는 랜선의 길이
 * 
 * @author SSAFY
 *
 * 메모리:15340kb, 시간:140ms
 */
public class Main_B_1654_랜선자르기_연민호 {

	static int K, N;
	static int[] lines;	//랜선의 길이 정보
	
	static int max;	//랜선의 최대 길이
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		K = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		
		lines = new int[K];
		for(int i=0; i<K; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, lines[i]);
		}
		
		int maxLength = binarySearch();

		System.out.println(maxLength);
	}

	private static int binarySearch() {

		long left = 1;
		long right = max;
		int maxLength = 1;
		
		while(left<=right) {
			long mid = (left+right)/2;
			int cnt=0;	//만들 수 있는 랜선의 개수
			//현재 길이로 랜선 만들기
			for(int i=0; i<K; i++) {
				cnt+= lines[i]/mid;
			}
			
			//N개 이상의 랜선을 만들 수 있는 경우
			//maxLength에 담고 더 긴 길이로 시도해보기
			if(cnt>=N) {
				maxLength = (int)mid;
				left = mid+1;
			}
			//N개 이상의 랜선을 만들 수 없는 경우
			//더 짧은 길이로 시도해보기
			else {
				right = mid-1;
			}
		}
		
		return maxLength;
	}


}