package mailsender;

import java.io.IOException;

public class Sender {

	public static void main(String[] args) throws IOException {
		
		SendMailWithAttachment obj = new SendMailWithAttachment();
		obj.send();

	}

}
