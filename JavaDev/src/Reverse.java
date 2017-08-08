public class Reverse {

    public String reverse(String str) {
       if(str.length()==1)
         return str;
       else
         return str.substring(str.length()-1) +
           reverse(str.substring(0,str.length()-1));
    }
}