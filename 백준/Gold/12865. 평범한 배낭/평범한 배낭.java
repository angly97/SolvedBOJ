import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
	
		st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] items = new int[n][2];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i] : 가방에 있는 물건 무게 합이 i일 때, 최대 가치합
		int[] dp = new int[k+1];
		
		for(int[] item: items) {
			for(int wei = k; wei >= item[0]; wei--) {		// k ~ 현재무게까지 역순으로 순회 => 이래야 물건이 1개만 들어감
				dp[wei] = Math.max(dp[wei], dp[wei-item[0]] + item[1]);		// 최대값 업데이트
			}
		}
		
		System.out.println(dp[k]);
	}
}