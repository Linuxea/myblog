package com.linuxea.service.articlemanager;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class AriticleManagerService {

    public static final AriticleManagerService SERVICE = new AriticleManagerService();

    public boolean add(Model<?> model) {
        return model.save();
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
