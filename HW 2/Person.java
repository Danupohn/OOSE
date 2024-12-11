public class Person {
    private String citizenID; 
    private String fullName; 
    private String gender; 

    public Person(String citizenID, String fullName, String gender) {
        this.citizenID = citizenID;
        this.fullName = fullName;
        this.gender = gender;
    }

    public String getCitizenID() {
        return citizenID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGender() {
        return gender;
    }
}
