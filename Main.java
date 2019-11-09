import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
//		int k = 3;
//		// return 3;
		
		int[] stones = {7, 7, 5, 3, 1, 1, 4, 3, 5, 1};
		int k = 5;
		// return 4;
		
		
		System.out.println(new Solution().solution(stones, k));
	}

}

class Solution {
    public int solution(int[] stones, int k) {
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < stones.length - k; i++) {
        	int max = Arrays.stream(Arrays.copyOfRange(stones, i, i+k)).max().getAsInt();
        	min = min < max ? min : max;
        }
        
        return min;
    }
}

class Solution_Correctness_Only {
    public int solution(int[] stones, int k) {
        int answer = 0;
        
        if (k == stones.length) {
        	return Arrays.stream(stones).max().getAsInt();
        }
        
        boolean flag = true;
        while (flag) {
        	int count = 0;
        	// 건너뛸수있는지 검사
        	for (int i = 0; i < stones.length; i++) {
        		if (stones[i] == 0) {
        			count++;
        			if (count == k) {
        				flag = false;
        				break;
        			}
        		} else {
        			count = 0;
        		}
        	}
        	
        	if (flag) {
        		int min = Arrays.stream(stones).filter(i -> i > 0).min().orElse(0);
            	if (min == 0)
            		break;
    			for (int i = 0; i < stones.length; i++) {
    				if (stones[i] != 0)
    					stones[i] -= min;
    			}
    			answer += min;
        	}
        }
        
        return answer;
    }
}