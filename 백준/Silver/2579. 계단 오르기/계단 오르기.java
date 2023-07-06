import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());

		int n = Integer.parseInt(in.readLine());
		
		int[] staire = new int[n];
		for(int i=0; i<n; i++)
			staire[i] = Integer.parseInt(in.readLine());
		
		int[][] dp = new int[n][2];
		
		Arrays.fill(dp[0], staire[0]);
		
		if(n > 1) {
			dp[1][0] = staire[1];
			dp[1][1] = staire[0] + staire[1];
			
			if(n > 2) {
				dp[2][0] = staire[0] + staire[2];
				dp[2][1] = staire[1] + staire[2];
				
				for(int i=3; i<n; i++) {
					dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + staire[i];
					dp[i][1] = dp[i-1][0] + staire[i];
				}
			}

		}

		
		int ans = Math.max(dp[n-1][0], dp[n-1][1]);
		System.out.println(ans);
		
	}
}