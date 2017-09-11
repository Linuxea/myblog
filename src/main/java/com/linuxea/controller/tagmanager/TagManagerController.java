package com.linuxea.controller.tagmanager;

import com.linuxea.controller.base.BaseController;
import com.linuxea.model.Tag;
import com.linuxea.service.tagmanager.TagManagerService;

/**
 * 标签管理
 * Created by Linuxea on 2017-09-11.
 */
public class TagManagerController extends BaseController {

    private static final TagManagerService SERVICE = TagManagerService.SERVICE;

    public void index() {
        super.renderJson("hello");
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
