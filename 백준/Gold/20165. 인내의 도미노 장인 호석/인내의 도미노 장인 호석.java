import java.io.*;
import java.util.*;


public class Main {
	
	static int n, m, R, map[][];
	static char[][] status;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		status = new char[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
			
			Arrays.fill(status[i], 'S');
		}
		
		
		int score = 0;

		char ad;
		int ar, ac;
		int dr, dc;
		
		for(int round=0; round<R; round++) {
			
			st = new StringTokenizer(in.readLine());
			ar = Integer.parseInt(st.nextToken())-1;
			ac = Integer.parseInt(st.nextToken())-1;
			ad = st.nextToken().charAt(0);
//			System.out.println(ar+" "+ac+" "+ad);
			
			if(status[ar][ac] == 'S') {
				switch(ad) {
					case 'E':
						score += attack(ar, ac, 0);
						break;
					case 'W':
						score += attack(ar, ac, 1);
						break;
					case 'S':
						score += attack(ar, ac, 2);
						break;
					case 'N':
						score += attack(ar, ac, 3);
						break;
				}
			}
			
			st = new StringTokenizer(in.readLine());
			dr = Integer.parseInt(st.nextToken())-1;
			dc = Integer.parseInt(st.nextToken())-1;
			
			if(status[dr][dc] == 'F') {
				status[dr][dc] = 'S';
			}
		}
		
		
		StringBuilder out = new StringBuilder();
		
		out.append(score+"\n");
		
		for(char[] row : status) {
			for(char col : row)
				out.append(col+" ");
			out.append("\n");
		}
		
		System.out.print(out);
		
	}
	
	static int[][] deltas = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	static int attack(int r, int c, int d) {
		
		status[r][c] = 'F';
		
		int score = 0;
		
		int[] nxt = {r + deltas[d][0], c + deltas[d][1]};
//		System.out.println(r+" "+c+" "+d);
		
		for(int i=1; i<map[r][c]; i++) {
			if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<m) {
				if(status[nxt[0]][nxt[1]] == 'S')
					score += attack(nxt[0], nxt[1], d);
				nxt[0] += deltas[d][0];
				nxt[1] += deltas[d][1];
			}
		}

//		System.out.println(r+" "+c+" "+d+" score : "+score);
//		for(char[] row : status) 
//			System.out.println(Arrays.toString(row));
//		System.out.println();
		return score+1;
	}
	
}