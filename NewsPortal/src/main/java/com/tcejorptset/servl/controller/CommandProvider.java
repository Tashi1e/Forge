package com.tcejorptset.servl.controller;

import java.util.HashMap;
import java.util.Map;

import com.tcejorptset.servl.controller.impl.DoAddNews;
import com.tcejorptset.servl.controller.impl.DoChangeLocale;
import com.tcejorptset.servl.controller.impl.DoDeleteNews;
import com.tcejorptset.servl.controller.impl.DoEditNews;
import com.tcejorptset.servl.controller.impl.DoRegistration;
import com.tcejorptset.servl.controller.impl.DoSignIn;
import com.tcejorptset.servl.controller.impl.DoSignOut;
import com.tcejorptset.servl.controller.impl.GoToAddEditNewsPage;
import com.tcejorptset.servl.controller.impl.GoToBasePage;
import com.tcejorptset.servl.controller.impl.GoToErrorPage;
import com.tcejorptset.servl.controller.impl.GoToNewsList;
import com.tcejorptset.servl.controller.impl.GoToViewNews;
import com.tcejorptset.servl.globalConstants.ErrorCode;

public final class CommandProvider {
	private static final CommandProvider instance = new CommandProvider();
	
	private final Map<CommandName, Command> commands = new HashMap<>();
	
	private CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.GO_TO_ADD_EDIT_NEWS_PAGE, new GoToAddEditNewsPage());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_CHANGE_LOCALE, new DoChangeLocale());
		commands.put(CommandName.DO_ADD_NEWS, new DoAddNews());
		commands.put(CommandName.DO_EDIT_NEWS, new DoEditNews());
		commands.put(CommandName.DO_DELETE_NEWS, new DoDeleteNews());
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
			e.printStackTrace();
			return new GoToErrorPage(ErrorCode.ERROR_404.name().toLowerCase());
		}
	}

}
