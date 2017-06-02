package com.shanghaichuangshi.controller;

import com.jfinal.core.ActionKey;
import com.shanghaichuangshi.constant.Url;
import com.shanghaichuangshi.util.CacheUtil;


import java.util.List;

public class ConfigController extends Controller {

    @ActionKey(Url.CONFIG_ADMIN_CACHE_DELETE)
    public void adminCacheDelete() {
        List<String> keyList = CacheUtil.getKeys(CacheUtil.CACHE_NAME_CACHE);

        for (String key : keyList) {
            CacheUtil.removeAll(key);
        }

        renderSuccessJson();
    }

}