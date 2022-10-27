package sample.models;
import java.util.Objects;

/** Class to hold contact objects.*/
public final class Contact {
    private final int contactId;
    private final String contactName;
    private final String contactEmail;

    // Constructor
    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    /** Get contact id.
     *
     * @return contact id.
     */
    public int contactId() {
        return contactId;
    }

    /** Get contact name.
     *
     * @return contac name.
     */
    public String contactName() {
        return contactName;
    }

    /** Get contact email.
     *
     * @return contact email.
     */
    public String contactEmail() {
        return contactEmail;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (obj == this) return true;
//        if (obj == null || obj.getClass() != this.getClass()) return false;
//        var that = (Contact) obj;
//        return this.contactId == that.contactId &&
//                Objects.equals(this.contactName, that.contactName) &&
//                Objects.equals(this.contactEmail, that.contactEmail);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(contactId, contactName, contactEmail);
//    }

    /** Get string format for contact
     *
     * @return contact Name
     */
    @Override
    public String toString() {
        return contactName;
    }

}
