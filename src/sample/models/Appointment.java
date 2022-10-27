package sample.models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Class for Appointments.*/
public class Appointment {

    private int appointmentId;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int customerId;
    private int userId;
    private int contactId;

    // constructor
    public Appointment(String title,
                       String description,
                       String location,
                       String type,
                       LocalDateTime startTime,
                       LocalDateTime endTime,
                       int customerId,
                       int userId,
                       int contactId) {

        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /** Get appointment id.
     *
     * @return appointment id
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /** Get appointment title.
     *
     * @return appointment title
     */
    public String getTitle() {
        return title;
    }

    /** Get appointment description.
     *
     * @return appointment description
     */
    public String getDescription() {
        return description;
    }

    /** Get appointment location.
     *
     * @return appointment location
     */
    public String getLocation() {
        return location;
    }

    /** Get appointment type.
     *
     * @return appointment type.
     */
    public String getType() {
        return type;
    }

    /** Get appoinment start time.
     *
     * @return start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /** get appointment start time formatted in string form.
     *
     * @return formatted start time.
     */
    public String getStartTimeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");
        return startTime.format(formatter);
    }

    /** Get appointment end time.
     *
     * @return appointment time
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /** Get end time formatted in a string.
     *
     * @return string of formatted end time.
     */
    public String getEndTimeString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy HH:mm");
        return endTime.format(formatter);
    }

    /** Get customer Id.
     *
     * @return customer id
     */
    public int getCustomerId() {
        return customerId;
    }

    /** Get user Id.
     *
     * @return user id.
     */
    public int getUserId() {
        return userId;
    }

    /** Get contact Id.
     *
     * @return contact id.
     */
    public int getContactId() {
        return contactId;
    }

    /** Get contact Name.
     *
     * @return contact name.
     */
    public String getContactName() {return UtilityLists.getContactName(contactId);}

    /** Set appointment id.
     *
     * @param appointmentId
     */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    /** Set appointment title.
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Set appointment description.
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** Set appointment location.
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /** Set appointment type.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /** Set appointment start time.
     *
     * @param startTime
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /** Set appointment end time.
     *
     * @param endTime
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /** Set customer id.
     *
     * @param customerId
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /** Set user id.
     *
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /** Set contact id.
     *
     * @param contactId
     */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    /** String representation of Appointment.
     *
     * @return appointment string
     */
    @Override
    public String toString() {
        return "Appointmnet{" +
                "appointmentId=" + appointmentId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
