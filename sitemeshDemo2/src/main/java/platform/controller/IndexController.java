package platform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ke.gong
 * @date 2017/2/2
 */
@Controller
@RequestMapping("")
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/platform/index.jsp";
    }

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String loginAndAuth(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        return "securityLogin";
    }

}
