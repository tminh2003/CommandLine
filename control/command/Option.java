package CommandLine.control.command;

public abstract class Option{
  protected String alias;
  
  protected abstract void setAlias();
  protected abstract boolean paramValid(String param);
  protected abstract void executeAfterParamCheck(String param);
  
  public void initInfo(){
    setAlias();
  }
  
  public void execute(String param){
    if(paramValid(param)){
      executeAfterParamCheck(param);
    }
  }
  
  public String getAlias(){
    return alias;
  }
  
}
