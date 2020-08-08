package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    int before=app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createNewGroup(new GroupData("group3", "group3(Logo)", "group3(Comment)"));
    int after=app.getGroupHelper().getGroupCount();
    Assert.assertEquals(before + 1,after);
  }

  @Test
  public void testGroupCreationEmpty() {
    app.getNavigationHelper().goToGroupPage();
    int before=app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createNewGroup(new GroupData(null, null, null));
    int after=app.getGroupHelper().getGroupCount();
    Assert.assertEquals(before + 1,after);
  }
}