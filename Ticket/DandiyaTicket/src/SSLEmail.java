
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

	/**
	 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
	 * authentication) Use Authentication: Yes Port for SSL: 465
	 */
	public void send(String toEmail, String[] digitalTickets) {
		final String fromEmail = "info@ojimaindians.com"; // requires valid gmail id
		final String password = "getready61"; // correct password for gmail id
		// final String toEmail = "praba_rs@yahoo.com"; // can be any email id

		// System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.europe.secureserver.net"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port
		//props.put("mail.smtp.starttls.enable","false");
		System.out.println("Setting connection to SMTP.....");
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);

		String subject = "(Re-issued) Digital Tickets: Diwali Dhamaka 2018";
		String body = "\r\nImportant: Your tickets has been reissued\r\n"
				+ "Please use tickets attached in this email.\r\n" + "\r\n" + "\r\n" + "Dear Guest(s),\r\n" + "\r\n"
				+ "Greetings from OjimaIndians Organizing Committee.\r\n"
				+ "Thank you for your interest in joining Diwali Dhamaka 2018 !\r\n" + "\r\n"
				+ "Digital tickets for all the guests registered under this email are attached. \r\n" + "\r\n"
				+ "Important:\r\n" + "1) Please save your digital ticket till the end of the event.\r\n"
				+ "2) Present this digital ticket, in phone or in printed form, at the event reception.\r\n"
				+ "3) Entry for only valid ticket holders.\r\n" 

				+ "\r\n" 
				+ "Date / Time & Venue:\r\n"
				+ "Diwali Dhamaka 2018\r\n" + "4th November 2018, Sunday @ 12:00 PM ~ 7:00 PM\r\n"
				+ "Kasai Kumin Kan\r\n" + "134-0083, Tokyo, Edogawa ku, Naka Kasai 3-10-1 \r\n" + "\r\n"
				+ "Looking forward to see you all at Diwali Dhamaka 2018 !!\r\n" + "\r\n"
				+ "OjimaIndians Organizing Committee\r\n" + "OjimaIndians - Little India Away From India\r\n"
				+ "Email : ojimaindians@yahoo.com\r\n" + "Yahoo Group : Ojima Indians\r\n" + "Facebook : OjimaIndians";

		EmailUtil.sendAttachmentEmail(session, toEmail, subject, body, digitalTickets);

		// EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with
		// Image", "SSLEmail Testing Body with Image");

	}

	public void send(Session session, String toEmail, String digitalTicket, String guest) {
/*		final String fromEmail = "info@ojimaindians.com"; // requires valid gmail id
		final String password = "getready61"; // correct password for gmail id
		// final String toEmail = "praba_rs@yahoo.com"; // can be any email id

		// System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.europe.secureserver.net"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port
		//props.put("mail.smtp.starttls.enable","false");
		
		//props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.debug", "true");
		System.out.println("Setting connection to SMTP.....");
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);*/

		String subject = "Digital Tickets: Dandiya 2019";
		String body = getDandiyaBody(guest);

		EmailUtil.sendAttachmentEmail(session, toEmail, subject, body, digitalTicket);

		// EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with
		// Image", "SSLEmail Testing Body with Image");

	}

	private String getDandiyaBody(String guest) {
		String body = "Dear Guest(s),\r\n" + "\r\n" + "Greetings from OjimaIndians Organizing Committee.\r\n"
				+ "Thank you for your interest in joining Dandiya Masti 2019!\r\n" + "\r\n"
				+ "Digital ticket is attached. \r\n"
				+ "Guest name:"+ guest + "\r\n\r\nImportant:\r\n"
				+ "1) Please save your digital ticket till the end of the event.\r\n"
				+ "2) Present this digital ticket, in phone or in printed form, at the event reception.\r\n"
				+ "3) Entry will not be permitted without the ticket.\r\n" 
				+ "4) Organizers reserve the right to request identity proof for verification.\r\n" 
				+ "5) Dandiya sticks (on returnable basis) will be provided for all.\r\n" 
				+ "6) Variety of Indian snacks available at food stalls (at actuals).\r\n" 
				+ "\r\n" 
				+ "Date / Time & Venue:\r\n"
				+ "Dandiya Masti 2019\r\n" + "5th Oct 2019, Saturday @ 4:00 PM ~ 9:00 PM\r\n"
				+ "Sunamachi Bunka Center\r\n" + "136-0073, Tokyo, Koto ku ku, Kita suna 5-1-7 \r\n" + "\r\n"
				+ "Looking forward to see you all at Dandiya Masti 2019 !!\r\n" + "\r\n"
				+ "OjimaIndians Organizing Committee\r\n" + "OjimaIndians - Little India Away From India\r\n"
				+ "Email : ojimaindians@yahoo.com\r\n" + "Yahoo Group : Ojima Indians\r\n" + "Facebook : OjimaIndians";
		return body;
	}
}
