package algo240221;
import java.util.Scanner;

/**
 * 1. alphaCnt에 요구하는 ACGT의 개수를 담아놓음
 * 
 * 2. 뽑은 부분 문자열에 ACGT가 포함되는 경우 alphaCnt에서 -1씩 처리
 * 
 * 3. 이 때, 유효한 암호의 경우 alphaCnt의 ACGT 인덱스의 값이 모두 0보다 작아야 함
 * 
 * 
 * 문자열을 뽑는 방법
 * - 매번 S개를 뽑는 것이 아니라 맨앞 문자를하나 빼고 뒤에 문자를 추가하는 방식
 *   => 슬라이딩 윈도우 기법
 * 
 * 메모리:24692KB, 시간:352ms
 */
public class Main_B_12891_DNA비밀번호_연민호_배열 {
	static int P;
	static int S;


	static int[] alphaCnt = new int['Z'+1];	//인덱스 자체가 알파벳의 아스키 코드를 의미 
	//alpha['A'] : 3 이라면? A의 개수가 3개라는 의미

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		P = s.nextInt(); // 임의의 DNA 문자열 길이
		S = s.nextInt(); // 부분문자열 길이

		String str = s.next();

		alphaCnt['A'] = s.nextInt();
		alphaCnt['C'] = s.nextInt();
		alphaCnt['G'] = s.nextInt();
		alphaCnt['T'] = s.nextInt();


		int answer = 0; // 가능한 비밀번호의 개수

		// 초기 문자의 개수 구하기
		for (int i = 0; i < S; i++) {
			char alpha = str.charAt(i);
			alphaCnt[alpha]--;
		}
		// 비밀번호 가능여부 체크
		if (isValidACGT())	answer++;

		for (int i = 0; i < P - S; i++) {

			// 맨 앞자리 빠짐
			char front = str.charAt(i);
			alphaCnt[front]++;

			// 맨 뒷자리 추가
			char back = str.charAt(i + S);
			alphaCnt[back]--;

			// 비밀번호 가능여부 체크
			if (isValidACGT())	answer++;
		}

		System.out.println(answer);
		s.close();

	}

	// ACGT 개수 유효성 검사
	private static boolean isValidACGT() {
		String acgt = "ACGT";
		for (int i=0; i<4; i++) {
			if (alphaCnt[acgt.charAt(i)] > 0)
				return false;
		}
		return true;
	}

}