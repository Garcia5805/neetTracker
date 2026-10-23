class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int l = 0;
        int r = matrix.length-1;
        int size = matrix[0].length-1;

        while(l<=r){
            int m = (l+r)/2;
            if(matrix[m][0] <= target && matrix[m][size] >= target){
                return biSearch(matrix[m], target);
            }
            else if(matrix[m][0] > target){
                r = m-1;
            }
            else{
                l = m+1;
            }
        }
        return false;
    }
    public boolean biSearch(int[] list, int target){
        int l = 0;
        int r = list.length-1;
        while(l <= r){
            int m = (l+r)/2;
            if(list[m] == target) return true;
            else if(list[m] >= target) r = m-1;
            else l = m+1;
        }
        return false;
    }
}
