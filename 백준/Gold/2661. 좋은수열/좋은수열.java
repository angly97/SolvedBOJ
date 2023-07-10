import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(in.readLine());
		
		// dp[i][j] : i로 끝나는, 길이 j인 좋은 수열 중 가장 작은 수(char[] 로 표현)
		char[][][] dp = new char[4][n+1][80];
		for(int i=0; i<=3; i++)
			for(int j=0; j<=n; j++)
				Arrays.fill(dp[i][j], '9');
		
		for(int end=1; end<=3; end++)
			dp[end][1][0] = (char)(end+'0');

		
		char[] num;
		
		for(int len=2; len<=n; len++) {
			
			for(int end=1; end<=3; end++) {
				
				for(int prev=1; prev<=3; prev++) {
					if(end == prev)	continue;
					
					num = nxtNum(dp[prev][len-1], end, len-1);
					if(!checkBad(num, len)) {
						dp[end][len] = getMinCharArr(dp[end][len], num, len);
					}
				}
				
			}
		}
		
		char[] minGoodArr = getMinCharArr(getMinCharArr(dp[1][n], dp[2][n], n), dp[3][n], n);
		
		StringBuilder ans = new StringBuilder();
		for(int i=0; i<n; i++) {
			ans.append(minGoodArr[i]);
		}
		
		System.out.println(ans);
	}
	
	private static char[] getMinCharArr(char[] a, char[] b, int len) {
		for(int i=0; i<len; i++) {
			if(a[i] < b[i])			return a;
			else if(a[i] > b[i])	return b;
		}
		return a;
	}

	static char[] nxtNum(char[] now, int end, int len) {
		char[] nxt = new char[len+1];
		for(int i=0; i<len; i++)
			nxt[i] = now[i];
		nxt[len] = (char)(end+'0');
		return nxt;
	}
	
	static boolean checkBad(char[] num, int totallen) {
		boolean isbad = true;
		
		// 부분 수열 길이 len이 2 ~ total/2 때까지
		for(int len = 2; len <= totallen/2; len++) {
			
			// 인접 부분 수열간 비교
			for(int i=0; i<=totallen-len*2; i++) {
				
				// 본격 비교
				for(int j=0; j<len; j++) {
					
					if(num[i+j] != num[i+len+j]) {
						isbad = false;
						break;
					}
				}

				if(isbad) {
					return true;
				}
				
				isbad = true;
			}
		}
		
		return false;
	}
}