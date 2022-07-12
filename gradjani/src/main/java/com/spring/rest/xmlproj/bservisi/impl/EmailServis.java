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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.SimpleDateFormat;

@Service
public class EmailServis {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailServis(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void slanjeInteresovanja(String toEmail, String path) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setSubject("Interesovanje");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toEmail);
            helper.setFrom("euprava");
            helper.setSubject("Interesovanje");
            
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            Calendar c = Calendar.getInstance(); 
            c.setTime(date); 
            c.add(Calendar.DATE, 7);
            date = c.getTime();


            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Pozdravljamo vašu zainteresovanost za imunizaciju!\n" +
                    "Vaš termin je " + formatter.format(date)+" \n"+
                    "U prilogu se nalazi Vaše interesovanje.");

            MimeBodyPart jpgBodyPart = new MimeBodyPart();
            jpgBodyPart.attachFile(path);

            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(jpgBodyPart);

            msg.setContent(emailContent);
            javaMailSender.send(msg);


        } catch (MessagingException | IOException ex) {
            System.out.println("Greška prilikom slanja!");
        }
    }

    public void slanjeSertifikata(String toEmail, String pdfPath, String htmlPath) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setSubject("Zahtev za zeleni sertifikat");
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(toEmail);
            helper.setFrom("euprava");
            helper.setSubject("Zahtev za zeleni sertifikat");


            Multipart emailContent = new MimeMultipart();
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("Poštovani,\nVaš zahtev je odobren.\n" +
                    "U prilogu se nalazi Vaš digitalni zeleni sertifikat.");

            MimeBodyPart mailBodyPart = new MimeBodyPart();
            mailBodyPart.attachFile(pdfPath);
            MimeBodyPart mailBodyPart2 = new MimeBodyPart();
            mailBodyPart2.attachFile(htmlPath);

            emailContent.addBodyPart(textBodyPart);
            emailContent.addBodyPart(mailBodyPart);
            emailContent.addBodyPart(mailBodyPart2);

            msg.setContent(emailContent);
            javaMailSender.send(msg);


        } catch (MessagingException | IOException ex) {
            System.out.println("Greška prilikom slanja!");
        }
    }
}
