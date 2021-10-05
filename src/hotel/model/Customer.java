package hotel.model;

import java.util.regex.Pattern;

public class Customer {

    private static final String EMAIL_PATTERN = "^(.+)@(.+).(.+)$";

    private final String firstName;
    private final String lastName;
    private final String email;

    public Customer(final String firstName, final String lastName, final String email) {
        this.isValidEmail(email);

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    private void isValidEmail(final String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Неправильная почта!");
        }
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "Имя: " + this.firstName +
                " Фамилия: " + this.lastName +
                " Email: " + this.email;
    }
}
