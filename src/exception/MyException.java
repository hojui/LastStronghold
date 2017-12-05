package exception;

import javafx.application.Platform;

public class MyException extends Exception{
	
	public MyException() {
		System.out.println("Exception here.");
		Platform.exit();
	}
	
	public void showException() {
		this.getMessage();
	}
	
	public void showStackTrace() {
		this.printStackTrace();
	}
}
