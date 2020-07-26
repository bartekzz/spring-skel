package com.cepheid.cloud.skel;

import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.model.State;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class ItemControllerTest extends TestBase {

  @Test
  public void testGetItemsSize() {
    Builder itemController = getBuilder("/app/api/1.0/items");

    Response response = itemController.get();
    List<Item> items = response.readEntity(new GenericType<List<Item>>() {});
    assertTrue(items.size() > 0);
  }

  @Test
  public void testItemsStatus() {
    Builder itemController = getBuilder("/app/api/1.0/items");

    Response response = itemController.get();
    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

  @Test
  public void testItemPayload() {
    Builder itemController = getBuilder("/app/api/1.0/item/id/1");

    Response response = itemController.get();
    Item item = response.readEntity(Item.class);
    assertEquals(Long.valueOf(1), item.getId());
  }

  @Test
  public void testItemMediaType() {
    Builder itemController = getBuilder("/app/api/1.0/item/id/1");

    Response response = itemController.get();
    MediaType mediaType = response.getMediaType();
    assertEquals(MediaType.APPLICATION_JSON, mediaType.toString());
  }

  @Test
  public void testItemState() {
    Builder itemController = getBuilder("/app/api/1.0/item/id/1");

    Response response = itemController.get();
    Item item = response.readEntity(Item.class);
    assertTrue(State.contains(item.getState()));
  }

}
