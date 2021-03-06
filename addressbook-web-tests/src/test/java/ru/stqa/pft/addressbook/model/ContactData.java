package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="addressbook")

public class ContactData {
  @Id
  @Column
  private int id=Integer.MAX_VALUE;
  @Expose
  @Column
  private String firstname;
  @Expose
  @Column
  private String middlename;
  @Expose
  @Column
  private String lastname;
 // @Expose
 // @Transient
 // private String group;
  @Transient
  private String nickname;
  @Transient
  private String title;
  @Transient
  private String company;
  @Transient
  private String address;
  @Column(name= "home")
  @Type(type="text")
  private String homephone;
  @Column(name= "mobile")
  @Type(type="text")
  private String mobile;
  @Type(type="text")
  @Column(name= "work")
  private String workphone;
  @Transient
  private String email;
  @Transient
  private String email2;
  @Transient
  private String email3;
  @Transient
  private String allphones;
  @Transient
  private String allemail;
  @Column(name= "photo")
  @Type(type="text")
  private String photo;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name= "address_in_groups",joinColumns = @JoinColumn(name="id"),
          inverseJoinColumns = @JoinColumn(name ="group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", homephone='" + homephone + '\'' +
            ", mobile='" + mobile + '\'' +
            ", workphone='" + workphone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(middlename, that.middlename) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(homephone, that.homephone) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(workphone, that.workphone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, middlename, lastname, homephone, mobile, workphone);
  }

  public File getPhoto() {
    if(photo!=null) {
      return new File(photo);
    }else {
      return null;
    }
  }

  public String getAllphones() {
   return allphones;
 }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllemail() {
    return allemail;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getTitle() {
    return title;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWorkphone() {
    return workphone;
  }

  public String getEmail() {
    return email;
  }

 /* public String getGroup() {
    return group;
  }*/
  public Groups getGroups() {
    return new Groups(groups);
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withAllphones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withHomephone(String home) {
    this.homephone = home;
    return this;
  }

  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkphone(String workphone) {
    this.workphone = workphone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllemail(String allemail) {
    this.allemail = allemail;
    return this;
  }

  public ContactData inGroup(GroupData group) {
     groups.add(group);
     return this;
  }

 /* public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }*/

}