class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        for(int[]row : matrix){
            int len = row.length - 1;
            if(row[len] >= target){
                return binarySearch(row, target);
            }
        }
        return false;
        
    }
    
    public static boolean binarySearch(int[] row, int target){
        int l = 0;
        int r = row.length - 1;

        while(l<=r){
            int mid = ((r+l)/2);
            if(row[mid] > target ){
                r = mid - 1;
            }
            else if(row[mid] < target){
                l = mid + 1;
            }
            else if(row[mid] == target){
                return true;
            }
        }
        
        
        return false;

    }
}
