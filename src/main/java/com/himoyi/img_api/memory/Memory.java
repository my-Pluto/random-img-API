package com.himoyi.img_api.memory;

import java.util.List;

public interface Memory {
    List<String> getPath();
    String getMD5(String path);
    boolean delete(String path);
}
