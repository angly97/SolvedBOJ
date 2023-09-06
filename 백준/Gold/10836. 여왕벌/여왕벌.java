import java.util.*;
import java.io.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[m][m];
		
		
		for(int day=0; day<n; day++) {
			st = new StringTokenizer(in.readLine());

			int acc = 0;
			
			for(int num=0; num <= 2; num++) {
				int cnt = Integer.parseInt(st.nextToken());	// num만큼 자라는게 몇개 있는지
				
				for(int c=0; c<cnt; c++) {
					if(acc + c < m) {		// 가장 왼쪽 열의 애벌레 크기 증가
						arr[m-1-acc-c][0] += num;
					}
					else {					// 가장 윗행의 애벌레 크기 증가
						arr[0][acc+c-m+1] += num;
					}
				}
				
				acc += cnt;
			}
			
			
		}
		
		// 가장 윗 행의 값을 아래로 쭈욱 복사
		for(int r=1; r<m; r++) {
			for(int c=1; c<m; c++) {
				arr[r][c] = arr[0][c];
			}
		}
		
		
		// 출력
		StringBuilder out = new StringBuilder();
		for(int r=0; r<m; r++) {
			for(int c=0; c<m; c++) {
				out.append((arr[r][c]+1)+" ");
			}
			out.append("\n");
		}
		
		System.out.print(out);
	}
}