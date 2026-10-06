class Solution {
    public int[] twoSum(int[] nums, int target) {
        ArrayList<Integer> lst = new ArrayList<>(nums.length);
        for(int num : nums){
            int comp = target - num;
            if(lst.contains(comp)){
                return new int[] {lst.indexOf(comp), lst.size()};
            }
            lst.add(num);
        }
            
        return new int[] {};
    }
}
