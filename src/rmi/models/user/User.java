package rmi.models.user;

import java.io.Serializable;

public class User implements Serializable {

    int id;
    String name;
    String email;
    String role;

    public User () {}

    public User(String name, String address, String role)
    {
        this.name = name;
        this.email = address;
        this.role = role;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String address)
    {
        this.email = address;
    }

    public String getRole() { return role;};

    public void setRole(String role)
    {
        this.role = role;
    }
}
