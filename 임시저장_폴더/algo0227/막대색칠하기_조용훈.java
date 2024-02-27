package algo0227;

public class 막대색칠하기_조용훈 {
	public static void main(String[] args) {
		int[] dp = new int[9];
		dp[1] = 2;
		dp[2] = 5;
		
		for(int i = 3; i < 9; i++) {
			dp[i] = 2 * dp[i-1] + dp[i-2];
		}
		System.out.println(dp[6]);
	}
}
