import java.util.*;

class Solution {
    
    List<int[]> mCnts = new ArrayList<>();
    
    public int solution(int k, int n, int[][] reqs) {
        
        permu(1, k, new int[k+1], n);
        
        int ans = Integer.MAX_VALUE;
        
        for(int[] mCnt : mCnts) {
            
            // mStatus[0] : 1번째 타입인 멘토들의 상담 종료 시간 {50, 90}
            List<Integer>[] mEndTimes = new ArrayList[k+1];
            
            for(int i=1; i<=k; i++) {
                // 현재 타입에 속하는 멘토 수만큼 시간 삽입(초기에는 0만 삽입)
                mEndTimes[i] = new ArrayList<>();
                for(int cnt=0; cnt<mCnt[i]; cnt++)
                    mEndTimes[i].add(0);
            }
            
            
            int wait = 0;       // 대기 시간 총합
            
            // req[0]: 요청 시간, req[1]: 상담 시간, req[2]: 유형
            for(int[] req : reqs) {
                
                /* 종료 시각이 현재 요청 시간보다 이전이면서, 그 중 가장 빠른 멘토 찾기 */
               
                // 선택된 멘토의 인덱스와 종료시간 for 대기시간 없을 경우를 위한 배열
                int[] selectedM = {-1, Integer.MAX_VALUE};     
                
                // 가장 빨리 상담이 끝나는 멘토의 인덱스와 종료시간 for 대기가 필요할 경우를 위한 배열
                int[] fastM = {-1, Integer.MAX_VALUE};       
                
                // 현재 유형에 속한 멘토 수만큼 반복
                for(int i=0; i<mCnt[req[2]]; i++) { 
                    
                    // 현재 유형의 i번째 멘토의 상담 종료 시간
                    int mEndTime = mEndTimes[req[2]].get(i);
                    
                    // 상담 종료 시각이 요청 시각보다 이전이면서, 그 중 가장 빠른 시각인 멘토
                    if(mEndTime <= req[0] && mEndTime < selectedM[1]) {
                        selectedM[0] = i;               // 선택될 멘토 갱신
                        selectedM[1] = req[0] + req[1]; // 선택될 멘토의 상담 종료 시각 갱신
                    }
                    
                    if(mEndTime < fastM[1]) {
                        fastM[0] = i;               // 가장 빨리 끝나는 멘토 갱신
                        fastM[1] = mEndTime;        // 가장 빨리 끝나는 멘토의 상담 종료 시각 갱신
                    }
                }
                
                // 최종 선택된 멘토가 있다면, 그 멘토의 상담 종료 시각 갱신
                if(selectedM[0] != -1)
                    mEndTimes[req[2]].set(selectedM[0], selectedM[1]);
                
                // 대기가 필요할 경우, 가장 빨리 끝나는 멘토를 기다림
                else {
                    wait += fastM[1] - req[0];
                    mEndTimes[req[2]].set(fastM[0], fastM[1] + req[1]);
                }
            }
            
            ans = Math.min(ans, wait);
        }
        
        return ans;
    }
    
    // mCnt[i] : 타입i의 멘토 숫자
    public void permu(int type, int k, int[] mCnt, int remain) {
        if(type == k) {
            mCnt[type] = remain;
            for(int i=1; i<=k; i++) {
                if(mCnt[i] == 0)    return;
            }
            mCnts.add(mCnt.clone());
            return;
        }
        else if(remain <= 0) {
            return;
        }
        
        for(int cnt = 1; cnt<=remain; cnt++) {
            mCnt[type] = cnt;
            permu(type+1, k, mCnt, remain-cnt);
        }
    }
}