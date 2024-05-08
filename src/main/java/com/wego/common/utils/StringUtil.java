package com.wego.common.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author hc
 */
public class StringUtil {
    private static Pattern compile = Pattern.compile("_(\\w)");

    /**
     * 查找字符串str中包含的字符串s的个数
     * 通过indexOf()寻找指定字符串，截取指定字符串后面的部分，再次寻找，直到找完所有
     * @param str
     * @param s
     * @return
     */
    public static int countString(String str, String s) {
        int count = 0;
        while (str.indexOf(s) != -1) {
            str = str.substring(str.indexOf(s) + 1);
            count++;
        }
        return count;
    }

    /**
     * 判断参数是否是JSON字符串
     * @param str
     * @return
     */
    public static boolean isJsonType(String str) {
        boolean result = false;
        if (str != null && str.length() > 0) {
            str = str.trim();
            if (str.startsWith("{") && str.endsWith("}")) {
                result = true;
            } else if (str.startsWith("[") && str.endsWith("]")) {
                result = true;
            }
        }
        return result;
    }

    /**
     * null、“”都返回true
     * 只要有任意一个字符（包括空白字符）就不为空
     *
     * @param cs
     * @return
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * null、“”、空格都返回true
     * 判断字符串是否为空字符串，全部空白字符也为空。
     *
     * @param cs
     * @return
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                // 只要有一个字符不为空白字符就返回 false
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    /**
     * 将字符串中的大写字母转换成小写字母
     *
     * @param str
     * @return
     */
    public static String toLower(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char pos = str.charAt(i);
            if ('A' <= pos && (pos <= 'Z')) {
                //注意这里要将转换结果强转为char类型
                result += (char) (pos + ('a' - 'A'));
            } else {
                result += pos;
            }
        }
        return result;
    }

    /**
     * 首字母转小写
     *
     * @param str
     * @return
     */
    public static String first2Lower(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     *
     * @param str
     * @return
     */
    public static String first2Upper(String str) {
        if (Character.isUpperCase(str.charAt(0))) {
            return str;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }
    }

    /**
     * 将驼峰式命名转换成下划线命名
     *
     * @param str
     * @return
     */
    public static String hump2underscore(String str) {
        String lowerCase = str.replaceAll("[A-Z]", "_$0").toLowerCase();
        if (lowerCase.startsWith("_")) {
            lowerCase = lowerCase.substring(1);
        }
        return lowerCase;
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String underline2hump(String str) {
        Matcher matcher = compile.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 字符串转int类型
     *
     * @param str
     * @return
     */
    public static int str2Int(String str) {
        if (!isBlank(str)) {
            return Integer.valueOf(str);
        }
        return -1;
    }

    /**
     * 字符串转int类型
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return
     */
    public static int str2Int(String str, String defaultValue) {
        if (isBlank(str)) {
            str = defaultValue;
        }
        return Integer.valueOf(str);
    }

    /**
     * 字符串转long类型
     *
     * @param str
     * @return
     */
    public static long str2Long(String str) {
        if (!isBlank(str)) {
            return Long.valueOf(str);
        }
        return -1;
    }

    /**
     * 字符串转long类型
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return
     */
    public static long str2Long(String str, String defaultValue) {
        if (isBlank(str)) {
            str = defaultValue;
        }
        return Long.valueOf(str);
    }

    /**
     * 判断字符串是否包含中文字符
     *
     * @param str          字符串
     * @return
     */
    public static boolean hasChinese(String str) {
        String regexChinese = "[\u4e00-\u9fa5]+";
        Pattern patternChinese = Pattern.compile(regexChinese);
        return patternChinese.matcher(str).find();
    }

    /**
     * 判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        boolean res = Pattern.matches("\\d+", str);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(isNumber(""));
        System.out.println(isNumber("123456"));
        System.out.println(isNumber("fdsa123456"));
        System.out.println(isNumber("123456fds"));
        System.out.println(isNumber("fdasfsa"));
    }

    /**
     * InputStream转换为字符串
     */
    public String inputStream2String(InputStream is) throws Exception {  //字节流转为字符流
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        reader.close();
        return buffer.toString();
    }

    /**
     * 字符串转换为InputStream
     */
    public InputStream string2InputStream(String str) {
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }
}
