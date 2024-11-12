package com.softeem.controller.sys;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.softeem.model.sys.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class LoginController {

    /**
     * 访问项目根路径
     * @return
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String root(Model model) {
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();
		if (user == null) {
			return "redirect:/page/login.html";
		} else {
			return "redirect:/index.html";
		}
	}
	

    /**
     * 跳转到login页面
     * @return
     */
//    @RequestMapping(value = "/login",method = RequestMethod.GET)
//    public String login(Model model) {
//        Subject subject = SecurityUtils.getSubject();
//        User user=(User) subject.getPrincipal();
//        if (user == null){
//            return "login";
//        }else{
//            return "redirect:index";
//        }
//
//    }
    
    
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        if (user == null){
            return "/page/login.html";
        }else{
            return "redirect:/index.html";
        }

    }
    
    /**
     * 用户登录
     * @param request
     * @param username
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/tologin")
    @ResponseBody
    public String loginUser(HttpServletRequest request, String username, String password, Model model, HttpSession session) {

        //对密码进行加密
        //password=new SimpleHash("md5", password, ByteSource.Util.bytes(username.toLowerCase() + "shiro"),2).toHex();
        //如果有点击  记住我
        //UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password,remeberMe);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录操作
            subject.login(usernamePasswordToken);
            User user=(User) subject.getPrincipal();
            //更新用户登录时间，也可以在ShiroRealm里面做
            session.setAttribute("user", user);
            model.addAttribute("user",user);
            user.getUsername();
            return user.getUsername();
        } catch(Exception e) {
            //登录失败从request中获取shiro处理的异常信息 shiroLoginFailure:就是shiro异常类的全类名
            String exception = (String) request.getAttribute("shiroLoginFailure");
            model.addAttribute("msg",e.getMessage());
            //返回登录页面
            return e.getMessage();
        }
    }

    @RequestMapping("/index")
    public String index(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        if (user == null){
            return "page/login.html";
        }else{
            model.addAttribute("user",user);
            return "/index.html";
        }
    }

    /**
     * 登出  这个方法没用到,用的是shiro默认的logout
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session, Model model) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "退出登录";
    }

    /**
     * 跳转到无权限页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/unauthorized")
    public String unauthorized(HttpSession session, Model model) {
        return "unauthorized";
    }
    
    
    @RequestMapping("/index/getUserName")
    @ResponseBody
    public String getUserName() {
        Subject subject = SecurityUtils.getSubject();
        User user=(User) subject.getPrincipal();
        return user.getUsername();
    }

}
