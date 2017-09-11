package com.linuxea.controller.classmanager;

import com.linuxea.controller.base.BaseController;
import com.linuxea.model.Kind;
import com.linuxea.service.classmanager.ClassManagerService;

/**
 * 文章类别管理
 * Created by Linuxea on 2017-09-10.
 */
public class ClassManagerController extends BaseController {

    private ClassManagerService service = ClassManagerService.service;

    /**
     * 跳转主页
     */
    public void index() {

    }

    /**
     * 添加类别
     */
    public void add() {
        service.add(getModel(Kind.class));
    }

    /**
     * 更新类别
     */
    public void update() {
        service.update(getModel(Kind.class));
    }

    /**
     * 删除类别
     */
    public void delete() {
        service.update(getModel(Kind.class));
    }

}
