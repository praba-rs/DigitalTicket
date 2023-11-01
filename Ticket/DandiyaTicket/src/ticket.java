import java.awt.Color;
//For setting font of the watermark text 
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;

public class ticket {
	//private static String basepath = "C:\\Data\\Ticketdata\\";
	//private static String basepath = "C:\\Users\\praba\\Documents\\GitHub\\DigitalTicket\\Ticket\\DandiyaTicket\\";
	private static String basepath = "C:\\Data\\projects\\DigitalTicket\\Ticket\\DandiyaTicket\\";
	
	private static String inputpath = basepath + "input\\";
	private static String attendeesinputpath = basepath + "input\\attendees\\";
	private static String imageinputpath = basepath + "input\\image\\";
	private static String codereadroutputpath = basepath + "output\\codereadr\\";
	private static String ticketoutputpath = basepath + "output\\tickets\\";

	public static void main(String[] args) throws NumberFormatException, IOException, WriterException {
		System.out.println("Start....." + getDateTime());

		Config cfg = new Config(basepath);
		String property = cfg.getProperty("process");
		
		property = "13";
		
		System.out.println("Start....." + property + "....");
		if (property.trim().equals("1")) {
			createperformerattendeedata();
		}

		if (property.trim().equals("2")) {
			genregattendeedata();
		}

		if (property.trim().equals("3")) {
			sponsorattendeedata();
		}
		if (property.trim().equals("4")) {
			commonattendeedata();
		}

		//diwali tickets has to be reworked

		if (property.trim().equals("13")) {
			dandiyaCreateTicketConsolidated();
		} // crea

		if (property.trim().equals("20")) {
			CreatePosters();
		} // crea

		if (property.trim().equals("30")) {
			SendEmailOnly();
		} //for testing

		System.out.println("End....." + getDateTime());

	}

	public static void SendEmailOnly() throws IOException {
		final String emailids = "praba_rs@yahoo.com,hemant.visal@gmail.com";
		List<String> emailList = Arrays.asList(emailids.split(","));
		Session session = getEmailSession();
		SSLEmail se = new SSLEmail();
		emailList.forEach((id)->se.send(session,id ));

	}
	
	public static void createperformerattendeedata() throws IOException {

		Performerattendee pa = new Performerattendee();
		pa.processperformerattendee(attendeesinputpath + "performerattendees.txt",
				attendeesinputpath + "PerAttnConsolidated.txt");
	}

	public static void genregattendeedata() throws IOException {

		genregAttendee ga = new genregAttendee();
		ga.processattendee(attendeesinputpath + "online.csv", attendeesinputpath + "genregConsolidated.txt");
	}

	public static void sponsorattendeedata() throws IOException {

		SponsorAttendee ga = new SponsorAttendee();
		ga.processattendee(attendeesinputpath + "sponsorattendees.txt", attendeesinputpath + "sponsorConsolidated.txt");
	}

	public static void commonattendeedata() throws IOException {
		Config cfg = new Config(basepath);

		SponsorAttendee ga = new SponsorAttendee();
		ga.processattendee(attendeesinputpath + cfg.getProperty("commonAttendeefilename"),
				attendeesinputpath + cfg.getProperty("commonAttendeefilenameoutput"));
	}


	public static Session getEmailSession() {
		System.out.println("getEmailSession.....");
		
		final String fromEmail = "info@ojimaindians.com"; // requires valid gmail id
		final String password = "Ojimaindians61"; // correct password for gmail id

		System.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtpout.secureserver.net");
        props.put("mail.smtp.port", "80");
                     

       props.put("mail.smtp.auth", "true");
       props.setProperty("mail.user", fromEmail);
       props.setProperty("mail.password", password);        
        
		
		System.out.println("Setting connection to SMTP.....");
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		return Session.getDefaultInstance(props, auth);
	}

	public static void dandiyaCreateTicketConsolidated() throws NumberFormatException, IOException, WriterException {
		String inputAttendeesFile = attendeesinputpath + getAttendeesFilename();
		System.out.println("Reading attendees file......" + inputAttendeesFile);
		/**
		 * Creating a buffered reader to read the file
		 */
		BufferedReader bReader = new BufferedReader(new FileReader(inputAttendeesFile));

		String line;

		/**
		 * Looping the read block until all lines in the file are read.
		 */

		String outputCodeReaderfile = codereadroutputpath + getfilenameforcodereader();
		PrintWriter qrCodeWriter = new PrintWriter(outputCodeReaderfile);
		System.out.println("Creating file for Codereader......" + outputCodeReaderfile);

		int userctr = 0;
		Session session = getEmailSession();

		while ((line = bReader.readLine()) != null) {

			/**
			 * Splitting the content of tabbed separated line
			 */
			System.out.println(
					"Start user --------------------------------------------------------------------------------------");
			String datavalue[] = line.split(",");
			String id = datavalue[0];
			String primaryname = datavalue[1]+ " "+ datavalue[2];
			String guests[] = datavalue[3].trim().split(";");
			String email = datavalue[4];

			String oneattendee;
			String oneattendeeid;

			userctr++;
			System.out.println("Reading user data ......" + id);

			System.out.println(
					"Start attendee -------------------------------------------------------------------------");
			ArrayList<String> attachments = new ArrayList<String>();
			int currentNo =0;
			for(String guest : guests)
			{
				oneattendee = guest;
				int random = (int) (Math.random() * 50 + 1);
				oneattendeeid = id + "0" + Integer.toString(userctr) + Integer.toString(random);
				System.out.println("working on attendee data ......" + oneattendee);
				System.out.println(oneattendeeid + "..." + id + ".." + primaryname + ".." + oneattendee + ".." + email);
				StringBuilder sb = new StringBuilder();
				sb.append(oneattendeeid);
				sb.append(',');
				sb.append(oneattendee);
				sb.append('\n');
				qrCodeWriter.write(sb.toString());
				String charset = "UTF-8";
				Map<?, ?> hintMap = new HashMap<Object, Object>();
				String pngFile = ticketoutputpath + "q.png";
				createQRCode(oneattendeeid, pngFile, charset, hintMap, 150, 150);
				// createQRCode(oneattendeeid, pngFile, charset, hintMap, 98, 98);
				String parentidSuffix = ++currentNo + "/"+guests.length; 

				String attachment = imageProcess(id.toString(),parentidSuffix, oneattendeeid, oneattendee, pngFile);
				attachments.add(attachment);
			}
			// qr
			SSLEmail se = new SSLEmail();
			se.send(session, email, attachments);
		}

		// writer.flush();
		qrCodeWriter.close();
		bReader.close();

	}

	public static void CreatePosters() throws IOException {
		Poster po = new Poster();
		po.imageProcessAppend();
	}


	public static String getAttendeesFilename() throws IOException {
		String dataFileName = inputpath + "filename.txt";
		BufferedReader bReader = new BufferedReader(new FileReader(dataFileName));

		String line = bReader.readLine();
		bReader.close();
		return line;
	}

	public static String getfilenameforcodereader() {
		return getDateTime() + ".txt";

	}

	public static String getfilenameforRaffle() {
		return getfilenameforcodereader();

	}

	public static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		return dateFormat.format(date);

	}

	public static String imageProcess(String parentid,String parentidSuffix,  String childid, String name, String png) throws IOException {
		BufferedImage img = null;
		File f = null;
		System.out.println("Readng image template....." + imageinputpath + "DM2023Ticket.png");
		// Read image
		try {
			f = new File(imageinputpath + "DM2023Ticket.png");
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println(e);
		}

		// create BufferedImage object of same width and
		// height as of input image
		BufferedImage temp = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Create graphics object and add original
		// image to it
		Graphics graphics = temp.getGraphics();
		graphics.drawImage(img, 0, 0, null);

		// Set font for the watermark text

		BufferedImage qr = ImageIO.read(new File(png));
		graphics.drawImage(qr, 443, 450, null);
		// graphics.drawImage(qr, 490, 533, null);
		System.out.println("Writing qrcode on ticket");

		graphics.setFont(new Font("Arial", Font.PLAIN, 30));
		graphics.setColor(Color.yellow);

		graphics.drawString(parentid+" "+parentidSuffix, 39, 550);

		// Add the watermark text at (width/5, height/3)
		// location
		graphics.drawString(name, 39, 580);
		System.out.println("Writing name on ticket");
		String filename = name;
		// releases any system resources that it is using
		graphics.dispose();
		String ticketFile;
		// check if file already exists
		while (true) {
			ticketFile = ticketoutputpath + parentid + "\\" + filename + ".png";
			f = new File(ticketFile);
			if (f.exists()) {
				filename = filename + "1";
				continue;
			} else {
				f.getParentFile().mkdir();
				break;
			}
		}
		try {
			ImageIO.write(temp, "png", f);
		} catch (IOException e) {
			System.out.println(e);
		}
		return ticketFile;
	}

	public static String imageProcessDiwali(String parentid, String childid, String name, String png, String adultchild)
			throws IOException {
		BufferedImage img = null;
		File f = null;

		String adultChildLegend = adultchild.equals("A") ? "ADULT" : "CHILD";
		String imageinput = adultchild.equals("A") ? "tickettemplateAdult.png" : "tickettemplateChild.png";
		name = adultchild.equals("A") ? childid + " " + name : name;

		System.out.println("Readng image template....." + imageinputpath + "\\" + imageinput);
		// Read image
		try {
			f = new File(imageinputpath + "\\" + imageinput);
			img = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println(e);
		}

		// create BufferedImage object of same width and
		// height as of input image
		BufferedImage temp = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

		// Create graphics object and add original
		// image to it
		Graphics graphics = temp.getGraphics();
		graphics.drawImage(img, 0, 0, null);

		graphics.setFont(new Font("Arial", Font.PLAIN, 40));
		graphics.setColor(Color.white);
		graphics.drawString(adultChildLegend, 25, 575);
		System.out.println("Writing Adult/child on ticket");

		graphics.setFont(new Font("Arial", Font.PLAIN, 30));
		graphics.setColor(Color.white);
		graphics.drawString(name, 20, 605);
		System.out.println("Writing name on ticket");

		BufferedImage qr = ImageIO.read(new File(png));
		graphics.drawImage(qr, 468, 539, null);
		System.out.println("Writing qrcode on ticket");

		String filename = name;
		// releases any system resources that it is using
		graphics.dispose();
		String ticketFile;
		// check if file already exists
		while (true) {
			ticketFile = ticketoutputpath + parentid + "\\" + filename + ".png";
			f = new File(ticketFile);
			if (f.exists()) {
				filename = filename + "1";
				continue;
			} else {
				f.getParentFile().mkdir();
				break;
			}
		}
		try {
			ImageIO.write(temp, "png", f);
		} catch (IOException e) {
			System.out.println(e);
		}
		return ticketFile;
	}

	@SuppressWarnings("deprecation")
	public static void createQRCode(String qrCodeData, String filePath, String charset, Map<?, ?> hintMap,
			int qrCodeheight, int qrCodewidth) throws WriterException, IOException {
		System.out.println("creatin qr code.....");
		BitMatrix matrix = new MultiFormatWriter().encode(new String(qrCodeData.getBytes(charset), charset),
				BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight);
		MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
	}
}
