package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().groupPage();
    if (app.group().all().size()==0) {
      app.group().create(new GroupData().withName("group3"));
    }
  }

  @Test
  public void testGroupDeletion()  {
    Groups before= app.group().all();

    GroupData deletedGroup= before.iterator().next();
    app.group().delete(deletedGroup);

    Groups after= app.group().all();
    assertThat( before.size()-1,equalTo(after.size()));
    assertThat(before.without(deletedGroup), equalTo(after));
  }

}
