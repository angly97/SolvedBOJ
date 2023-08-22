import java.util.*;

class Solution {
    public int[] solution(int n, int[] aShot) {
        
        permu(0, n, aShot, new int[11]);
        
        // 이길 수 있는 경우가 없을 경우 -> maxdiff가 갱신된 적 없으므로, maxdiff == 0
        if(maxdiff == 0)
            return new int[] {-1};
        
        return ans;
    }
    
    int maxdiff = 0;
    int[] ans = new int[11];
    
    /*
    1. idx == 11일때, 최종계산을 하고, 그전에 remain == 0되면 종료되게 해서 틀렸었음
       idx == 11되기 전에 remain == 0인거 당연히 가능함.
       idx == 11인데도 화살이 남는 경우만 아니면 됨
    2. 점수를 계산하면서 permu를 진행하면, 
       idx가 11이 되기 전에 remain == 0인 경우, 
       점수계산을 끝까지 안하고 끝나므로 최종 점수가 아니게 됨 => 그래서 틀렸었음
    */
    
    public void permu(int idx, int remain, int[] aShot, int[] rShot) {
        
        // 라이언이 화살을 다 쓴 경우, 점수 계산
        if(remain == 0) {
            
            int diff = calScoreDiff(aShot, rShot);  // 점수 계산
            if(diff == 0){                          // 동점일 때는 어피치가 이기므로 걍 종료
               return;
            }    
            else if(maxdiff < diff) {               // 최대 점수차로 갱신
                maxdiff = diff;
                for(int i=0; i<11; i++) {
                    ans[i] = rShot[i];
                }
            }
            else if(maxdiff == diff) {      // 점수차가 같은 경우, 가장 작은 점수를 많이 맞힌 경우를 채택
                
                for(int i=10; i>=0; i--) {    
                    // rShot이 더 작은 점수를 많이 맞췄으므로, ans를 rShot으로 갱신
                    if(ans[i] < rShot[i]) {
                        for(int j=0; j<11; j++) {
                            ans[j] = rShot[j];
                        }
                        break;
                    }
                    else if(ans[i] == rShot[i]) continue;
                    else                        break;      // 이거 안해서 틀림
                }                
            }
            return;
        }
        else if(idx == 11){
            return;
        }
        
        
        for(int shot = 0; shot<=remain; shot++) {
            rShot[idx] = shot;
            
            permu(idx+1, remain - shot, aShot, rShot);
            
            rShot[idx] = 0;         // 꼭 필요!
        }
    }
    
    public int calScoreDiff(int[] aShot, int[] rShot) {
        int aScore = 0;
        int rScore = 0;
        for(int i=0; i<11; i++) {
            if(aShot[i] + rShot[i] == 0)       continue;       // 이거 안해서 틀림
            else if(aShot[i] >= rShot[i])    aScore += (10-i);
            else                            rScore += (10-i);
        }
        return rScore - aScore;
    }
}