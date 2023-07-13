package tcejorptset.servl.util.validation;

public class UserDataValidationImpl implements UserDataValidation{

	@Override
	public boolean checkAUthData(String login, String password) {
		if (login == null) {
			return false;
		}
		else if (password == null) {
			return false;
		}
		else return true;
	}
}
