package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "roles")
public class Role {

    @Id
    private int id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String role_name) {
        this.name = role_name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setRole(User user) {
        this.users.add(user);
        user.getRoles().add(this);
    }

    public void removeRole(User user) {
        this.users.remove(user);
        user.getRoles().remove(this);
    }
}
