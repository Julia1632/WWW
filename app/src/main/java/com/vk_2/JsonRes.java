package com.vk_2;

import java.util.concurrent.ExecutionException;

/**
 * Created by User on 09.01.2018.
 */

public class JsonRes {
    String res(String url, String Token) {
        String result = null;
        try {
            result = new Parse(Token).execute(url).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }


}
