package tikape.keskustelufoorumi.domain;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Base64.Decoder;
import javax.crypto.SecretKeyFactory;

public class User {
    private Integer id;
    private String name;
    private String pwHash;

    public User(Integer id, String name, String pwHash) {
        this.id = id;
        this.name = name;
        this.pwHash = pwHash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getPwHash() {
        return this.pwHash;
    }
    
    @Override
    public String toString() {
        return "[" + this.id + "/" + this.name + "]";
    }
}