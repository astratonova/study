package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

  private int id=Integer.MAX_VALUE;;
  private String middlename;
  private String lastname;
  private String firstname;
  private String nickname;
  private String title;
  private String company;
  private String address;
  private String home;
  private String mobile;
  private String workphone;
  private String email;
  private String group;

 /* public ContactData(int id,String middlename, String lastname, String firstname, String nickname, String title, String company, String address, String home, String mobile, String workphone, String email, String group) {
    this.id=id;
    this.middlename = middlename;
    this.lastname = lastname;
    this.firstname = firstname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.workphone = workphone;
    this.email = email;
    this.group = group;
  }

   public ContactData(String middlename, String lastname, String firstname, String nickname, String title, String company, String address, String home, String mobile, String workphone, String email, String group) {
    this.id=Integer.MAX_VALUE;
    this.middlename = middlename;
    this.lastname = lastname;
    this.firstname = firstname;
    this.nickname = nickname;
    this.title = title;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.workphone = workphone;
    this.email = email;
    this.group = group;
  }
  */
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

  public String getHome() {
    return home;
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

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public ContactData withId(int id) {
    this.id = id;
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

  public ContactData withHome(String home) {
    this.home = home;
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

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", lastname='" + lastname + '\'' +
            ", firstname='" + firstname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(firstname, that.firstname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, lastname, firstname);
  }
}