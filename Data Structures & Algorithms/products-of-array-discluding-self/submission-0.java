class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        ArrayList<Integer> num = new ArrayList<>();

        for(int n : nums){
            num.add(n);
        }
        for(int i = 0; i < len; i++){
            List<Integer> left = num.subList(0,i);
            List<Integer> right = num.subList(i+1,len);

            int l = left.stream().reduce(1, (a, b) -> a * b);
            int r = right.stream().reduce(1, (a, b) -> a * b);
            ans[i] = l*r;
        }
        return ans;
    }
}  
