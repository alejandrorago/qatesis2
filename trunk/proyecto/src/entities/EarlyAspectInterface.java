package entities;

import java.util.List;

public interface EarlyAspectInterface {
		
	public int getId();
	public String getName();
	public List<EarlyAspectPairInterface> getPairs();
}
