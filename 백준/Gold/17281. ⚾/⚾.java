import java.util.*;
import java.io.*;

/*
 * 1이닝 돌고, 초기화시켜줘야하는데 안해서 틀림
 */
public class Main {
	
	static int n, scores[][];
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(in.readLine());
		
		scores = new int[n][9];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<9; j++)
				scores[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[] seq = new int[9];		
		
		permu(0, seq, new boolean[9]);
		
		System.out.println(maxScore);
		
	}
	
	static int maxScore = 0;
	
	static void permu(int idx, int[] seq, boolean[] visited) {
		
		if(idx == 9) {
			maxScore = Math.max(maxScore, calScore(seq));
			return;
		}
		
		
		for(int num=1; num<9; num++) {	// 0번은 이미 4번타자이므로 제외
			if(!visited[num]) {
				visited[num] = true;
				
				seq[idx] = num;
				if(idx == 2) {
					permu(idx+2, seq, visited);
				}
				else {
					permu(idx+1, seq, visited);
				}
				
				visited[num] = false;
			}
		}
		
	}

	
	private static int calScore(int[] seq) {
		
		int score = 0;
		int outcnt = 0, idx=0;
		
		boolean[] occupied = new boolean[4];		// 각 루에 사람이 있는지 여부
		
		for(int round=0; round<n; round++) {
			
			while(outcnt < 3) {
				idx %= 9;
				
				if(scores[round][seq[idx]] == 0) {
					outcnt++;
				}
				else {
					score += go(scores[round][seq[idx]], occupied);
				}
				idx++;
				
			}
			
			outcnt = 0;					// 이거 안해서 틀림
			occupied = new boolean[4];	// 이거 안해서 틀림
		}
		
		return score;
	}
	
	static int go(int hitType, boolean[] occupied) {
		
		int plusScore = 0;
		
		// 타석에 타자가 존재
		occupied[0] = true;
		
		
		// hitType만큼 점수 획득
		for(int lu=3; lu>3-hitType; lu--) {
			if(occupied[lu]) {
				plusScore++;
			}
		}
		
		
		for(int lu=3; lu>=0; lu--) {
			if(lu >= hitType)
				occupied[lu] = occupied[lu-hitType];
			else
				occupied[lu] = false;
		}

		return plusScore;
	}
}