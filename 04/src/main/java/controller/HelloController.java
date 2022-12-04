package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * todo daleksandrova
 *
 * @author Darya Alexandrova
 * @since 2022.11.20
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String getHelloWorld(Model model) {
        model.addAttribute("message", "hello!");
        return "hello";
    }

    @GetMapping("/hellojsp")
    public String getHelloWorldJsp(Model model) {
        model.addAttribute("message", "hello!");
        return "hellojsp";
    }

    @GetMapping("/ff")
    @ResponseBody
    public ResponseEntity<String> getFf() {
        return new ResponseEntity<>("ff",HttpStatus.OK);
    }
}
