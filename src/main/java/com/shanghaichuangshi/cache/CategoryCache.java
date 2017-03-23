package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.model.Category;

public class CategoryCache extends Cache {

    private final String CATEGORY_OBJECT_CACHE = "category_object_cache";

    public Category getCategoryByCategory_id(String category_id) {
        return (Category) getObjectBykeyAndId(CATEGORY_OBJECT_CACHE, category_id);
    }

    public void setCategoryByCategory_id(Category category, String category_id) {
        setObjectBykeyAndId(category, CATEGORY_OBJECT_CACHE, category_id);
    }

    public void removeCategoryByCategory_id(String category_id) {
        removeObjectBykeyAndId(CATEGORY_OBJECT_CACHE, category_id);
    }

    public void removeCategory() {
        removeObjectByKey(CATEGORY_OBJECT_CACHE);
    }

}
