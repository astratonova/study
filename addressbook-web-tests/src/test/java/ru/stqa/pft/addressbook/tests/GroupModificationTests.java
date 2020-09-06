package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().groups().size()==0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("group3"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before= app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group= new GroupData()
            .withId(modifiedGroup.getId()).withName("group4")
            .withHeader("group4(Logo)").withFooter("group4(Comment)");
    app.goTo().groupPage();
    app.group().modify(group);
    Groups after= app.db().groups();
    assertThat(before.size(), equalTo(after.size()));
    assertThat(before.without(modifiedGroup).withAdded(group),equalTo(after));

    verifyGroupListInUI();
  }


}
