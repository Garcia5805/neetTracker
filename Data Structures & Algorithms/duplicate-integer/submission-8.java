class Solution {
    public boolean hasDuplicate(int[] nums) {
        HashSet<Integer> cmp = new HashSet<>();
        for(int i : nums){
            if(!cmp.contains(i)){
                cmp.add(i);
            }
            else{
                return true;
            }
        }
        return false;
    }
}