package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String middlename;
  private final String lastname;
  private final String firstname;
  private final String nickname;
  private final String title;
  private final String company;
  private final String address;
  private final String home;
  private final String mobile;
  private final String workphone;
  private final String email;
  private String group;


  public ContactData(String middlename, String lastname, String firstname, String nickname, String title, String company, String address, String home, String mobile, String workphone, String email, String group) {
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
}
