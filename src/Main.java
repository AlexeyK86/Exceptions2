import ru.skupro.exceptions.WrongLoginException;
import ru.skupro.exceptions.WrongPasswordException;

public class Main {
    public static final String VALIDATE_PATTERN = "^[a-zA-Z0-9-_]+$";
    public static void main(String[] args) {
        check("login", "pass", "pass");
        check("login&%", "pass", "pass");
        check("login", "pass&&&", "pass");
        check("login", "pass3sdfkdfjkdfkdfjkdfjkdf", "pass");
        check("login", "pass", "pass987");
    }

    public static boolean check(String login, String pass, String confirmPass) {
        boolean isValid = true;
        try {
            checkLogin(login);
            checkPass(pass, confirmPass);
        } catch (WrongLoginException e) {
            System.out.println("Ошибка с введенным логином: " + e.getMessage());
            isValid = false;
        } catch (WrongPasswordException e) {
            System.out.println("Ошибка с введенным паролем: " + e.getMessage());
            isValid = false;
        }

        return isValid;
    }

    public static void checkLogin(String login) throws WrongLoginException {
        if (!login.matches(VALIDATE_PATTERN)) {
            throw new WrongLoginException("Логин должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (login.length() > 20) {
            throw new WrongLoginException("Логин не может быть длиннее 20 символов");
        }
    }

    public static void checkPass(String pass, String confirmPass) throws WrongPasswordException {
        if (!pass.matches(VALIDATE_PATTERN)) {
            throw new WrongPasswordException("Пароль должен содержать в себе только латинские буквы, цифры и знак подчеркивания");
        } else if (pass.length() > 20) {
            throw new WrongPasswordException("Пароль не может быть длиннее 20 символов");
        } else if (!pass.equals(confirmPass)) {
            throw new WrongPasswordException("Пароли не совпадают");
        }
    }
}