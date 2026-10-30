class Solution {
    List<List<Integer>> lists;
    List<Integer> list;
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        lists = new ArrayList<>();
        List<Integer> list = new ArrayList<>();

        dfs(nums, target, 0, list);

        return lists;
    }

    private void dfs(int[] nums, int target, int i, List<Integer> list){
        if(i >= nums.length || target < 0)  return;
        if(target == 0){
            lists.add(new ArrayList<>(list));
            return;
        }
    

        list.add(nums[i]);

        dfs(nums, target - nums[i], i, list);

        list.remove(list.size()-1);

        dfs(nums, target, i+1,list );
    }

}
