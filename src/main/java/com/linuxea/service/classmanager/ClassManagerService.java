package com.linuxea.service.classmanager;

import com.linuxea.model.Kind;

/**
 * Created by Linuxea on 2017-09-10.
 */
public class ClassManagerService {

	public static final ClassManagerService service = new ClassManagerService();

	public boolean add(Kind kind) {
		return kind.save();
	}

	public boolean delete(String id) {
		Kind kind = new Kind();
		kind.setId(id);
		return kind.delete();
	}

	public boolean update(Kind kind) {
		return kind.update();
	}

}
