package com.zhangjc.mysql.utils;

public class SqlToPoUtil {

    /**
     *替换掉下划线并让紧跟它后面的字母大写
     * @param s
     * @return
     */
    public static String replaceUnderlineAndfirstToUpper(String s){
        StringBuffer sb = new StringBuffer(s.trim());
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num+1;
            if (count >= sb.length()) {
                break;
            }
            if(num!=-1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count,count+1,ia+"");
            }
        }
        String ss = sb.toString().replaceAll("_","");
        return ss.trim();
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0))) {return s; }else{
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0))){
            return s;
        }else{
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
