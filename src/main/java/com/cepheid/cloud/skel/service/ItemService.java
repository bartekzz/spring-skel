package com.cepheid.cloud.skel.service;

import com.cepheid.cloud.skel.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService
{
    public abstract List<Item> getItems();
    public abstract Optional<Item> getItemById(Long id);
    public abstract List<Item> getItemByName(String name);
    public abstract List<Item> getItemByState(String state);
    public abstract List<Item> getItemsByDescriptionsContains(String description);
    public abstract Item createItem(Item item);
    public abstract Item createItemIfPossible(Long id, Item item);
    public abstract void updateItem(Long id, Item Item);
    public abstract boolean updateItemIfPossible(Long id, Item item);
    public abstract void deleteItem(Long id);
}
