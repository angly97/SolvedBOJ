import java.util.*;
import java.io.*;

/*
 * 처음에 DP인지 모르고 했다가 시간초과
 * 성냥개비 6개일 때는 6아니면 0임 -> big의 성냥수가 6일때, 이걸 6이 아니라 0으로 치환
 */
public class Main {
	
	public static void main(String[] args) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());

		
		Str[] dp = new Str[101];
		for(int i=0; i<=100; i++)
			dp[i] = new Str(51, new char[101]);

		dp[2].len = 1;		dp[2].str[0] = '1';
		dp[3].len = 1;		dp[3].str[0] = '7';
		dp[4].len = 1;		dp[4].str[0] = '4';
		dp[5].len = 1;		dp[5].str[0] = '2';
		dp[6].len = 1;		dp[6].str[0] = '6';
		dp[7].len = 1;		dp[7].str[0] = '8';
		
		Str small, big;
		
		Str zero = new Str(6, new char[101]);
		zero.str[0] = '0';
		
		for(int n=8; n<=100; n++) {
			
			nxt : for(int a=2; a<n/2+1; a++) {
				
				if(dp[a].len < 51 && dp[n-a].len < 51) {
					
					int newlen = dp[a].len + dp[n-a].len;
					
					// dp[a].str과 dp[n-a].str 중 앞으로 올 것(small)을 선택
					small = dp[a];
					big = dp[n-a];
					
					for(int i=0; i<Math.min(small.len, big.len); i++) {
						if(small.str[i] < big.str[i]) {
							break;
						}
						else if(small.str[i] > big.str[i]) {	// 이 if문 안했다가 틀림
							small = dp[n-a];
							big = dp[a];
							break;
						}
					}
					
					if(big.len == 1 && big.str[0] == '6') {		// 이거 안했다가 틀림
						big = zero;
					}
					
					if(newlen < dp[n].len) {
						int idx = 0;
						
						dp[n].len = newlen;
						
						for(int i=0; i<small.len; i++)
							dp[n].str[idx++] = small.str[i];
						
						for(int i=0; i<big.len; i++)
							dp[n].str[idx++] = big.str[i];
					}
					
					else if(newlen == dp[n].len) {
						boolean update = false;
						
						int idx = 0;
						for(int i=0; i<small.len; i++) {
							if(dp[n].str[idx] < small.str[i]) {
								continue nxt;
							}
							else if(dp[n].str[idx] > small.str[i]) {
								update = true;
								break;
							}
							idx++;
						}
						
						if(!update) {
							for(int i=0; i<big.len; i++) {
								if(dp[n].str[idx] < big.str[i]) {
									continue nxt;
								}
								else if(dp[n].str[idx] > big.str[i]) {
									update = true;
									break;
								}
								idx++;
							}
						}
						
						if(update) {
							idx = 0;
							for(int i=0; i<small.len; i++)
								dp[n].str[idx++] = small.str[i];
							
							for(int i=0; i<big.len; i++)
								dp[n].str[idx++] = big.str[i];
						}
					}
					
				}
			}
		}
		
		
		int T = Integer.parseInt(in.readLine());
		
		StringBuilder out = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			int n = Integer.parseInt(in.readLine());
			
			// min 값 기록
			for(int i=0; i<dp[n].len; i++)
				out.append(dp[n].str[i]);
			out.append(" ");
			
			// max값 기록
			if(n % 2 == 0) {
				n /= 2;
				for(int i=0; i<n; i++) {
					out.append("1");
				}
			}
			else {
				n /= 2;
				out.append("7");
				for(int i=1; i<n; i++) {
					out.append("1");
				}
			}
			out.append("\n");
			
		}
		
		System.out.print(out);
		
	}
	
	static class Str {
		int len;
		char[] str = new char[101];
		
		public Str(int l, char[] s) {
			this.len = l;
			this.str = s;
		}
	}
	
}