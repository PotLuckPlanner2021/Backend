package com.lambdaschool.potluckbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "potlucks")
public class Potluck
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long potluckid;

  @NotNull
  @Column(unique = true)
  private String name;
  private String date;
  private String time;
  private String location;
  private String Host;
  private String theme;

  @OneToMany(mappedBy = "potluck",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
  @JsonIgnoreProperties(value = "potluck",
      allowSetters = true)
   private List<Guest> guests = new ArrayList<>();

  @OneToMany(mappedBy = "potluck",
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @JsonIgnoreProperties(value = "potluck",
      allowSetters = true)
  private List<FoodItems> items = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "userid", nullable = false)
  @JsonIgnoreProperties(value = "potlucks")
  private User user;



  public Potluck()
  {
  }

  public Potluck(
      @NotNull String name,
      String date,
      String time,
      String location,
      String host,
      String theme)
  {
    this.name = name;
    this.date = date;
    this.time = time;
    this.location = location;
    Host = host;
    this.theme = theme;
  }

  public long getPotluckid()
  {
    return potluckid;
  }

  public void setPotluckid(long potluckid)
  {
    this.potluckid = potluckid;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getDate()
  {
    return date;
  }

  public void setDate(String date)
  {
    this.date = date;
  }

  public String getTime()
  {
    return time;
  }

  public void setTime(String time)
  {
    this.time = time;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getHost()
  {
    return Host;
  }

  public void setHost(String host)
  {
    Host = host;
  }

  public String getTheme()
  {
    return theme;
  }

  public void setTheme(String theme)
  {
    this.theme = theme;
  }

  public List<Guest> getGuests()
  {
    return guests;
  }

  public void setGuests(List<Guest> guests)
  {
    this.guests = guests;
  }



  public User getUser()
  {
    return user;
  }

  public void setUser(User user)
  {
    this.user = user;
  }

  public List<FoodItems> getItems()
  {
    return items;
  }

  public void setItems(List<FoodItems> items)
  {
    this.items = items;


  }
}
