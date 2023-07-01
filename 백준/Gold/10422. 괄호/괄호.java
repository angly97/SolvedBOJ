import java.io.*;
import java.util.*;

/*
	(A + B) % p = ((A % p) + (B % p)) % p
	(A * B) % p = ((A % p) * (B % p)) % p
	(A - B) % p = ((A % p) - (B % p) + p) % p
 */
public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
//		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		
		// dp[i] : i쌍의 괄호가 있을 때 만들 수 있는 올바른 괄효 문자열 갯수
		long[] dp = new long[2501];
		dp[0] = 1;
		dp[1] = 1;
        
		// pair : 괄호 쌍 갯수
		for(int pair = 2; pair <= 2500; pair++) {
			
			// 한쌍의 괄호'()' 안에 괄호가 있는 경우  * 한쌍의 괄호'()' 밖에 괄호가 있는 경우
			for(int i=0; i<pair; i++) {
				dp[pair] += ((dp[i]% 1000000007) * (dp[pair-i-1]% 1000000007 )) % 1000000007;
			}
		}
		
		
		// 테스트케이스 입력
		int l;

		StringBuilder out = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			l = Integer.parseInt(in.readLine());
			
			if(l%2 == 1)	out.append(0+"\n");
			
			else 			out.append((dp[l/2]%1000000007)+"\n");
		}
		
		System.out.print(out);
	}
	
}