package algo0402;
import java.util.*;
import java.io.*;

public class P_영어끝말잇기_0402 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        Set<String> list = new HashSet<>();
        answer[1] = 1;
        for(int i = 0; i < words.length; i++){
            answer[0] += 1;
            if(answer[0] == n+1){
                answer[0] = 1;
                answer[1] += 1;
            }
            if(!list.add(words[i])){
                return answer;
            }
            if(i == 0){
                continue;
            }
            if(words[i].charAt(0) != words[i-1].charAt(words[i-1].length()-1)){
                return answer;
            }
        }
        answer = new int[2];
        return answer;
    }
}