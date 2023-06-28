import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(in.readLine());

		int max = dfs(n, new HashMap<Integer, Integer>(), true);
		int min = dfs(n, new HashMap<Integer, Integer>(), false);
		
		
		System.out.println(min+" "+max);	
	}
	
	public static int dfs(int now, Map<Integer, Integer> dp, boolean isMax) {
		if(dp.containsKey(now)) {
			return dp.get(now);
		}
		// 한자리숫자일 때
		else if(now < 10) {
			return now % 2;
		}
		
		
		// 현재 숫자의 자릿수 세기
		int len = Integer.toString(now).length();
		
		
		// 현재 숫자의 홀수 갯수 세기
		int oddCnt = 0;
		for(int i=0; i<len; i++) {
			oddCnt += now/(int)Math.pow(10, len-1-i) % 2;
		}
		
		
		// 두자리 숫자일 때
		if(now < 100) {
			oddCnt += dfs(now/10 + now%10, dp, isMax);
			
			dp.put(now, oddCnt);
			return oddCnt;
		}
		
		// 3자리 이상일 때
		else {
			int nxt = 0;
			
			int tempOddCnt = 0;
			
			// 최댓값을 구하는거면 0으로 초기화, 최솟값을 구하는거면 최대 int값으로 초기화
			int finalOddCnt = isMax ? 0 : Integer.MAX_VALUE;
			
			// 3등분 하기
			for(int i=0; i<len-2; i++) {
				for(int j=i+1; j<len-1; j++) {
					
					// 3등분해서 3개 숫자 더하기
					nxt = now/(int)Math.pow(10, len-1-i);
					
					int remain = now % (int)Math.pow(10, len-1-i);
					
					nxt += remain/(int)Math.pow(10, len-1-j);
					nxt += remain%(int)Math.pow(10, len-1-j);
					
					// 홀수 갯수 세기
					tempOddCnt = dfs(nxt, dp, isMax) + oddCnt;
					
					// 최종 홀수 갯수 갱신
					if(isMax) {
						finalOddCnt = Math.max(finalOddCnt, tempOddCnt);
					}
					else {
						finalOddCnt = Math.min(finalOddCnt, tempOddCnt);
					}
					
				}
			}
			
			dp.put(now, finalOddCnt);
			return finalOddCnt;
		}
	}

}