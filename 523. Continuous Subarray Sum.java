//keeping track of the running sum mod k of the sum

public class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) 
                sum %= k; 
            Integer prev = map.get(sum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else 
                map.put(sum, i);
        }
        return false;
    }
}