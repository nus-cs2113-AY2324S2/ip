package ChelleExceptions;

import Common.Messages;
public class SaveFileNotFoundException extends Exception{

    public SaveFileNotFoundException(){
        System.out.println(Messages.MESSAGE_SAVE_FILE_NOT_FOUND);
    }
}
