package poly.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
    private String from;
    private String to;
    private String subject;
    private String body;
    private String cc;
    private String bcc;

    public MailInfo(String to, String subject, String body) {
        this.from = "your_email@gmail.com";
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
