class Solution {
    public boolean hasDuplicate(int[] nums) {
        ArrayList<Integer> num = new ArrayList<>();
        for(int i : nums){
            if(num.contains(i)){
                return true;
            }
            num.add(i);
        }

        return false;

    }
}