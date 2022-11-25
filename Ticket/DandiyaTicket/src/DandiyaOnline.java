import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DandiyaOnline {
	public void processattendee(String inputfile, String outputfile) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(inputfile));

		String line;
		String id;
		PrintWriter writer = new PrintWriter(outputfile);
		String email = "";
		String primaryname = "";
		
		

		while ((line = bReader.readLine()) != null) {
			String datavalue[] = line.split(",");

			primaryname =  datavalue[2] +" " +datavalue[1];
			
			id = datavalue[0];
			email = datavalue[4];
			String allNames[] = datavalue[3].trim().split(";");
			for (String name: allNames) {  
				if (name != "")
				writer.write(
						id+ "\t" +primaryname  + "\t" + email + "\t" + name + "\n");

			}
			
		}

		writer.close();
		bReader.close();
	}
	public  String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }

}
