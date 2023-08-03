import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 최장공통부분수열 알고리즘을 응용
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] s1 = in.readLine().toCharArray();
		char[] s2 = in.readLine().toCharArray();
		
		int n = s1.length;
		int m = s2.length;
		
		// dp[i][j]: s1[i]와 s2[j]가 같을 때, 이 위치를 끝으로 하는 공통 문자열 길이	
		int[][] dp = new int[n+1][m+1];
		
		int ans = 0;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				if(s1[i-1] == s2[j-1]) {
					dp[i][j] = dp[i-1][j-1]+1;
				}
				ans = Math.max(ans, dp[i][j]);
			}
		}
		
		System.out.println(ans);
		
	}

}