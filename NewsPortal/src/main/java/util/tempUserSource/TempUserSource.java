package util.tempUserSource;

import bean.UserInfo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TempUserSource {

	private final File file = new File("D:/_Java/_Workspace/Forge/NewsPortal/src/main/java/util/tempUserSource", "users.usr");
	private List<UserInfo> userList = new ArrayList<>();

	@SuppressWarnings("all")
	public TempUserSource() {
		try {
			if (!file.exists())
				file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserInfo checkLogPass(String login, String password) throws ClassNotFoundException, IOException {
		UserInfo user = findUser(login);
		UserInfo result = null;
		if (user!=null && user.getPassword().equals(password)) {
			result =  user;
		}
		return result;
	}

	public boolean saveNewUser(UserInfo newUser) throws ClassNotFoundException, IOException {
		if (findUser(newUser.getEmail())==null) {
			userList.add(newUser);
			writeFile();
			return true;
		} else
			return false;
	}

	private UserInfo findUser(String login) throws ClassNotFoundException, IOException {
		readFile();
		UserInfo found = null;
		for (UserInfo user : userList) {
			if (user.getEmail()!=null && user.getEmail().equals(login)) {
				found = user;
				break;
			}
		}
		return found;
	}

	private void writeFile() throws IOException {
		try (var write = new ObjectOutputStream(new FileOutputStream(file))) {
			write.writeObject(userList);
			write.flush();
		}
	}

	@SuppressWarnings("unchecked")
	private void readFile() throws ClassNotFoundException, IOException {
		try (var read = new ObjectInputStream(new FileInputStream(file))) {
			userList = (List<UserInfo>) read.readObject();
		} catch (EOFException ignored) {
		}
	}

}
