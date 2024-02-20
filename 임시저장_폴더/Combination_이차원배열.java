package algo0220;

import java.util.Arrays;
import java.util.Scanner;

public class Combination_�������迭 {
	static int R = 3;	//��
	static int C = 4;	//��
	
	static int K;		// ���� ����
	
	static int[][] arr = {
			{1,3,5,7},
			{9,11,13,15},
			{17,19,21,23}
	};
	
	//
//	0  1  2  3
//	4  5  6  7
//	8  9  10 11
	//R = 3 , C = 4
	//R*C => 12
	
	static int[] picked;	//���� ��� ����
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		
		picked = new int[K];
		
//		combination(0, 0, 0);
		
		combination2(0, 0);
	}
	
	//
//	0  1  2  3
//	4  5  6  7
//	8  9  10 11
	//R = 3 , C = 4
	//R*C => 12
	private static void combination2(int cnt, int start) {
		if(cnt==K) {
			System.out.println(Arrays.toString(picked));
			return;
		}
		
		for(int i=start; i<R*C; i++) {
			int r = i/C;	//��
			int c = i%C;	//��
			
			//��� �̱�
			picked[cnt] = arr[r][c];
			
			combination2(cnt+1, i+1);
		}
	}
	
	/**
	 * cnt��° ��Ҹ� �̰� ���� ��һ̱�� ��ͷ� �ѱ�
	 */
	private static void combination(int cnt, int startR, int startC){
		if(cnt == K ) {
			System.out.println(Arrays.toString(picked));
			return;
		}
		
		
		//�ĺ���
		for(int i=startR; i<R; i++) {
			for(int j = (i==startR? startC : 0); j<C; j++) {
				picked[cnt] = arr[i][j];	//cnt��° ���� ���
				
				if(j!=C-1) combination( cnt+1, i, j+1 );
				else combination(cnt+1, i+1, 0);		//������ ���� ��� 
			}
		}
	}
}
