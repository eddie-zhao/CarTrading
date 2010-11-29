package action;

public interface CommonAction {
	final String REDIRECT_ACTION = "redirectAction";
	final String CUSTOM_PAGE = "customPage";
	String setRedirectAction(String toAction);
	String getRedirectAction();
	String setCustomPage(String page);
	String getCustomPage();
}

