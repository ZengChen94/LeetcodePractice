/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        if(root == null) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        List<Integer> res = new LinkedList<>();
        for(Map.Entry<Integer, Integer> me: map.entrySet()){
            if(me.getValue() == max) res.add(me.getKey());
        }
        int[] res2 = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            res2[i] = res.get(i);
        }
        return res2;
    }
    
    private int helper(TreeNode n, Map<Integer, Integer> map){
        int left = (n.left==null) ? 0 : helper(n.left, map);
        int right = (n.right==null) ? 0 : helper(n.right, map);
        int sum = left + right + n.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        max = Math.max(max, map.get(sum));
        return sum;
    }
}