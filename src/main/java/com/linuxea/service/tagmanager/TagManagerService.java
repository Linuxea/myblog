package com.linuxea.service.tagmanager;

import com.linuxea.model.Tag;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class TagManagerService {

    public static final TagManagerService SERVICE = new TagManagerService();

    public boolean add(Tag tag) {
        return tag.save();
    }

    public boolean update(Tag tag) {
        return tag.update();
    }

    public boolean delete(Tag tag) {
        return tag.delete();
    }

    public void find() {
    }

}
