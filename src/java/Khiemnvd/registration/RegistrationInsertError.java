package Khiemnvd.registration;

import java.io.Serializable;

/**
 *
 * @author TheKhiem7
 */
public class RegistrationInsertError implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmPassNotMatch;
    private String fullnameLengthErr;
    private String usernameExisted;

    public RegistrationInsertError() {
    }

    public RegistrationInsertError(String usernameLengthErr, String passwordLengthErr, String confirmPassNotMatch, String fullnameLengthErr, String usernameExisted) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.confirmPassNotMatch = confirmPassNotMatch;
        this.fullnameLengthErr = fullnameLengthErr;
        this.usernameExisted = usernameExisted;
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmPassNotMatch() {
        return confirmPassNotMatch;
    }

    public void setConfirmPassNotMatch(String confirmPassNotMatch) {
        this.confirmPassNotMatch = confirmPassNotMatch;
    }

    public String getFullnameLengthErr() {
        return fullnameLengthErr;
    }

    public void setFullnameLengthErr(String fullnameLengthErr) {
        this.fullnameLengthErr = fullnameLengthErr;
    }

    public String getUsernameExisted() {
        return usernameExisted;
    }

    public void setUsernameExisted(String usernameExisted) {
        this.usernameExisted = usernameExisted;
    }
    
    
}
