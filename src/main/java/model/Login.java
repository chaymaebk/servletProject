package model;

public class Login {
    private int IdUser;
    private String Password;
    private String Email;

    // Default constructor
    public Login() {
    }

    // Parameterized constructor
    public Login(String password, String email) {
        password = this.Password;
        email = this.Email;
    }

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}}

    