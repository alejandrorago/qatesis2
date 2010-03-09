package entities;

import java.util.Map;

public interface RichedWordInterface {
	
	public Object getAttribute (String key);
	public Map<String, Object> getAttributes();
	public String getWord();
	public void setAttribute(String key, String value);
    public void setAttributes(Map<String, Object> attributes);
    public void setWord(String word); 
    public void removeAttribute(String key);
	
}
