
package com.wego.controller.manager;
//        import com.community.mapper.UserMapper;
import com.wego.entity.domain.Article;
//import com.community.pojo.User;
//import com.community.service.UserService;
import com.wego.common.utils.HtmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;


@Controller
@RequestMapping("/manager/article")
public class ArticleController {

//    @RequestMapping("Word")

    @RequestMapping("publish")
    public String input(Article article,HttpSession session , HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //在获取流对象之前告诉浏览器使用什么字符集
        resp.setCharacterEncoding("utf-8");
        //告诉浏览器，服务器发送的消息体的数据的编码
        resp.setHeader("content-type","textml;charset=utf-8");
//        if(article.getArticleContent()==null||article.getArticleTitle()==null||HtmlUtil.filterHtmlLabel1(HtmlUtil.delHTMLTag(article.getArticleContent()))==""||article.getArticleTitle()==""){
//            resp.getWriter().write("请输入文章标题或内容");
//            return;
//        }
        String ArticleContent=HtmlUtil.filterHtmlLabel(article.getArticleContent());
        article.setArticleContent(ArticleContent);
////        articleService.insertPassage(article.getArticleUserId(),article.getArticleTitle(),article.getArticleContent());
////        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        Date date = new Date(System.currentTimeMillis());
////        System.out.println(formatter.format(date));
        resp.getWriter().write("发布成功");
        return "manager/publishArticle";
    }
}


