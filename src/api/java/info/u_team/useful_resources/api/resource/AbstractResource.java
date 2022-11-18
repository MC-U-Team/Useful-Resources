package info.u_team.useful_resources.api.resource;

public interface AbstractResource {
	
	String getName();
	
	int getColor();
	
	AbstractResourceEntries getEntries();
	
	AbstractRegisterProvider getRegisterProvider();
	
}
