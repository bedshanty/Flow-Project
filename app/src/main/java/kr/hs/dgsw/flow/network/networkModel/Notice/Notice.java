package kr.hs.dgsw.flow.network.networkModel.Notice;

import com.google.gson.annotations.SerializedName;

public class Notice {
    private long idx;
    private String content;
    private String writer;
    @SerializedName("write_date")
    private String writeDate;
    @SerializedName("modify_date")
    private String modifyDate;
    @SerializedName("notice_files")
    private NoticeFiles[] noticeFiles;

    public long getIdx() { return idx; }
    public void setIdx(long value) { this.idx = value; }

    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }

    public String getWriter() { return writer; }
    public void setWriter(String value) { this.writer = value; }

    public String getWriteDate() { return writeDate; }
    public void setWriteDate(String value) { this.writeDate = value; }

    public String getModifyDate() { return modifyDate; }
    public void setModifyDate(String value) { this.modifyDate = value; }

    public NoticeFiles[] getNoticeFiles() { return noticeFiles; }
    public void setNoticeFiles(NoticeFiles[] value) { this.noticeFiles = value; }
}
