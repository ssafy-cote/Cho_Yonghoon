package algo0227;

public class 아파트색칠하기_조용훈 {
	public static void main(String[] args) {
		int[] map = new int[9];
		
		map[1] = 2;
		map[2] = 3;
		
		for(int i = 3; i < 9; i++) {
			map[i] = map[i-1] + map[i-2];
		}
		System.out.println(map[8]);
		
	}

}
