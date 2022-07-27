package com.hamitmizrak.ui.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientData {

    //http://localhost:8080/client/api
    @GetMapping("/client/api")
    @ResponseBody
    public String getDataValue(Model model){
       String URL="http://localhost:8080/api/v1/rest/object9/44";
        RestTemplate restTemplate=new RestTemplate();
        String jsonData=restTemplate.getForObject(URL,String.class);
model.addAttribute("key",jsonData);
        return jsonData;
    }
}
