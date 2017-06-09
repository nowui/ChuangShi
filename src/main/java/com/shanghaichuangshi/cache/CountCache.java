package com.shanghaichuangshi.cache;

import com.shanghaichuangshi.dao.CountDao;
import com.shanghaichuangshi.model.Count;
import com.shanghaichuangshi.util.CacheUtil;

import java.math.BigDecimal;

public class CountCache extends Cache {

    public static final String COUNT_BY_COUNT_TYPE_AND_OBJECT_ID_AND_COUNT_KEY_CACHE = "count_by_count_type_and_object_id_and_count_key_cache";

    private final CountDao countDao = new CountDao();

    public Count find(String count_type, String object_id, String count_key) {
        Count count = CacheUtil.get(COUNT_BY_COUNT_TYPE_AND_OBJECT_ID_AND_COUNT_KEY_CACHE, count_type + "_" + object_id + "_" + count_key);

        if (count == null) {
            count = countDao.find(count_type, object_id, count_key);

            CacheUtil.put(COUNT_BY_COUNT_TYPE_AND_OBJECT_ID_AND_COUNT_KEY_CACHE, count_type + "_" + object_id + "_" + count_key, count);
        }

//        if (count == null) {
//            Count c = new Count();
//            c.setObject_id(object_id);
//            c.setCount_type(count_type);
//            c.setCount_key(count_key);
//            c.setCount_value(Constant.ZERO);
//            count = save(c, request_user_id);
//
//            CacheUtil.put(COUNT_BY_COUNT_TYPE_AND_OBJECT_ID_AND_COUNT_KEY_CACHE, count_type + "_" + object_id + "_" + count_key, count);
//        }

        return count;
    }

    public Count saveOrUpdate(String count_type, String object_id, String count_key, String count_value) {
        Count count = find(count_type, object_id, count_key);
        String request_user_id = "";

        if (count == null) {
            count = new Count();
            count.setObject_id(object_id);
            count.setCount_type(count_type);
            count.setCount_key(count_key);
            count.setCount_value(count_value);

            count = countDao.save(count, request_user_id);
        } else {
            if (count.getCount_value().contains(".") || count_value.contains(".")) {
                count.setCount_value(new BigDecimal(count.getCount_value()).add(new BigDecimal(count_value)).setScale(2, BigDecimal.ROUND_HALF_UP).toString());
            } else {
                count.setCount_value(String.valueOf(Integer.valueOf(count.getCount_value()) + Integer.valueOf(count_value)));
            }

            countDao.update(count, request_user_id);
        }

        CacheUtil.put(COUNT_BY_COUNT_TYPE_AND_OBJECT_ID_AND_COUNT_KEY_CACHE, count_type + "_" + object_id + "_" + count_key, count);

        return count;
    }

    public boolean delete(String count_id, String request_user_id) {
        return countDao.delete(count_id, request_user_id);
    }

}