class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("\\s","");
        s = s.replaceAll("[^a-zA-Z0-9]","");
        s = s.toLowerCase();
        for(int i = 0; i < s.length(); i++){
            int j = s.length() - i - 1;
            if(s.charAt(i) != (s.charAt(j))){
                return false;
            }
        }

        return true;
    }
}
