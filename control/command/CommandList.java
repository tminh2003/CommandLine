package CommandLineControl.control.command;

import java.util.ArrayList;
import java.util.Scanner;

import control.command.Command;
import control.exception.DuplicateCommandException;

public class CommandList{
  private static CommandList commandList;
  private ArrayList<Command> allCommands;
  
  private CommandList(){
    allCommands = new ArrayList<Command>();
  }
  
  public static CommandList getCommandListObject(){
    if(commandList == null){
      commandList = new CommandList();
    }
    return commandList;
  }
  
  /*
  * This method returns a clone of the array list of all registered commands to ensure that the list is not easily mutable. aka view-only.
  *
  * 
  */
  
  @SuppressWarnings("unchecked")
  public ArrayList<Command> cloneAllCommands(){
    return (ArrayList<Command>) allCommands.clone();
  }
  
  /*
  * This method registers the command that might be called by the command line
  *
  * @param command - the command to be registered
  *
  * @throws DuplicateCommandException if the client code adds two commands with the same alias
  */
  
  public void register(Command command){
    command.initInfo();

    for(Command c : allCommands){
      if(c.getAlias().equals(command.getAlias())){
        throw new DuplicateCommandException();
      }
    }

    allCommands.add(command);
  }
  
  
  /*
  * This method executes the command, the option, and the parameter given by the query
  *
  * @param query - the query that includes the alias and all the parameters of the command that will be executed
  *
  */
  
  public void execute(String query){  
    Scanner scanner = new Scanner(query);
    String alias = scanner.next();

    for(Command command : allCommands){
      if(command.getAlias().equals(alias)){
        if(scanner.hasNext()){
          command.execute(scanner.nextLine());
        }else{
          command.execute("");
        }
        
        
      }
    }
  }
}
