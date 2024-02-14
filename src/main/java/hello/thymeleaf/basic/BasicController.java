package hello.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-basic.html";
    }
    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped.html";
    }
    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> userList = new ArrayList<>();
        userList.add(userA);
        userList.add(userB);

        Map<String,User> userMap = new HashMap<>();
        userMap.put("userA",userA);
        userMap.put("userB",userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", userList);
        model.addAttribute("userMap", userMap);

        return "basic/variable";
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1","data1");
        model.addAttribute("param2","data2");

        return "basic/link";
    }
    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request",request);
        model.addAttribute("response",response);
        model.addAttribute("servletContext",request.getServletContext());

        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean{
        public String hello(String str){
            return "빈에 접근했다 " + str;
        }
    }
    @Data
    static class User{
        private String username;
        private  int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

}


