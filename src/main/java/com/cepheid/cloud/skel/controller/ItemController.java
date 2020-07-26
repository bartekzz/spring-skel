package com.cepheid.cloud.skel.controller;

import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.ItemRepository;
import com.cepheid.cloud.skel.service.ItemServiceImpl;
import com.cepheid.cloud.skel.util.CommonUtil;
import com.cepheid.cloud.skel.validator.group.ValidatedOnCreationOnly;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

// curl http:/localhost:9443/app/api/1.0/items

@Component
@Path("/api/1.0/")
@Api(value = "Item Resource REST Enpoint", description = "Shows item info")
public class ItemController
{

    @Autowired ItemServiceImpl itemServiceImpl;

    @GET
    @Path("items")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @ApiOperation(value = "Gets items resources.", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Items resources found"),
    })
    public Response getItems()
    {
        List<Item> items = itemServiceImpl.getItems();
        return Response.ok(items).build();
    }

    @GET
    @Path("item/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @ApiOperation(value = "Gets item resource by id.", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item resource found"),
            @ApiResponse(code = 404, message = "Item resource not found"),
    })
    public Response getItemById(@PathParam("id") Long id)
    {
        Optional<Item> item = itemServiceImpl.getItemById(id);
        if(!item.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(item).build();
        }

    }

    @GET
    @Path("item/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @ApiOperation(value = "Gets item resource by name.", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Items resource found"),
            @ApiResponse(code = 404, message = "Items resource not found"),
    })
    public Response getItemByName(@PathParam("name") String name)
    {
        List<Item> items = itemServiceImpl.getItemByName(name);
        if (items == null)
            return Response.noContent().build();
        return Response.ok(items).build();
    }

    @GET
    @Path("items/state/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @ApiOperation(value = "Gets item resource by state.", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Items resource found"),
            @ApiResponse(code = 404, message = "Items resource not found"),
    })
    public Response getItemByState(@PathParam("state") String state)
    {
        List<Item> items = itemServiceImpl.getItemByState(state);
        if (items == null)
            return Response.noContent().build();
        return Response.ok(items).build();
    }

    @GET
    @Path("items/description/{description}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    @ApiOperation(value = "Gets item resource by description.", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Items resource found"),
            @ApiResponse(code = 404, message = "Items resource not found"),
    })
    public Response getItemsByDescriptionsContains(@PathParam("description") String description)
    {
        List<Item> items = itemServiceImpl.getItemsByDescriptionsContains(description);
        if (items == null)
            return Response.noContent().build();
        return Response.ok(items).build();
    }

    @POST
    @Path("item")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @ApiOperation(value = "Add item resource", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Item resource created"),
            @ApiResponse(code = 400, message = "Item resource not valid"),
    })
    public Response createItem(@Valid @ConvertGroup(to = ValidatedOnCreationOnly.class) Item item,
            @Context UriInfo uriInfo) throws URISyntaxException
    {
        Item result = itemServiceImpl.createItem(item);
        return Response.created(new URI(
                String.format("%s/%s", uriInfo.getAbsolutePath().toString(),
                        result.getId())))
                .build();
    }

    @PUT
    @Path("item/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @ResponseStatus
    @ApiOperation(value = "Update item resource by id.", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Item resource updated"),
            @ApiResponse(code = 201, message = "Item resource created"),
            @ApiResponse(code = 400, message = "Item resource not valid")
    })
    public Response updateItem(@PathParam("id") Long id, @Valid @ConvertGroup(to = Default.class) Item item,
            @Context UriInfo uriInfo) throws URISyntaxException
    {

        boolean itemUpdated = itemServiceImpl.updateItemIfPossible(id, item);
        if (itemUpdated)
        {
            return Response.ok(item).build();
        }

        Item itemCreated = itemServiceImpl.createItemIfPossible(id, item);
        if(itemCreated != null)
        {
            return Response.created(new URI(
                    String.format("%s/%s", uriInfo.getAbsolutePath().toString(),
                            itemCreated.getId())))
                    .build();
        }

        return Response.status(400, "Name is already used").build();

    }

    @DELETE
    @Path("item/{id}")
    @ApiOperation(value = "Delete item resource by id.", response = Item.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Item resource deleted"),
            @ApiResponse(code = 204, message = "Item resource not found")
    })
    public Response deleteItem(@PathParam("id") Long id)
    {
        itemServiceImpl.deleteItem(id);
        return Response.noContent().build();
    }

}
