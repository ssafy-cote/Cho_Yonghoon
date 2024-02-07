package algo230130;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1208_Flatten_연민호_sorted {

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
				
				int diff = Integer.MAX_VALUE; //차이 초기값
				
				//오름차순 정렬
				Arrays.sort(box);
				
				//주어진 작업회수 만큼
				for(int i=0; i<dump; i++) {
					
					//dump 작업
					box[N-1]--;
					box[0]++;
					
					//오름차순 정렬
					Arrays.sort(box);
					
					//최고점과 최저점의 차이 구하기
					diff = box[N-1] - box[0];
					
					//diff 1 이하일 경우
					if(diff<=1) break;
					
				}
				
				//출력
				System.out.println("#"+tc+" "+diff);
		}
	}
}