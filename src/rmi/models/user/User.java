package rmi.models.user;

import java.io.Serializable;

public class User implements Serializable {

    int id;
    String name;
    String address;
    String role;

    public User () {}

    public User(String name, String address, String role)
    {
        this.name = name;
        this.address = address;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getRole() { return role;};

    public void setRole(String role)
    {
        this.role = role;
    }
}
