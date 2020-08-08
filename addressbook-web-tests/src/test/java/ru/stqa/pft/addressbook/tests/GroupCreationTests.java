package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before= app.getGroupHelper().getGroupList();
    app.getGroupHelper().createNewGroup(new GroupData("group3", "group3(Logo)", "group3(Comment)"));
    List<GroupData> after= app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() + 1,after.size());
  }

  @Test
  public void testGroupCreationEmpty() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before= app.getGroupHelper().getGroupList();
    app.getGroupHelper().createNewGroup(new GroupData(null, null, null));
    List<GroupData> after= app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() + 1,after.size());
  }
}