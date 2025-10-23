package poly.edu.service;

import jakarta.mail.MessagingException;
import lombok.Builder;
import lombok.Data;
import lombok.Builder.Default;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

public interface MailService {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mail {
        @Default
        private String from = "WebShop <web-shop@gmail.com>";
        private String to, cc, bcc, subject, body, filenames;
    }

    void send(Mail mail) throws MessagingException;

    default void send(String to, String subject, String body) throws MessagingException {
        Mail mail = Mail.builder().to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    void push(Mail mail);

    default void push(String to, String subject, String body) {
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}
