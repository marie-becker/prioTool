package se1_projekt.exceptions;

public class ContainerException extends Exception {

	private Integer id;
	
	public ContainerException( String s ) {
		super ( s );
	}

	@Override
	public void printStackTrace() {
		System.out.println("Das Person-Objekt mit der ID " + this.id + " ist bereits vorhanden!"); 
	} 

	public void addID(Integer id) {
		this.id = id;
	}


}
