package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before= app.getGroupHelper().getGroupList();
    GroupData group= new GroupData("group3", "group3(Logo)", "group3(Comment)");
    app.getGroupHelper().createNewGroup(group);

    List<GroupData> after= app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() + 1,after.size());

    int max=0;
    for (GroupData g : after){
      if (max< g.getId()) {
        max=g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }

  @Test
  public void testGroupCreationEmpty() {
    app.getNavigationHelper().goToGroupPage();
    List<GroupData> before= app.getGroupHelper().getGroupList();
    GroupData group= new GroupData("groupName", null, null);
    app.getGroupHelper().createNewGroup(group);

    List<GroupData> after= app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() + 1,after.size());

    int max=0;
    for (GroupData g : after){
      if (max< g.getId()) {
        max=g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}