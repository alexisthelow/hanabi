package hanabi.driver;

public enum MoveType {
	
	GIVE_INFO("give information"), DISCARD_CARD("discard"), PLAY_CARD("play");
	
	MoveType(String actionName) {
		this.actionName = actionName;
	}
	
	String actionName;

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	
}
