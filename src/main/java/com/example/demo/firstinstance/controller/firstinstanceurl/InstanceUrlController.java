package com.example.demo.firstinstance.controller.firstinstanceurl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InstanceUrlController {

    
    @GetMapping("/admin")  //url이 /admin인 경우, 관리자 페이지로 이동.
    public String index(){
        //firstInstance index의 처음 위치.
        return "firstinstance/index";
    }
     
	@GetMapping("/login")
	public String login() {
		return "welcome/login";
	}

    @GetMapping("/administer/instanceurl")
    public String index3(){
        return "firstinstance/index";
    }

   
    @GetMapping("/")        //url이 /인 경우, welcome페이지로 이동.
    public String index2(){

        return "welcome/index";
    }

}