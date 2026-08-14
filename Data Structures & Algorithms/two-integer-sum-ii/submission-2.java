class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length -1 ;
        while (l<r){
            int comp = numbers[l] + numbers[r];
            if(comp > target){
                --r;
            }
            else if (comp < target){
                l++;
            }
            else if( comp == target){
                return new int[]{l+1, r+1};
            }
            
        }
        return new int []{};
    }
}