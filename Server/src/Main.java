import model.FileManagement;
import controller.Workflow;


/**
 * Class Main contains starting point(main method) of the Server.
 * If you start the program with reset as param it will create a new users.bin file
 * 
 * @author Henrik Johansson
 * @version 2013-02-27
 */
public class Main {
	private static Workflow workflow;
	public static void main(String[] args) {
		
		if(args.length == 0){
			workflow = new Workflow();	
			
		}else if(args[0].equals("reset")){
			FileManagement fileMan = new FileManagement(); //Reset the users.bin so that there is only one user. Login: Admin Password: Admin
			fileMan.writeUsersFile("users.bin"); //Use this if you have edited the user or the users.bin file is corrupt or non existing
		}

	}

}
