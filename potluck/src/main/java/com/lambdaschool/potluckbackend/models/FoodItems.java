package com.lambdaschool.potluckbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "fooditems")
public class FoodItems
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long itemid;

    @NotNull
    private String itemname;

    @ManyToOne
    @JoinColumn(name = "potluckid", nullable = false)
    @JsonIgnoreProperties(value = "items")
    private Potluck potluck;

    public FoodItems()
    {
    }

    public FoodItems(
        @NotNull String itemname,
        Potluck potluck)
    {
        this.itemname = itemname;
        this.potluck = potluck;
    }

    public long getItemid()
    {
        return itemid;
    }

    public void setItemid(long itemid)
    {
        this.itemid = itemid;
    }

    public String getItemname()
    {
        return itemname;
    }

    public void setItemname(String itemname)
    {
        this.itemname = itemname;
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
