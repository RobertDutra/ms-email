package com.ms.email.controller;

import com.ms.email.dto.EmailDto;
import com.ms.email.exceptions.InvalidEmailException;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Value
@AllArgsConstructor
@RestController
@RequestMapping("/api/email")
public class EmailController {

    //@Autowired

    EmailService emailService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmailModel sendiEmail(@RequestBody @Valid EmailDto emailDto) throws InvalidEmailException {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return emailModel;
    }

//    @ResponseStatus(HttpStatus.OK) default = OK
    @GetMapping("/from")
//    @RequestMapping(method = RequestMethod.GET, value = "/from")
    public List<EmailModel>findByEmailFrom(@RequestParam("emailFrom") String emailFrom) throws InvalidEmailException {
        List<EmailModel> emails = emailService.findByEmailFrom(emailFrom);
        return emails;
    }


}
