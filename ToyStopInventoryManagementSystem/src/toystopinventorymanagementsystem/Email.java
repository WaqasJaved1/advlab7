
package toystopinventorymanagementsystem;

import java.io.Serializable;

/**
 *
 * @author Fahad Satti
 */
class Email implements Serializable{
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Email{" + "emailAddress=" + emailAddress + '}';
    }

}
