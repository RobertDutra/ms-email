package com.ms.email.services;

import com.ms.email.enums.StatusEmail;
import com.ms.email.exceptions.InvalidEmailException;
import com.ms.email.models.EmailModel;
import com.ms.email.repositories.EmailRepository;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Value
@Service
public class EmailService {

    EmailRepository emailRepository;

    JavaMailSender emailSender;

    public EmailModel sendEmail(EmailModel emailModel) throws InvalidEmailException {
        validaEmail(emailModel.getEmailFrom());
        validaEmail(emailModel.getEmailTo());
        emailModel.setSendDateEmail(LocalDateTime.now());
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
            emailSender.send(message);
            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

    public List<EmailModel> findByEmailFrom(String emailFrom) throws InvalidEmailException {
        validaEmail(emailFrom);
        return emailRepository.findByEmailFromOrderByEmailTo(emailFrom);
    }

    private void validaEmail(String email) throws InvalidEmailException {
        if (!email.contains("@")){
            throw new InvalidEmailException(email);
        }
    }
}
