package algo0307;

import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        int[] arr = new int[10000001];
        for(int i = 0; i < tangerine.length; i++){
            arr[tangerine[i]] += 1;
        }
        Arrays.sort(arr);
        int sum = 0;
        for(int i = arr.length-1; i > -1; i--){
            sum += arr[i];
            answer++;
            if(sum >= k){
                break;
            }
        }
        return answer;
    }
}