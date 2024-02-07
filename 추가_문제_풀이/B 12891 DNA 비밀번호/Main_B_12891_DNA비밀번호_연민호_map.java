package algo240221;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * 
 * 
 * 메모리:51076KB, 시간:536ms, 
 */
public class Main_B_12891_DNA비밀번호_연민호_map {
	static int P;
	static int S;

	static Map<Character, Integer> checkACGT = new HashMap<>();


	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		P = s.nextInt(); // 임의의 DNA 문자열 길이
		S = s.nextInt(); // 부분문자열 길이

		String str = s.next();

		checkACGT.put('A', s.nextInt());
		checkACGT.put('C', s.nextInt());
		checkACGT.put('G', s.nextInt());
		checkACGT.put('T', s.nextInt());


		int answer = 0; // 가능한 비밀번호의 개수

		// 초기 문자의 개수 구하기
		for (int i = 0; i < S; i++) {
			char alpha = str.charAt(i);
			if (checkACGT.containsKey(alpha))
				checkACGT.put(alpha, checkACGT.get(alpha) - 1);
		}
		// 비밀번호 가능여부 체크
		if (isValidACGT())
			answer++;

		for (int i = 0; i < P - S; i++) {

			// 맨 앞자리 빠짐
			char front = str.charAt(i);
			if (checkACGT.containsKey(front))
				checkACGT.put(front, checkACGT.get(front) + 1);

			// 맨 뒷자리 추가
			char back = str.charAt(i + S);
			if (checkACGT.containsKey(back))
				checkACGT.put(back, checkACGT.get(back) - 1);

			// 비밀번호 가능여부 체크
			if (isValidACGT())
				answer++;
		}

		System.out.println(answer);
		s.close();

	}

	// ACGT 개수 유효성 검사
	private static boolean isValidACGT() {
		for (char key : checkACGT.keySet()) {
			if (checkACGT.get(key) > 0)
				return false;
		}
		return true;
	}
}