package com.email.controller;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmailController {

    @Autowired
    private EmailService emailService;


    @RequestMapping("/welcome")
    public  String welcome(){
        return  "Hello";
    }
    //api to send email
    @RequestMapping(value = "/sendemail",method = RequestMethod.POST)
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request)
    {
        boolean result=this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo());
        if (result)
        {
            return ResponseEntity.ok(new EmailResponse("Email Sent Successfully...."));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email Not Send"));
        }
    }

}
