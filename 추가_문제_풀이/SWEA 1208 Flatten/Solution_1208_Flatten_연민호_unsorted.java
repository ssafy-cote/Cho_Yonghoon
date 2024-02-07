package algo230130;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_Flatten_연민호_unsorted {

	public static void main(String[] args)  throws IOException {
		// TODO Auto-generated method stub
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		for(int tc =1 ; tc<=10; tc++) {
				int N = 100;
				int dump = Integer.parseInt(br.readLine());	//작업 회수
				int[] box = new int[N];
				
				// /r/t/n
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				//box 높이 받아오기
				for(int i=0;i<N;i++) {
					box[i] = Integer.parseInt(st.nextToken());
				}
				
				//주어진 작업회수 만큼
				for(int i=0; i<dump; i++) {
					
					//최고점, 최저점 탐색
					int max = 0;	//최고값
					int maxIdx = 0;	//최고값을 가진 요소의 index
					int min = Integer.MAX_VALUE;	//최저값
					int minIdx = 0;	//최저값을 가진 요소의 index
					for(int j=0; j<N; j++) {
						//최고점을 발견한 경우
						if(max < box[j]) {
							max = box[j];	//최고값 갱신
							maxIdx = j;		//최고값을 가진 요소의 index 저장
						}
						//최저점을 발견한 경우
						if(min > box[j]) {
							min = box[j];	//최저값 갱신
							minIdx = j;		//최저값을 가진 요소의 index 저장
						}
					}
					
					//dump 작업
					box[maxIdx]--;
					box[minIdx]++;
					
					//(최고점 - 최저점) 1 이하일 경우
					if( max-min <= 1) {
						System.out.println("#"+tc+" "+ (max-min) );
						System.exit(0);	
					}
				}
				
				//최고점, 최저점 탐색
				int max = 0;	//최고값
				int min = Integer.MAX_VALUE;	//최저값
				for(int j=0; j<N; j++) {
					//최고점을 발견한 경우
					if(max < box[j]) {
						max = box[j];	//최대값 갱신
					}
					//최저점을 발견한 경우
					if(min > box[j]) {
						min = box[j];	//최소값 갱신
					}
				}
				
				//출력
				System.out.println("#"+tc+" "+ (max-min) );
			
			
		}
		
		
		
		
	}

}