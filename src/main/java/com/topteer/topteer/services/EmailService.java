package com.topteer.topteer.services;



// using SendGrid's Java Library
// https://github.com/sendgrid/sendgrid-java
import com.sendgrid.*;
import com.topteer.topteer.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    @Value("${SENDGRID_API_KEY}")
    private String sendGridKey;


    public void sendTextEmail(User user) throws IOException {
        // the sender email should be the same as we used to Create a Single Sender Verification
        Email from = new Email("evan.b.williams95@gmail.com");
        String subject = "Thank you for volunteering!";
        Email to = new Email(user.getEmail());
        Content content = new Content("text/plain", "Your help is appreciated!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}

