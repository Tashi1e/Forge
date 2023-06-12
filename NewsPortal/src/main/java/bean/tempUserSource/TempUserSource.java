package bean.tempUserSource;

import bean.UserInfo;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TempUserSource {

	private final File file = new File("src/usersInfo/", "users.usr");
	private List<UserInfo> userList = new ArrayList<>();

	private boolean findUserByLogin(String login) throws ClassNotFoundException, IOException {
		readFile();
			
		for (UserInfo user: userList ) {
			return (user.getNickName().equals(login) || user.getEmail().equals(login)) 	
		}
	}

	public boolean saveNewUser(UserInfo newUser) throws ClassNotFoundException, IOException {
		String nickName = newUser.getNickName();
		String email = newUser.getEmail();
		
		if (findUserByLogin(nickName) && findUserByLogin(email)) {
			saveNewUser(newUser);
			return true;
		}
		else return false;
	}

	@SuppressWarnings("all")
	public TempUserSource() {
		try {
			if (!file.exists())
				file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
