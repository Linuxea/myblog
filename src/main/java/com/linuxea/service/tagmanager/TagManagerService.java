package com.linuxea.service.tagmanager;

import com.jfinal.plugin.activerecord.Model;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class TagManagerService {

    public static final TagManagerService SERVICE = new TagManagerService();

    public boolean add(Model<?> model) {
        return model.save();
    }

    public boolean update(Model<?> model) {
        return model.update();
    }

    public boolean delete(Model<?> model) {
        return model.delete();
    }

    public void find() {
    }

}
