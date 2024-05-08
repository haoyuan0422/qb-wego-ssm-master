package com.wego.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * html标签清洗工具类
 *
 * @Author quwei
 * @Date 2022-09-14 14:33
 */

public class HtmlUtil {

    private static final String regEx_table = "(?!</?(table|th|tr|td|p|h|br).*?>)<.*?>";//定义正则除表table、th、tr、td外的所有标签

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";// 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";// 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
    private static final String regEx_w = "]*?>[\\s\\S]*?<\\/w[^>]*?>";//定义所有w标签


    /**
     * @param htmlStr
     * @return 删除Html标签
     * @author quwei
     */

    public static String delHTMLTag(String htmlStr) {

        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll(""); // 过滤script标签

        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);

        htmlStr = m_script.replaceAll(""); // 过滤script标签
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll(""); // 过滤style标签

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll(""); // 过滤html标签

        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
        htmlStr = htmlStr.replaceAll(" ", ""); //过滤

        return htmlStr.trim(); // 返回文本字符串
    }


    /**
     * 过滤HTML里去除表(table、th、tr、td)外的所有标签
     *
     * @param content
     * @return
     */
    public static String filterHtmlLabel(String content) {
        try {
            if (StringUtils.isNotEmpty(content)) {
                System.out.println("html过滤前: {}" + content);
                Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
                Matcher m_script = p_script.matcher(content);

                content = m_script.replaceAll(""); // 过滤script标签
                Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
                Matcher m_style = p_style.matcher(content);
                content = m_style.replaceAll(""); // 过滤style标签
                Pattern pattern = Pattern.compile(regEx_table, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(content);
                content = matcher.replaceAll("");
                System.out.println("html过滤后: {}" + content);
                return content.trim();
            }
        } catch (Exception e) {
            System.out.println("html标签过滤异常: {}" + e.getMessage());
        }
        return content;
    }
}