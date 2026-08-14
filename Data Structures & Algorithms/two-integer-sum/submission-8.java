class Solution {
    public int[] twoSum(int[] nums, int target) {
        ArrayList<Integer> num = new ArrayList<>();
        for(int a : nums){num.add(a);}

        for(int i = 0; i < nums.length-1;i++){
            int comp = target - nums[i];
            num.remove(0);
            if(num.contains(comp)){
                int j=num.indexOf(comp);
                return new int[]{i, j+(i+1)}; 
            }

        }
        return nums;
    }

}