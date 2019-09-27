package channeldemo.ZQSX;

import cn.hutool.core.codec.Base64;

import java.io.File;

public class VideoMessageCreateEntity {
    private String name;
    private String content;

    public VideoMessageCreateEntity(String name, File file) {
        this.name = name;
        this.content = Base64.encode(file);
    }

    public VideoMessageCreateEntity(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
