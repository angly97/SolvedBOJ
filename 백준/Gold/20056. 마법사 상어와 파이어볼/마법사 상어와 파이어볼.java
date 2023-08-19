import java.util.*;
import java.io.*;

/*
 * 한번에 이동하는 거리를 n으로 모듈러한 것을 받아서 틀림.
 * 앞으로 처음 받을때부터 모듈러로 받지 말자!!
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		
		// fires : 파이어볼 큐
		Queue<int[]> fires = new ArrayDeque<>();
		
		// fireQueues[i][j] : (i, j)에 있는 파이어볼들을 큐에 담고 있음
		Queue<int[]>[][] fireQueues = new ArrayDeque[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++)
				fireQueues[i][j] = new ArrayDeque<>();
		}
		
		int r, c, M, s, d;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(in.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			M = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			fires.add(new int[] {r, c, M, s, d});
		}
		
		
		int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
		
		
		int nxtr, nxtc, nowFire[];
		int sumM, sumS, sumD, fireCnt;
		int renewM, renewS;
		Queue<int[]> fireQ;

		int size;
		
		// k번 이동 명령
		for(int round=0; round<k; round++) {
			
			// 1. 파이어볼 이동
//			System.out.println("-------------------------");
//			System.out.println("불 이동");
			
			while(!fires.isEmpty()) {
				nowFire = fires.poll();
//				System.out.print("("+nowFire[0]+", "+nowFire[1]+") --> ");
				
				nxtr = (nowFire[0] + (nowFire[3]*deltas[nowFire[4]][0])% n + n)%n;
				nxtc = (nowFire[1] + (nowFire[3]*deltas[nowFire[4]][1])% n + n)%n;
				
				fireQueues[nxtr][nxtc].offer(new int[] {nowFire[2], nowFire[3], nowFire[4]});
//				System.out.println("("+nxtr+", "+nxtc+")");
			}


//			System.out.println("2개 이상인 칸 4개의 불로 분리");
			// 파이어볼 2개 이상인 칸은 4개로 분리됨
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(fireQueues[i][j].size() == 1) {
						nowFire = fireQueues[i][j].poll();
						fires.offer(new int[] {i, j, nowFire[0], nowFire[1], nowFire[2]});
					}
					else if(fireQueues[i][j].size() >= 2) {
						
						fireQ = fireQueues[i][j];		// (i, j)에 있는 파이어볼 큐
						fireCnt = fireQ.size();			// (i, j)에 있는 파이어볼 갯수

//						System.out.println("("+i+", "+j+") 칸의 불 갯수: "+fireCnt);
						
						// 현재 칸의 질량합, 속력합, 방향%2합 초기화
						sumM = sumS = sumD = 0;
						
						// fireQ에 있는 파이어볼들의 질량 합, 속력 합, 방향%2의 합 계산
						while(!fireQ.isEmpty()) {
							nowFire = fireQ.poll();
//							System.out.println(Arrays.toString(nowFire));

							sumM += nowFire[0];
							sumS += nowFire[1];
							sumD += nowFire[2]%2;
						}
						
//						System.out.println("("+i+", "+j+") 칸의 질량,속력,방향 합: "+sumM+" "+sumS+" "+sumD);

						
						// 4개로 나뉠 파이어볼들의 질량과 속력 갱신
						renewM = sumM/5;
						if(renewM == 0) continue;
						
						renewS = sumS/fireCnt;
						
						
						// fireQ에 있는 파이어볼들의 방향이 모두 짝수거나 모두 홀수
						if(sumD == 0 || sumD == fireCnt) {
							
							// 4개로 나뉜 파이어볼의 방향들이 각각 0 2 4 6
							for(int renewD=0; renewD<=6; renewD+=2) {
								
								// 1번 연산에 쓰일 파이어볼 큐에 담기
								fires.offer(new int[] {i, j, renewM, renewS, renewD});
							}
						}
						else {
							// 4개로 나뉜 파이어볼의 방향들이 각각 1 3 5 7
							for(int renewD=1; renewD<=7; renewD+=2) {
								
								// 1번 연산에 쓰일 파이어볼 큐에 담기
								fires.offer(new int[] {i, j, renewM, renewS, renewD});
							}
						}

//						System.out.println("현재 칸 불의 갯수: "+fireQ.size());
//						System.out.println("현재 칸 불이 나뉜 결과 불의 갯수: "+fires.size());
					}
				}
			}
			
			
//			System.out.println("연산 결과 ");
//			for(int i=0; i<n; i++)
//				for(int j=0; j<n; j++) {
//					size=  fireQueues[i][j].size();
//					if(size > 0)
//						System.out.println(i+" "+j);
//					while(size-->0) {
//						nowFire = fireQueues[i][j].poll();
//						System.out.println(Arrays.toString(nowFire));
//						fireQueues[i][j].offer(nowFire);
//					}
//				}
		}
		

		int ans = 0;

//		System.out.println("남은 질량 구하기");
		
		// 남은 질량 구하기
		while(!fires.isEmpty()) {
			nowFire = fires.poll();
//			System.out.println(Arrays.toString(nowFire));
			ans += nowFire[2];
		}
		
		System.out.println(ans);
	}
}