package kr.hs.dgsw.flow.network.networkModel.Notice;

import com.google.gson.annotations.SerializedName;

public class NoticeFiles {
    private long idx;
    @SerializedName("upload_name")
    private String uploadName;
    @SerializedName("upload_dir")
    private String uploadDir;
    @SerializedName("notice_idx")
    private long noticeIdx;

    public long getIdx() { return idx; }
    public void setIdx(long value) { this.idx = value; }

    public String getUploadName() { return uploadName; }
    public void setUploadName(String value) { this.uploadName = value; }

    public String getUploadDir() { return uploadDir; }
    public void setUploadDir(String value) { this.uploadDir = value; }

    public long getNoticeIdx() { return noticeIdx; }
    public void setNoticeIdx(long value) { this.noticeIdx = value; }
}
