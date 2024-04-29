package website.programming.androideatitserver.Model;

/**
 * Created by cokel on 2/27/2018.
 */

public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String IsStaff;

    public User(String name, String password) {
        Name = name;
        Password = password;
        //Phone = phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public User() {
    }


    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        this.IsStaff = isStaff;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
