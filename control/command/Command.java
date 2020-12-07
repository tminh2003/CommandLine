package CommandLine.control.command;

import java.util.ArrayList;

public abstract class Command{
  protected String alias;
  protected String desc;
  protected String param;
  protected OptionList optionList;
  
  public void initInfo(){
    optionList = new OptionList();
    setAlias();
    setDesc();
    setParams();
    registerOptions();
  }
  
  protected abstract void setAlias();
  protected abstract void setDesc();
  protected abstract void setParams();
  protected abstract void registerOptions();
  
  public void execute(String optionAndParam){
    optionList.execute(optionAndParam);
  }
  
  public String getAlias(){
    return alias;
  }
  
  public String getDesc(){
    return desc;
  }
  
  public String getParam(){
    return param;
  }
  
  
}
