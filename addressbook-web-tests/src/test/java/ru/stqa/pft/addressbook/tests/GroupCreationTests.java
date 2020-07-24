package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {

    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("group3", "group3(Logo)", "group3(Comment)"));
    app.submitGroup();
    app.returnToGroupPage();
  }

}