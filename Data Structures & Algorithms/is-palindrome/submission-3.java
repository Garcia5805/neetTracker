class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("\\s", "").toLowerCase();
        s = s.replaceAll(
          "[^a-zA-Z0-9]", "");
        char[] sChar = s.toCharArray();

        int j = sChar.length-1;
        for(int i=0;i<s.length();i++){
            if(sChar[i] != sChar[j]){return false;}
            --j;
        }

        return true;
    }
}
