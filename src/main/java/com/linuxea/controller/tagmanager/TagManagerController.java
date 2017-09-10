package com.linuxea.controller.tagmanager;

import com.jfinal.core.Controller;
import com.linuxea.model.Tag;
import com.linuxea.service.tagmanager.TagManagerService;

/**
 * 标签管理
 * Created by Linuxea on 2017-09-11.
 */
public class TagManagerController extends Controller {

    private static final TagManagerService SERVICE = TagManagerService.SERVICE;

    public void index() {

    }

    public void add() {
        SERVICE.add(getModel(Tag.class));
    }

    public void delete() {
        SERVICE.delete(getModel(Tag.class));
    }

    public void update() {
        SERVICE.update(getModel(Tag.class));
    }

    public void find() {

    }

}
