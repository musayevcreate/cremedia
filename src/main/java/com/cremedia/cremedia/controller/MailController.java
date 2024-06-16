package com.cremedia.cremedia.controller;

import com.cremedia.cremedia.service.MailSender158;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {

    private final MailSender158 mailSender;

    @PostMapping("/send")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        mailSender.sendMail(to, subject, text);
        return "Mail sent successfully";
    }
}
