package algo0313;

public class P_2개이하로다른비트_0313 {
	public static long[] solution(long[] numbers) {
		long[] answer = numbers.clone();
		for (int i = 0; i < numbers.length; i++) {
			answer[i]++; // x보다 큰수로 만든다.
			answer[i] += (answer[i] ^ numbers[i]) >> 2;
		}
		return answer;
	}
}