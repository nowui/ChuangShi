package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.model.Category;

public class CategoryCache extends Cache {

    private final String CATEGORY_CACHE_MODEL = "category_cache_model";

    public Category getCategoryByCategory_id(String category_id) {
        return (Category) ehcacheObject.get(CATEGORY_CACHE_MODEL + "_" + category_id);
    }

    public void setCategoryByCategory_id(Category category, String category_id) {
        ehcacheObject.put(CATEGORY_CACHE_MODEL + "_" + category_id, category);

        setMapByKeyAndId(CATEGORY_CACHE_MODEL, category_id);
    }

    public void removeCategoryByCategory_id(String category_id) {
        ehcacheObject.remove(CATEGORY_CACHE_MODEL + "_" + category_id);

        removeMapByKeyAndId(CATEGORY_CACHE_MODEL, category_id);
    }

    public void removeCategory() {
        ehcacheObject.removeAll(getMapByKey(CATEGORY_CACHE_MODEL));

        removeMapByKey(CATEGORY_CACHE_MODEL);
    }

}
