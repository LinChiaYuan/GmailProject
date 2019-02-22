/**
 *
 *  author  :   Chia Yuan Lin (林家源)
 *
 *  email   :   lo919201@gmail.com
 *
 * **/
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class GmailProject
{
    public static void main(String[] args)
    {
        String[] to = {"xxxxxx@gmail.com"};   // many addresses

        String mail_host = "smtp.gmail.com";
        String mail_port = "587";
        String mail_from = "xxxxxx@gmail.com";
        String mail_pwd = "yyyyyy";

        String subject = "mail tst";
        String content = "i see you?";

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", mail_host);
        props.put("mail.smtp.user", mail_from);
        props.put("mail.smtp.password", mail_pwd);
        props.put("mail.smtp.port", mail_port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust","*");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try
        {
            message.setFrom(new InternetAddress(mail_from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            for (int i = 0; i < to.length; i++)
                toAddress[i] = new InternetAddress(to[i]);

            for (int i = 0; i < toAddress.length; i++)
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);

            message.setSubject(subject);
            message.setText(content);
            Transport transport = session.getTransport("smtp");
            transport.connect(mail_host, mail_from, mail_pwd);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae)
        {
            ae.printStackTrace();
        }
        catch (MessagingException me)
        {
            me.printStackTrace();
        }
    }
}
