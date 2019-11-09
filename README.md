# 2019_KAKAO_Developer_intern_05

## 2019 카카오 개발자 겨울 인턴십 코딩테스트 5번

### 1. 문제설명

input으로 징검다리의 디딤돌에 적혀있는 숫자값 배열 `int[] stones`와 한번에 뛰어넘을 수 있는 최대 길이 `int k`가 들어온다. 한 명이 디딤돌을 건널때 한칸씩 건너가며 지나간 디딤돌의 숫자는 `1`씩 줄어든다. `0`이 된 디딤돌은 밟고 지나갈 수 없으므로 점프하여 지나가야하는데 점프할 수 있는 최대 길이다 `k`이다. 이때 최대 몇 명까지 징검다리를 건널 수 있는지 return하는 문제

### 2. 풀이

한 사람마다 디딤돌을 건너는 행위를 해주는 것은 너무 시간이 오래걸릴 것 같아 두 가지 방법을 생각했다.

#### 2.1) min을 찾아 한번에 제거하는 방법 [정확도 100%, 효율성 0%]

두가지의 step을 반복하며 지나갈 수 있는 사람의 명수를 세주었다.

1)  징검다리를 건널 수 있는지 검사
2)  건너지 못한다면 현재까지 지나간 명수 return
3)  건널수 있다면 `0`이 아닌 디딤돌 중 최소값을 찾고 그 값을 `answer`에 더하고 모든 `0`아닌 디딤돌의 값을 `min`만큼 뺀다.

위의 과정을 반복하여 한번에 `min`만큼 사람을 한번에 보내는 방법으로 시간을 줄였다. 하지만 효율성은 해결되지 않았다.

```java

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

```

#### 2.2) k개만큼 끊어 max값을 검사, 가장 작은 max값 

최대 `k`칸 점프할 수 있다는 것은 디딤돌을 `k`개 끊어서 볼때, 가장 큰 값을 가지는 디딤돌의 값 만큼의 사람이 징검다리를 이동할 수 있다는 것을 뜻한다. 이때 이러한 `k`개씩 끊어서 `max`값을 찾는 행위를 디딤돌의 처음부터 끝까지 이어나간다고 하자. 매번 찾는 `max`값은 지나갈 수 있는 사람의 수를 뜻하며, 이 `max`값 중 가장 작은 값을 가지는 부분에 `max`명의 사람이 지나가면 더 이상 사람이 지나갈 수 없다.

이러한 방법으로 `k`개씩 끊어 해당 구간의 `max`를 구하고 이 중 `min`값을 return하여 해결할 수 있다.

```java

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

```

코드를 작성하고 테스트를 하려고 할 때 시간이 다 되어 제출하지 못하였는데, 이 방법이 가장 효율적일 수 있다고 생각하낟.
