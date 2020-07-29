package cn.dun.web.admin;

import cn.dun.bean.User;
import cn.dun.service.BlogService;
import cn.dun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    @GetMapping()
    public String loginPage() {

        return "admin/login";
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session
                        , RedirectAttributes attributes) {
        User user=userService.checkUser(username,password );
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user );
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "admin/_fragments :: newblogList";
    }

}
