package com.lambdaschool.potluckbackend.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "guests")
public class Guest
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long guestid;

    @NotNull
    private String guestname;

    @ManyToOne
    @JoinColumn(name = "potluckid", nullable = false)
    @JsonIgnoreProperties(value = "guests")
    private Potluck potluck;

    public Guest()
    {
    }

    public Guest(
        String guestname,
        Potluck potluck)
    {
        this.guestname = guestname;
        this.potluck = potluck;
    }

    public long getGuestid()
    {
        return guestid;
    }

    public void setGuestid(long guestid)
    {
        this.guestid = guestid;
    }

    public String getGuestname()
    {
        return guestname;
    }

    public void setGuestname(String guestname)
    {
        this.guestname = guestname;
    }

    public Potluck getPotluck()
    {
        return potluck;
    }

    public void setPotluck(Potluck potluck)
    {
        this.potluck = potluck;
    }
}
