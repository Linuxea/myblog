package com.linuxea.controller.index;

import com.jfinal.core.Controller;

/**
 * Created by Linuxea on 2017-09-11.
 */
public class IndexController extends Controller {

    public void index() {
        renderJsp("index.html");
    }

}
