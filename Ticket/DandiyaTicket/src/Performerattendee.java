import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Performerattendee {

	public void processperformerattendee(String inputfile, String outputfile) throws IOException {
		BufferedReader bReader = new BufferedReader(new FileReader(inputfile));

		String line;
		PrintWriter writer = new PrintWriter(outputfile);

		StringBuilder name = new StringBuilder("");
		String email = "";
		String primaryname = "";
		StringBuilder adultchild = new StringBuilder("");

		while ((line = bReader.readLine()) != null) {
			String datavalue[] = line.split("\t");
			if (!email.trim().equals(datavalue[1])) {
				if (email != "") {
					writer.write(
							primaryname + "\t" + email + "\t" + name.toString() + "\t" + adultchild.toString() + "\n");
				}
				primaryname = datavalue[0];
				name = new StringBuilder("");
				adultchild = new StringBuilder("");

			}

			name.append(datavalue[0] + ",");
			email = datavalue[1];
			adultchild.append(datavalue[2].substring(0, 1) + ",");

		}
		writer.write(
				primaryname + "\t" + email + "\t" + name.toString() + "\t" + adultchild.toString() + "\n");

		writer.close();
		bReader.close();
	}
}
