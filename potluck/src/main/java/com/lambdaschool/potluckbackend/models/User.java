package com.lambdaschool.potluckbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false,
        unique = true)
    @Email
    private String primaryemail;

    @OneToMany(mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
                        allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();


    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
        allowSetters = true)
    private Set<Potluck> potlucks = new HashSet<>();

    public User()
    {
    }

    public User(
        @NotNull String username,
        @NotNull String password,
        @NotNull String primaryemail)
    {
        setUsername(username);
        setPassword(password);
        this.primaryemail = primaryemail;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }


    public String getPrimaryemail()
    {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail.toLowerCase();
    }



    public String getPassword()
    {
        return password;
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;

    }

    /**
     * the new password for the current user, comes in plain text and goes encrypted for
     * security.
     * @param password
     */

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public Set<UserRoles> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<UserRoles> roles)
    {
        this.roles = roles;
    }

    public Set<Potluck> getPotlucks()
    {
        return potlucks;
    }

    public void setPotlucks(Set<Potluck> potlucks)
    {
        this.potlucks = potlucks;
    }

    /**
     * List of all roles that a user has
     * @return
     */
    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for(UserRoles r : this.roles) {
            String myRole = "ROLE" + r.getRole()
                .getName()
                .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }
        return rtnList;
    }

}
