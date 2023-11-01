

public class DandiyaOnline {
	public  String repeat2(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }

}
