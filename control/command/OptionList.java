package commandLineControl.command;

import java.util.Scanner;
import java.util.ArrayList;

import control.exception.NoDefaultMethodRegisteredException;

public class OptionList{
  private ArrayList<Option> allOptions;

  public OptionList(){
    allOptions = new ArrayList<Option>();
  }

  public void register(Option option){
    option.initInfo();
    allOptions.add(option);
  }






  public void execute(String optionAndParam){
    boolean hasDefaultMethod = false;
    for(Option option : allOptions){
        if(option.getAlias().equals("default")){
          hasDefaultMethod = true;
        }
    }
    if(!hasDefaultMethod){
      throw new NoDefaultMethodRegisteredException();
    }


    Scanner scanner = new Scanner(optionAndParam);
    
    //default option and without param
    if(optionAndParam.length() == 0){
      for(Option option : allOptions){
        if(option.getAlias().equals("default")){
            option.execute("");
        }
      }
      return;
    }

    //Remove all spaces before query
    while(optionAndParam.charAt(0) == ' '){
      optionAndParam = optionAndParam.substring(1);
    }

    //default option with param 
    if(optionAndParam.charAt(0) != '-'){
      for(Option option : allOptions){
        if(option.getAlias().equals("default")){
          option.execute(scanner.nextLine());
          return;
        }
      }
    }
    
    String optionAlias = scanner.next();
    
    //non-default option with param
    if(scanner.hasNext()){
      for(Option option : allOptions){
        if(option.getAlias().equals(optionAlias.substring(1))){
          option.execute(scanner.nextLine().substring(1));
          return;
        }
      }
    }
      
    //non-default option without param
    if(!scanner.hasNext()){
      for(Option option : allOptions){
        if(option.getAlias().equals(optionAlias.substring(1))){
          option.execute("p");
          return;
        }
      } 
    }
  }
  
  
  
  
  
  
  
}
