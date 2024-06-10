import java.util.*;
import java.io.*;

public class Solution {
    public int solution(int n) {
        int ans = 1;
        while(n > 1){
            if(n % 2 == 1){
                n -= 1;
                ans++;
            }else{
                n /= 2;
            }
        }
        return ans;
    }
}