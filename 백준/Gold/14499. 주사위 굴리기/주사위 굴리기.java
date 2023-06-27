import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder out = new StringBuilder();
		
		
		int n = Integer.parseInt(st.nextToken());	// 세로크기
		int m = Integer.parseInt(st.nextToken());	// 가로크기
		
		int x = Integer.parseInt(st.nextToken());	// x : 주사위 행
		int y = Integer.parseInt(st.nextToken());	// y : 주사위 열
		
		int k = Integer.parseInt(st.nextToken());	// k : 명령 갯수
		
		
		int[][] map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(in.readLine());
		
		int order = 0;
		int[] dice = new int[6];
		
		int[] nxt = new int[2];
		
		for(int i=0; i<k; i++) {
			order = Integer.parseInt(st.nextToken())-1;
			
			nxt[0] = x + deltas[order][0];
			nxt[1] = y + deltas[order][1];
			
			if(0<=nxt[0] && nxt[0]<n && 0<=nxt[1] && nxt[1]<m) {
//				System.out.println("방향: "+ order+" 위치: "+Arrays.toString(nxt));
//				for(int[] row : map)
//					System.out.println(Arrays.toString(row));
				
//				System.out.println("굴리기 전 주사위: "+Arrays.toString(dice));


				dice = rotateDice(dice, order);
				
				x = nxt[0];
				y = nxt[1];
				
				if(map[x][y] == 0) {
					map[x][y] = dice[5];
				}
				else {
					dice[5] = map[x][y];
					map[x][y] = 0;
				}
				
//				System.out.println("굴리기 후 주사위: "+Arrays.toString(dice));
				out.append(dice[0]+"\n");
			}
		}
		
		System.out.print(out);
	}
	
	static int[][] deltas = {{0,1}, {0,-1}, {-1,0}, {1,0}};
	
	public static int[] rotateDice(int[] dice, int order) {	
//		System.out.println(order);
		switch(order) {
			case 0:		// 동
				return new int[] {dice[3], dice[1], dice[0], dice[5], dice[4], dice[2]};
			case 1:		// 서
				return new int[] {dice[2], dice[1], dice[5], dice[0], dice[4], dice[3]};
			case 2:		// 북
				return new int[] {dice[4], dice[0], dice[2], dice[3], dice[5], dice[1]};
			case 3:
				return new int[] {dice[1], dice[5], dice[2], dice[3], dice[0], dice[4]};
			default:
				return new int[] {};
		}
	}

}