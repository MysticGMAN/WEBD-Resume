package webd4201.ClossG;

@SuppressWarnings(value = "serial")

/**
 * @author Grayson Closs
 *	@version 1.0
 *	@since 2023-01-18
 */
public class InvalidIdException extends Exception{
	
	public InvalidIdException(String message){
		super(message);
	}
	
	public InvalidIdException(){
		super();
	}
	
}
