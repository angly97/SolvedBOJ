import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
        Map<String, Integer> pidx = new HashMap<>();
        for(int i=0, size = players.length; i<size; i++)
            pidx.put(players[i], i);
        
        
        String tempstr;
        int pos, temppos;
        
        for(String call : callings) {
            pos = pidx.get(call);
            // System.out.println(pos+" "+Arrays.toString(players));
            
            tempstr = players[pos];
            players[pos] = players[pos-1];
            players[pos-1] = tempstr;
            
            pidx.put(call, pos-1);
            pidx.put(players[pos], pos);
        }
        
        return players;
    }
}