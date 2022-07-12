package com.spring.rest.xmlproj.bservisi.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.SimpleDateFormat;

@Service
public class EmailServis {
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;


    public EmailServis(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void odbijenZahtev(String toEmail, String razlog){
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setSubject("Zahtev za zeleni sertifikat");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toEmail);
            helper.setFrom("euprava");
            helper.setSubject("Zahtev za zeleni sertifikat");

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, 7);
            date = c.getTime();


            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Poštovani,\n" +
                    "Vaš zahtev za zeleni sertifikat je odbijen sa sledećim obrazloženjem:\n"+
                    razlog);

            emailContent.addBodyPart(textBodyPart);

            msg.setContent(emailContent);
            javaMailSender.send(msg);


        } catch (MessagingException ex) {
            System.out.println("Greška prilikom slanja!");
        }
    }
}
