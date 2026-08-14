class Solution {
    public boolean isPalindrome(String s) {
        String str = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        char[] ans = str.toCharArray();
        int g = ans.length - 1;

        for(int i = 0; i< ans.length; i++){
            if(ans[i] != ans[g-i]){
                return false;
            }
        }
        return true;
    }
}
