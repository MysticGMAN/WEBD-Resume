package webd4201.ClossG;

/**
 * @author Grayson Closs
 *
 */
@SuppressWarnings("serial")
public class NotFoundException extends Exception{

	public NotFoundException() {
		super();
	}
	
	public NotFoundException(String message){ 
		super(message);
	}

}