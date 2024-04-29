package website.programming.androideatit.Model;

/**
 * Created by cokel on 2/27/2018.
 */

public class Category {

    private String Categoryid;
    private String Name;
    private String Image;

    public Category() {
    }

    public Category(String categoryid, String name, String image) {
        Categoryid = categoryid;
        Name = name;
        Image = image;
    }

    public String getCategoryid() {
        return Categoryid;
    }

    public void setCategoryid(String categoryid) {
        Categoryid = categoryid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}


