package com.cepheid.cloud.skel.service;

import com.cepheid.cloud.skel.model.Item;
import com.cepheid.cloud.skel.repository.ItemRepository;
import com.cepheid.cloud.skel.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService
{

    private final ItemRepository mItemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository)
    {
        mItemRepository = itemRepository;
    }

    @Override public List<Item> getItems()
    {
        return mItemRepository.findAll();
    }

    @Override public Optional<Item> getItemById(Long id)
    {
        return mItemRepository.findById(id);
    }

    @Override public List<Item> getItemByName(String name)
    {
        return mItemRepository.findByName(name);
    }

    @Override public List<Item> getItemByState(String state)
    {
        return mItemRepository.findByState(state);
    }

    @Override public List<Item> getItemsByDescriptionsContains(String description)
    {
        return mItemRepository.findDistinctByDescriptionsDescriptionContains(description);
    }

    @Override
    public Item createItem(Item item) {
        return mItemRepository.save(item);
    }

    @Override public Item createItemIfPossible(Long id, Item item)
    {
        List<Item> itemNames = mItemRepository.findByName(item.getName());
        if(CommonUtil.createItemPossible(itemNames, id)) {
            return mItemRepository.save(item);
        }

        return null;

    }

    @Override public void updateItem(Long id, Item Item)
    {

    }

    @Override public boolean updateItemIfPossible(Long id, Item item)
    {

        List<Item> itemNames = mItemRepository.findByName(item.getName());
        boolean updateItemPossible = CommonUtil.updateItemPossible(itemNames, id);

        Optional<Item> itemInDB = mItemRepository.findById(id);
        if (itemInDB.isPresent() && updateItemPossible)
        {
            itemInDB.get().setName(item.getName());
            itemInDB.get().setState(item.getState());
            itemInDB.get().setDescriptions(item.getDescriptions());
            mItemRepository.save(itemInDB.get());

            return true;
        }

        return false;
    }

    @Override public void deleteItem(Long id)
    {
        Optional<Item> item = mItemRepository.findById(id);
        if (item.isPresent())
        {
            mItemRepository.deleteById(id);
        }
    }

}
