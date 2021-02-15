package UsersAndContacts;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String birthDate;
    public User(int id,String FirstName,String LastName,String email,String password,String gender,String birth){
        this.id=id;
        this.firstName=FirstName;
        this.lastName =LastName;
        this.password=password;
        this.email=email;
        this.gender=gender;
        this.birthDate=birth;
    }

    public User(){

    }
    public void setID(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

}
