import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class genregAttendee {
	public void processattendee(String inputfile, String outputfile) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(inputfile));

		String line;
		PrintWriter writer = new PrintWriter(outputfile);
System.out.println("sssss");
		String name = "";
		String email = "";
		String primaryname = "";
		String adultchild = "";

		while ((line = bReader.readLine()) != null) {
			System.out.println(line);
			String datavalue[] = line.split("\t");

			primaryname =  datavalue[1] +" " +datavalue[0];
			System.out.println(primaryname);	
			name = datavalue[2] ;
			email = datavalue[3];
			adultchild =   repeat("A,", Integer.parseInt( datavalue[4].toString().substring(3, 4))) +repeat("C,", Integer.parseInt( datavalue[4].toString().substring(32, 33)));
			writer.write(
					primaryname  + "\t" + email + "\t" + name + "\t" + adultchild + "\n");

		}

		writer.close();
		bReader.close();
	}
	public  String repeat(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }

}
