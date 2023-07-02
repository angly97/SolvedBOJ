import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] arr1 = in.readLine().toCharArray();
		char[] arr2 = in.readLine().toCharArray();
		
		int n = arr1.length;
		int m = arr2.length;
		
		int[][] dp = new int[n+1][m+1];
		for(int i=0; i<n; i++) {
			for(int j=0; j<m ;j++) {
				if(arr1[i] == arr2[j])
					dp[i+1][j+1] = dp[i][j] + 1;
				else
					dp[i+1][j+1] = Math.max(dp[i+1][j], Math.max(dp[i][j+1], dp[i][j]));
			}
		}
		
		System.out.println(dp[n][m]);
	}

}