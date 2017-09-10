package com.linuxea.service.classmanager;

import com.jfinal.plugin.activerecord.Model;
import com.linuxea.model.Kind;

/**
 * Created by Linuxea on 2017-09-10.
 */
public class ClassManagerService {

    public static final ClassManagerService service = new ClassManagerService();

    public boolean add(Model<?> model) {
        return model.save();
    }

    public boolean delete(String id) {
        Kind kind = new Kind();
        kind.setId(id);
        return kind.delete();
    }

    public boolean update(Model<?> model) {
        return model.update();
    }

}
