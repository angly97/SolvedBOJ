import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
		StringTokenizer st;
		
		int n = Integer.parseInt(in.readLine());
		
		char[][] map = new char[n][n];
		List<int[]> ts = new ArrayList<>();
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'T') {
					ts.add(new int[] {i, j});
				}
			}
		}
		
		int[] nxt = new int[2];
		
		int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
		
		String ans = "NO";
		
		out: for(int i1=0; i1<n; i1++) {
			for(int j1=0; j1<n; j1++) {

				if(map[i1][j1] != 'X')	continue;

				map[i1][j1] = 'O';
				
				for(int i2=0; i2<n; i2++) {
					for(int j2=0; j2<n; j2++) {

						if(map[i2][j2] != 'X' || (i1 == i2 && j1 == j2))	continue;
						
						map[i2][j2] = 'O';
								
						for(int i3=0; i3<n; i3++) {
							 for(int j3=0; j3<n; j3++) {

								if(map[i3][j3] != 'X' || (i3 == i2 && j3 == j2) || (i3 == i1 && j3 == j1))	continue;
								
								map[i3][j3] = 'O';
								
								boolean ok = true;
								
								t: for(int[] t : ts) {
									for(int[] del : deltas) {

										nxt[0] = t[0] + del[0];
										nxt[1] = t[1] + del[1];
										
										while(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<n) {
											if(map[nxt[0]][nxt[1]] == 'S') {
												ok = false;
												break t;
											}
											else if(map[nxt[0]][nxt[1]] == 'O') {
												break;
											}

											nxt[0] += del[0];
											nxt[1] += del[1];
										}
									
									}
									
								}
								
								if(ok) {
									ans = "YES";
									break out;
								}
								map[i3][j3] = 'X';
							}
						}
						
						map[i2][j2] = 'X';
						
					}
				}
				
				map[i1][j1] = 'X';
			}
		}
		
		System.out.println(ans);
	}
	
}