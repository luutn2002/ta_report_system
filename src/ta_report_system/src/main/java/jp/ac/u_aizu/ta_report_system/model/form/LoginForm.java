package jp.ac.u_aizu.ta_report_system.model.form;

public class LoginForm {
  private String userName;
  private String password;

  public LoginForm() {
  }

  public LoginForm(String userName, String password) {
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return this.userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
