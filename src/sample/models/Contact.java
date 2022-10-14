package sample.models;

import java.util.Objects;

public final class Contact {
    private final int contactId;
    private final String contactName;
    private final String contactEmail;

    public Contact(int contactId, String contactName, String contactEmail) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactEmail = contactEmail;
    }

    public int contactId() {
        return contactId;
    }

    public String contactName() {
        return contactName;
    }

    public String contactEmail() {
        return contactEmail;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Contact) obj;
        return this.contactId == that.contactId &&
                Objects.equals(this.contactName, that.contactName) &&
                Objects.equals(this.contactEmail, that.contactEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, contactName, contactEmail);
    }

    @Override
    public String toString() {
        return contactName;
    }

}
