import java.io.*;
import java.util.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char[] a = in.readLine().toCharArray();
		char[] b = in.readLine().toCharArray();
		
		int asize = a.length;
		int bsize = b.length;
		
		int[][] dp = new int[asize+1][bsize+1];
		
		for(int i=1; i<=asize; i++) {
			for(int j=1; j<=bsize; j++) {
				
				if(a[i-1] == b[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		int aidx = asize;
		int bidx = bsize;
		
		StringBuilder out = new StringBuilder();
		
		while(aidx != 0 || bidx != 0) {
            
            // if문 순서 중요! dp[aidx][bidx] == dp[aidx-1][bidx-1] + 1 이게 지금처럼 맨 뒤로 가야함!!
			if(aidx > 0 && dp[aidx][bidx] == dp[aidx-1][bidx]) {
				aidx--;
			}
			else if(bidx > 0 && dp[aidx][bidx] == dp[aidx][bidx-1]) {
				bidx--;
			}
			else if(aidx > 0 && bidx > 0 && dp[aidx][bidx] == dp[aidx-1][bidx-1] + 1) {
				out.append(a[aidx-1]);
				aidx--;
				bidx--;
			}
		}
		
		System.out.println(dp[asize][bsize]+"\n"+out.reverse());
	}
	
}