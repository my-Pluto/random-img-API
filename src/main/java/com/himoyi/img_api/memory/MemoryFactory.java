package com.himoyi.img_api.memory;

import com.himoyi.img_api.utils.PropertiesUtils;

public class MemoryFactory {
    public Memory getMemory() {
        String type = PropertiesUtils.getName();
        if (type.equals("UpYun")) {
            return new MemoryImplByUpYun();
        }
        else return null;
    }

}
