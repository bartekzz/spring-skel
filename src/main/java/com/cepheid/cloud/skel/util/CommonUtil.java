package com.cepheid.cloud.skel.util;

import com.cepheid.cloud.skel.model.Item;

import java.util.List;

public class CommonUtil
{

    public static boolean updateItemPossible(List<Item> itemList, Long id)
    {
        boolean updatePossible = false;
        if (itemList.size() == 0 || (itemList.size() == 1 && itemList.get(0).getId() == id))
        {
            updatePossible = true;
        }

        return updatePossible;
    }

    public static boolean createItemPossible(List<Item> itemList, Long id)
    {
        boolean createPossible = true;
        if(itemList.size() == 1 && itemList.get(0).getId() != id) {
            createPossible = false;
        }
        return createPossible;
    }
}
