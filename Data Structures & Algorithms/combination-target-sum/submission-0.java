class Solution {
    List<List<Integer>> lists;
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, target,0, list);
        return lists;
        
    }
    void dfs(int[] nums, int target, int i, List<Integer> cur){
        if(target == 0){
            lists.add(new ArrayList(cur));
            return;
        }
        
        if(target < 0 ||i >= nums.length) return;

        cur.add(nums[i]);
        dfs(nums,target-nums[i], i, cur);
        cur.remove(cur.size()-1);
        dfs(nums, target, i+1,  cur);
    }
}
