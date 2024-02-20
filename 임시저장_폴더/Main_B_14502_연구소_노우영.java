package algo0220;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_14502_������_��쿵 {
	static int N,M;
	static int[][] arr;
	static int[][] copyArr;
	static int[][] picked = new int[3][2]; //3�� ���� ��ǥ ����
	static int result =0;

	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		copyArr = new int[N][M];
		//���������
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] =  Integer.parseInt(st.nextToken());
			
			}
		}
		
//		for(int i=0; i<N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		comb(0,0);
		System.out.println(result);
	}//main



	private static void comb(int cnt, int start) {
		if(cnt == 3) {
			
			//���� ��������� Ȯ�� ���
//			for(int i=0; i<3; i++) {
//				System.out.println(Arrays.toString(picked[i]));
//			}
//			System.out.println();
			
			
			int y1,y2,y3,x1,x2,x3;
			y1=picked[0][0];
			y2=picked[1][0];
			y3=picked[2][0];
			x1=picked[0][1];
			x2=picked[1][1];
			x3=picked[2][1];


			//�� �� 0�϶��� ����
			if(arr[y1][x1]==0 && arr[y2][x2]==0 && arr[y3][x3]==0) {
				//���� �ǵ�� �ȵǴϱ� ���纻���� �˻�
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						copyArr[i][j] = arr[i][j];
					}
				}
				//�����ߴ� �ε����� �̿��Ͽ� �������
				copyArr[y1][x1] = 1;
				copyArr[y2][x2] = 1;
				copyArr[y3][x3] = 1;

				//dfs
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(copyArr[i][j] == 2) {
							dfs(i,j);
						}
					}
				}

//				for(int i=0; i<N; i++) {
//					System.out.println(Arrays.toString(copyArr[i]));
//				}
//				

				int temp=0; //0���� ������ ����
				
				for(int i=0; i<N; i++) {
					for(int j=0; j<M; j++) {
						if(copyArr[i][j] == 0) {
							temp++;
						}
					}
				}

				if(temp>result) result = temp;
			}
			return;
		}//���� �ϼ�


		for(int i=start; i<N*M; i++) {
			int r = i/M;
			int c = i%M;

			//y,x��ǥ�� picked�� ����
			picked[cnt][0] = r;
			picked[cnt][1] = c;

			comb(cnt+1, i+1);
		}


	}//comb

	private static void dfs(int y, int x) {
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny>=0 && ny<N && nx>=0 && nx<M && copyArr[ny][nx]==0) { //���� ��, ��ĭ�̸� ���̷��� ���� �� ����
				copyArr[ny][nx] = 2;
				dfs(ny,nx);
			}
		}

	}



}
