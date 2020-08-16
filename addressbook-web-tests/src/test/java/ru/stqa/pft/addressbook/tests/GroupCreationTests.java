package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData()
            .withName("group3").withFooter("group3(Logo)").withHeader("group3(Comment)");
    app.group().create(group);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(before.size() + 1, after.size());

    before.add(group);
    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testGroupCreationEmpty() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("group3");
    app.group().create(group);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(before.size() + 1, after.size());

    before.add(group);
    Comparator<? super GroupData> byId= (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}