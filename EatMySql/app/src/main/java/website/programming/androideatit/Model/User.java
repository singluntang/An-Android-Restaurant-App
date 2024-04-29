package website.programming.androideatit.Model;

import android.util.Base64;

import java.security.Key;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Created by cokel on 2/27/2018.
 */

public class User {
    private String Name;
    private String Password;
    private String Phone;
    private String IsStaff;
    private String secureCode;

    public User(String name, String password, String secureCode) {
        this.Name = name;
        this.Password = password;
        this.IsStaff = "false";
        this.secureCode = secureCode;
    }

    public String getSecureCode() {
        return secureCode;
    }

    public void setSecureCode(String secureCode) {
        this.secureCode = secureCode;
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
        IsStaff = isStaff;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public String verifyPassword(String jwt_token, String password_hash) {

        int flags = Base64.NO_WRAP | Base64.URL_SAFE;

        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        try {

            assert Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt_token).getBody().getSubject().equals(password_hash);

            return new String(Base64.decode(password_hash, flags));
            //OK, we can trust this JWT

        } catch (JwtException e) {

            return "";
            //don't trust the JWT!
        }

    }

    public void setName(String name) {
        Name = name;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
