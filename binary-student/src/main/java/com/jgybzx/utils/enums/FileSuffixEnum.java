package com.jgybzx.utils.enums;

/**
 * @author jgybzx
 * @date 2021/3/4 11:12
 * @description
 */
public enum FileSuffixEnum {
    /**
     * 文件后缀
     */
    BMP(".bmp ", ".bmp"),
    GIF(".gif ", ".gif"),
    JPEG(".jpeg", ".jpeg"),
    HTML(".html", ".html"),
    TXT(".txt ", ".txt"),
    VSD(".vsd ", ".vsd"),
    PPTX(".pptx", ".pptx"),
    PPT(".ppt", ".ppt"),
    DOCX(".docx", ".docx"),
    DOC(".doc", ".doc"),
    XML(".xml ", ".xml"),
    PDF(".pdf ", ".pdf");

    private String desc;
    private String code;

    FileSuffixEnum(String desc, String code) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
