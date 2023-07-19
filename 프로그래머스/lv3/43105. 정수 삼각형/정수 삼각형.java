import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        
        int[][] dp = new int[n][n];
        for(int i=0; i<n-1; i++)
            Arrays.fill(dp[i], -1);
        
        for(int i=0; i<n; i++)
            dp[n-1][i] = triangle[n-1][i];
        
        return dfs(0, 0, dp, n, triangle);
    }
    
    int[][] deltas = {{1, 0}, {1, 1}};
    
    public int dfs(int r, int c, int[][] dp, int n, int[][] tri){
        
        if(dp[r][c] != -1) {
            return dp[r][c];
        }
        
        int[] nxt = new int[2];
        
        for(int[] del : deltas) {
            nxt[0] = r + del[0];
            nxt[1] = c + del[1];
            
            dp[r][c] = Math.max(dp[r][c], dfs(nxt[0], nxt[1], dp, n, tri));
        }
        
        dp[r][c] += tri[r][c];
        
        return dp[r][c];
    }
}