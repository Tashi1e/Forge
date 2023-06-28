package controller;

import java.util.HashMap;
import java.util.Map;

import controller.impl.DoRegistration;
import controller.impl.DoSIgnIn;
import controller.impl.DoSignOut;
import controller.impl.DoChangeLocale;
import controller.impl.GoToBasePage;
import controller.impl.GoToErrorPage;
import controller.impl.GoToNewsList;
import controller.impl.GoToRegistrationPage;
import controller.impl.GoToViewNews;

public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();
	
	private final Map<CommandName, Command> commands = new HashMap<>();
	
	private CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.DO_SIGN_IN, new DoSIgnIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_CHANGE_LOCALE, new DoChangeLocale());
	}
	
	public static CommandProvider getInstance () {
		return instance;
	}
	
	public final Command getCommand(String name) {
		try {
		CommandName  commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
		}
		catch (Exception e) {
			return new GoToErrorPage("404");
		}
	}

}
