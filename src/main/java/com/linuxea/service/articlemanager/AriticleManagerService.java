package com.linuxea.service.articlemanager;

import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class AriticleManagerService {

    public static final AriticleManagerService SERVICE = new AriticleManagerService();

    public Kv add(Model<?> model) {
        Kv kv = Kv.create();
        if (model.save()) {
            kv.set("stateCode", "ok");
        } else {
            kv.set("stateCode", "notok");
        }
        return kv;
    }

    public boolean update(Model<?> model) {
        return model.update();
    }

    public boolean delete(Model<?> model) {
        return model.delete();
    }

    /**
     * 列出文章每十条
     */
    public void find() {

    }

}
