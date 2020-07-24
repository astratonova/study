package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("group3", "group3(Logo)", "group3(Comment)"));
    submitGroup();
    returnToGroupPage();
  }

}