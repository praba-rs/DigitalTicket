
import java.util.ArrayList;
import javax.mail.Session;

public class SSLEmail {

	/**
	 * Outgoing Mail (SMTP) Server requires TLS or SSL: smtp.gmail.com (use
	 * authentication) Use Authentication: Yes Port for SSL: 465
	 */
	
	/*
	public void send(String toEmail, String[] digitalTickets) {
		final String fromEmail = "registration@ojimaindians.com"; // requires valid gmail id
		//final String fromEmail = "info@ojimaindians.com"; // requires valid gmail id
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
*/
	/*
	public void send(Session session, String toEmail, String digitalTicket, String guest) {


		String subject = "OjimaIndians Dandiya 2023 - Tickets";
		String body = getDandiyaBody();

		EmailUtil.sendAttachmentEmail(session, toEmail, subject, body, digitalTicket);


	}
	*/
	public void send(Session session, String toEmail, ArrayList<String> digitalTickets) {


		String subject = "OjimaIndians Dandiya 2023 - Tickets";
		String body = getDandiyaBody();

		EmailUtil.sendAttachmentEmail(session, toEmail, subject, body, digitalTickets);


	}

	public void send(Session session, String toEmail) {


		String subject = "OjimaIndians Dandiya 2023 - Tickets";
		String body = getDandiyaBody(); 

		EmailUtil.sendEmail(session, toEmail, subject, body);

	}

	private String getDandiyaBody()
	{
		String body = "<p><b>Dear Guest(s),</b></p>"
				+ "<p>"
				+ "<br />Greetings from OjimaIndians Organizing Committee."
				+ "<br />Thank you for your interest in joining Dandiya Masti 2023!"
				+ "<br />"
				+ "<br />Your digital ticket for the event is attached along with."
				+ "<br />"
				+ "<br /><strong><span style='color:#8e44ad'><span style='font-size:20px'>Important:</span></span></strong>"
				+ "<br />1) Please save your digital ticket till the end of the event."
				+ "<br />2) You can present this digital ticket on you phone or in printed form at the event reception."
				+ "<br />3) Entry will not be permitted without the ticket."
				+ "<br />4) Organizers reserve the right to request identity proof for verification."
				+ "<br />5) Dandiya sticks (on returnable basis) will be provided for all."
				+ "<br />"
				+ "<br /><strong><span style='color:#8e44ad'><span style='font-size:20px'>Dandiya Masti 2023</span></span></strong>"
				+ "<br />Date    :	    3rd Nov 2023"
				+ "<br />Time    :	    3:30 PM to 8:00 PM"
				+ "<br />Venue   : 	    India International School in Japan"
				+ "<br />          	    135-0015 Tokyo,"
				+ "<br />          	    Koto Ku, Sengoku, 3-1-4 "
				+ "<br />"
				+ "<br />Looking forward to see you all at Dandiya Masti 2023 !!"
				+ "<br />"
				+ "<br />With Best Regards,"
				+ "<br />OjimaIndians Organizing Committee"
				+ "<br />OjimaIndians - Little India Away From India";
		
		return body;
	}

}
