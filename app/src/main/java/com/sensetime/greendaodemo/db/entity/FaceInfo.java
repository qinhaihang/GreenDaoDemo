package com.sensetime.greendaodemo.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author qinhaihang_vendor
 * @version $Rev$
 * @time 2019/5/27 13:56
 * @des
 * @packgename com.sensetime.greendaodemo.db.entity
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes
 */
@Entity
public class FaceInfo {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String faceId;

    @NotNull
    private int age;

    @NotNull
    private int gender;

    @NotNull
    private String faceImage;

    @NotNull
    private long time;

    @NotNull
    private boolean isSend;

    @Generated(hash = 641714782)
    public FaceInfo(Long id, @NotNull String faceId, int age, int gender,
            @NotNull String faceImage, long time, boolean isSend) {
        this.id = id;
        this.faceId = faceId;
        this.age = age;
        this.gender = gender;
        this.faceImage = faceImage;
        this.time = time;
        this.isSend = isSend;
    }

    @Generated(hash = 1003586454)
    public FaceInfo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean getIsSend() {
        return this.isSend;
    }

    public void setIsSend(boolean isSend) {
        this.isSend = isSend;
    }
}
