package algo0429;

public class P_타겟넘버_0429 {
    static int size;
    static int targetNum;
    static int[] list;
    static int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        size = numbers.length;
        targetNum = target;
        list = new int[size];
        for(int i = 0; i < size; i++){
            list[i] = numbers[i];
        }
        dfs(1, -list[0]);
        dfs(1, list[0]);
        return answer;
    }
    
    static void dfs(int index, int sum){
        if(index == size){
            if(sum == targetNum){
                ++answer;
            }
            return;
        }
        dfs(index + 1, sum - list[index]);
        dfs(index + 1, sum + list[index]);
    }
}