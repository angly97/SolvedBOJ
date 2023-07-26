import java.util.*;
import java.io.*;

class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        StringTokenizer st;
        
        Map<String, int[]> in = new HashMap<>();              // 차량이 들어온 시각
        Map<String, Integer> accTime = new TreeMap<>();       // 차량의 누적 주차 시간
        
        String num, flag;
        
        for(String record : records) {
            st = new StringTokenizer(record);    
            
            // "HH:MM" 문자열을 [HH, MM] 배열로 파싱
            int[] time = Arrays.stream(st.nextToken().split(":"))
                        .mapToInt(Integer::parseInt).toArray();
            
            // 차 번호
            num = st.nextToken();    
            
            // IN, OUT
            flag = st.nextToken();
            
            // 차량 주차 시작 처리
            if(flag.equals("IN")) {
                in.put(num, time);
            }
            // 차량 나감; 누적 주차 시간 갱신
            else {
                // 기존에 누적 기록이 있으면, 기존 시간에 추가
                if(accTime.containsKey(num)) {
                    accTime.put(num, accTime.get(num) + calTime(in.get(num), time));
                }
                // 기존에 누적 기록이 없으면, 새로 시간 등록
                else {
                    accTime.put(num, calTime(in.get(num), time));
                }
                
                // IN에서 출차된 차는 제거
                in.remove(num);
            }
        }
        
        // 23:59에 나간 차들 누적 주차 시간 갱신
        int[] closingTime = {23, 59};
        for(String carNum : in.keySet()) {
            
            // 기존에 누적 기록이 있으면, 기존 시간에 추가
            if(accTime.containsKey(carNum)) {
                accTime.put(carNum, accTime.get(carNum) + calTime(in.get(carNum), closingTime));
            }
            // 기존에 누적 기록이 없으면, 새로 시간 등록
            else {
                accTime.put(carNum, calTime(in.get(carNum), closingTime));
            }
        }
        
        // 요금 계산
        int idx = 0;
        int[] finalCost = new int[accTime.size()];
        for(String carNum : accTime.keySet()) {
            
            int nowAccTime = accTime.get(carNum);
            
            finalCost[idx] = fees[1];     // 기본요금
            
            // 누적시간이 기본요금보다 클 경우, 단위시간만큼 단위요금 추가
            if(nowAccTime > fees[0]) {
                finalCost[idx] += fees[3] * Math.ceil((nowAccTime - fees[0])/(double)fees[2]);
            }
            
            idx++;
        }
        
        return finalCost;
    }
    
    // in 시간과 out 시간의 차이 계산
    public int calTime(int[] in, int[] out) {
        
        // 분 차이 계산
        int diffTime = out[1]-in[1];
        
        // 시간 차이 계산
        if(diffTime < 0) {
            diffTime += 60;
            diffTime += (out[0]-in[0]-1) * 60;
        }
        else {
            diffTime += (out[0]-in[0]) * 60;
        }
        
        return diffTime;
    }
    
}