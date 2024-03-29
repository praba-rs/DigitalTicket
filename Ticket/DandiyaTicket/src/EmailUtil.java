
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;

public class EmailUtil {

	/**
	 * Utility method to send simple HTML email
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendEmail(Session session, String toEmail, String subject, String body) {
		try {
			MimeMessage msg = new MimeMessage(session);
			// set message headers
			msg.addHeader("Content-type", "text/html");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			//msg.setFrom(new InternetAddress("info@ojimaindians.com", "OjimaIndians Ticket"));
			//msg.setReplyTo(InternetAddress.parse("info@ojimaindians.com", false));
			msg.setFrom(new InternetAddress("info@ojimaindians.com", "OjimaIndians Ticket"));
			msg.setReplyTo(InternetAddress.parse("info@ojimaindians.com", false));
			
			msg.setSubject(subject, "UTF-8");

			msg.setContent(body, "text/html");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
			// System.out.println("Message is ready");
			System.out.println(toEmail);

			Transport.send(msg);
			System.out.println(toEmail);
			System.out.println("33333333333333333333333");

			// System.out.println("EMail Sent Successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Utility method to send email with attachment
	 * 
	 * @param session
	 * @param toEmail
	 * @param subject
	 * @param body
	 */
	public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body,
			String[] digitalTickets) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/html");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			//msg.setFrom(new InternetAddress("info@ojimaindians.com", "OjimaIndians Ticket"));
			//msg.setReplyTo(InternetAddress.parse("info@ojimaindians.com", false));
			msg.setFrom(new InternetAddress("info@ojimaindians.com", "OjimaIndians Dandiya Ticket"));
			msg.setReplyTo(InternetAddress.parse("info@ojimaindians.com", false));
			
			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			//messageBodyPart.setText(body);
			messageBodyPart.setContent(body,"text/html");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is attachment
			System.out.println("setting email data.....");
			for (String s : digitalTickets) {
				messageBodyPart = new MimeBodyPart();

				DataSource source = new FileDataSource(s);
				messageBodyPart.setDataHandler(new DataHandler(source));
				int random = (int) (Math.random() * 5000 + 1);
				messageBodyPart.setFileName("ticket"+Integer.toString(random)+".png");
				multipart.addBodyPart(messageBodyPart);

			}

			// Send the complete message parts
			msg.setContent(multipart);

			System.out.println("sedning email...1." + toEmail);
			// Send message

			Transport.send(msg);
			// System.out.println("EMail Sent Successfully with attachment!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body,
			ArrayList<String> digitalTickets) {
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/html");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			//msg.setFrom(new InternetAddress("info@ojimaindians.com", "OjimaIndians Ticket"));
			//msg.setReplyTo(InternetAddress.parse("info@ojimaindians.com", false));
			msg.setFrom(new InternetAddress("info@ojimaindians.com", "OjimaIndians Dandiya Ticket"));
			msg.setReplyTo(InternetAddress.parse("info@ojimaindians.com", false));
			
			msg.setSubject(subject, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			//messageBodyPart.setText(body);
			messageBodyPart.setContent(body,"text/html");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is attachment
			System.out.println("setting email data.....");
			for (String s : digitalTickets) {
				if (s.trim().length() >0 )
				{
			
				messageBodyPart = new MimeBodyPart();

				DataSource source = new FileDataSource(s);
				messageBodyPart.setDataHandler(new DataHandler(source));
				int random = (int) (Math.random() * 5000 + 1);
				messageBodyPart.setFileName("ticket"+Integer.toString(random)+".png");
				multipart.addBodyPart(messageBodyPart);
				}

			}

			// Send the complete message parts
			msg.setContent(multipart);

			System.out.println("sedning email...1." + toEmail);
			// Send message

			Transport.send(msg);
			// System.out.println("EMail Sent Successfully with attachment!!");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body,
			String singleticket) {
		
		
				String[] dTickets = new String[] {singleticket};
				sendAttachmentEmail(session, toEmail, subject, body, dTickets);
	}
}
