
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
		final String fromEmail = "registration@ojimaindians.com"; // requires valid gmail id
		//final String fromEmail = "info@ojimaindians.com"; // requires valid gmail id
		final String password = "getready61"; // correct password for gmail id
		// final String toEmail = "praba_rs@yahoo.com"; // can be any email id

		// System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtpout.secureserver.net"); // SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); // SSL Port
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL Factory Class
		props.put("mail.smtp.auth", "true"); // Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); // SMTP Port
		//props.put("mail.smtp.starttls.enable","false");
		System.out.println("Setting connection to SMTP.....");
		System.out.println("........................................................");
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


		String subject = "Digital Tickets: Dandiya 2022";
		String body = getDandiyaBody(guest);

		EmailUtil.sendAttachmentEmail(session, toEmail, subject, body, digitalTicket);


	}

	public void send(Session session, String toEmail) {


		String subject = "OjimaIndians Dandiya 2022 - Option for Partial refund";
		String body = getRefundBody(); 

		EmailUtil.sendEmail(session, toEmail, subject, body);

	}

	private String getRefundBody()
	{
		String body = "<p>Dear Dandiya Participants,</p>"
				+ "<p>"
				+ "<br />Thank you for your continuous support and participating in OjimaIndians Dandiya Masti 2022."
				+ "<br />As you know, we had to end the event before full time due to a medical emergency. We thank you again for your immense support and understanding at that time."
				+ "<br />After due deliberation, we decided that it is only fair that you have an opportunity to avail 50% refund of your entrance fee, provided you are interested."
				+ "</p>"
				+ "<p>"
				+ "If interested in getting the refund, please fill in the form from the link below."
				+ "<br />Please make sure to use the same email address that was used at time of ticket booking."
				+ "<br />Last date to fill the form is <B>18th November 2022 (Friday)</B>"
				+ "<br /><a href=\"https://forms.gle/3XCh34HWeYWkfATSA\">Click here for refund</a>"
				+ "</p>"
				+ "<p>"
				+ "Thank you always for your kind and valued support."
				+ "</p>"
				+ "<p>"
				+ "<em>PS: OIOC has been supporting the NFBM Jagriti School for Blind Girls for the past 10 years. The proceeds from Dandiya is used for this purpose and our commitment to them continues this year as well.</em>"
				+ "<br />Learn more about Jagriti below<br /><a href=\"https://www.facebook.com/jagritischool/\" target=\"_blank\" rel=\"noopener\">Click here for Jagriti info</a>"
				+ "</p>"
				+ "<p>"
				+ "Sincerely<br />OIOC"
				+ "</p>";

		return body;
	}
	private String getDandiyaBody(String guest) {
		String body = "Dear Guest(s),\r\n" + "\r\n" + "Greetings from OjimaIndians Organizing Committee.\r\n"
				+ "Thank you for your interest in joining Dandiya Masti 2022!\r\n" + "\r\n"
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
				+ "Dandiya Masti 2022\r\n" + "15th Oct 2022, Saturday @ 5:30 PM ~ 9:00 PM\r\n"
				+ "Sunamachi Bunka Center\r\n" + "136-0073, Tokyo, Koto ku ku, Kita suna 5-1-7 \r\n" + "\r\n"
				+ "Looking forward to see you all at Dandiya Masti 2022 !!\r\n" + "\r\n"
				+ "IMPORTANT COVID-19 MEASURES: PLEASE DO FOLLOW MASK & SOCIAL DISTANCING PROCEDURES WHEREVER APPLICABLE !!\r\n" + "\r\n"
				+ "OjimaIndians Organizing Committee\r\n" + "OjimaIndians - Little India Away From India\r\n"
				+ "Email : ojimaindians@yahoo.com\r\n" + "Yahoo Group : Ojima Indians\r\n" + "Facebook : OjimaIndians";
		return body;
	}
}
