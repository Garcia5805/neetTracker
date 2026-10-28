class Solution {
    List<List<Integer>> lists;
    List<Integer> list;
    public List<List<Integer>> subsets(int[] nums) {
        lists = new ArrayList<>();
        list = new ArrayList<>();

        dfs(nums, 0);
        return lists;
        
    }
    private void dfs(int[] nums, int i){
        if(i >= nums.length){
            lists.add(new ArrayList<>(list));
            return;
        }
        list.add(nums[i]);
        dfs(nums, i+1);
        list.remove(list.size()-1);
        dfs(nums, i+1);

    }
}
