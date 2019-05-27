package com.sensetime.greendaodemo.db.manage;

import android.content.Context;
import android.util.Log;

import com.sensetime.greendaodemo.db.dao.DaoMaster;
import com.sensetime.greendaodemo.db.dao.DaoSession;
import com.sensetime.greendaodemo.db.dao.FaceInfoDao;
import com.sensetime.greendaodemo.db.entity.FaceInfo;

import org.greenrobot.greendao.database.Database;

import java.util.List;

/**
 * @author qinhaihang_vendor
 * @version $Rev$
 * @time 2019/5/27 14:08
 * @des
 * @packgename com.sensetime.greendaodemo.db.manage
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes
 */
public class FaceInfoDbManager {

    public static final String DB_NAME = "faceInfos.db";
    private DaoMaster.DevOpenHelper mOpenHelper;
    private Database mDb;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private FaceInfoDao mFaceInfoDao;

    private static class SingletonHolder {
        private static final FaceInfoDbManager INSTANCE = new FaceInfoDbManager();
    }

    public static FaceInfoDbManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public FaceInfoDbManager() {

    }

    public void initDB(Context context) {
        mOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        mDb = mOpenHelper.getWritableDb();
        mDaoMaster = new DaoMaster(mDb);
        mDaoSession = mDaoMaster.newSession();
        mFaceInfoDao = mDaoSession.getFaceInfoDao();
    }

    /**
     * 插入一个新的数据
     *
     * @param faceInfo
     * @return id 主键
     */
    public long insert(FaceInfo faceInfo) {
        long id = 0;
        try {
            id = mFaceInfoDao.insert(faceInfo);
        } catch (Exception e) {
            throw new RuntimeException("insert data is fail e = " + e.getMessage());
        }

        return id;
    }

    /**
     * 批量插入数据
     * @param faceInfos
     */
    public void insert(final List<FaceInfo> faceInfos){
        mDaoSession.runInTx(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                mFaceInfoDao.insertInTx(faceInfos);
                long comsumeTime = System.currentTimeMillis() - startTime;
                Log.i("qhh","insert time = " + comsumeTime);
            }
        });
    }

    /**
     * 根据主键进行删除
     * @param key
     */
    public void deleteByKey(long key) {
        try {
            mFaceInfoDao.deleteByKey(key);
        }catch (Exception e){
            throw new RuntimeException("delete key = " + key + "is fail e = " + e.getMessage());
        }
    }

    /**
     * 根据实体类进行删除
     * @param faceInfo
     */
    public void deleteByEntity(FaceInfo faceInfo){
        try{
            mFaceInfoDao.delete(faceInfo);
        }catch (Exception e){
            throw new RuntimeException("delete entity is fail e = " + e.getMessage());
        }
    }

    /**
     * 删除整个表
     */
    public void deleteAll(){
        try{
            mFaceInfoDao.deleteAll();
        }catch (Exception e){
            throw new RuntimeException("deleteAll is fail e = " + e.getMessage());
        }
    }

    /**
     * 根据实体类进行更新
     * @param faceInfo
     */
    public void update(FaceInfo faceInfo){
        try{
            mFaceInfoDao.update(faceInfo);
        }catch (Exception e){
            throw new RuntimeException("update is fail e = " + e.getMessage());
        }
    }

    /**
     * 查询全部数据
     * @return
     */
    public List<FaceInfo> queryAll(){
        List<FaceInfo> faceInfos = null;
        try{
            faceInfos = mFaceInfoDao.loadAll();
        }catch (Exception e){

        }
        return faceInfos;
    }

    /**
     * 通过查询是否发送过获取数据
     * @param isSend
     * @return
     */
    public List<FaceInfo> querySended(boolean isSend){
        List<FaceInfo> faceInfos = null;
        try{
            faceInfos = mFaceInfoDao.queryBuilder().where(FaceInfoDao.Properties.IsSend.eq(isSend)).list();
        }catch (Exception e){

        }
        return faceInfos;
    }

    /**
     * 查询小于指定时间的数据
     * @param time
     * @return
     */
    public List<FaceInfo> queryByTime(long time){
        List<FaceInfo> list = mFaceInfoDao.queryBuilder().where(FaceInfoDao.Properties.Time.lt(time)).list();
        return list;
    }

    /**
     * 关闭数据库
     */
    public void closeFaceInfoDb(){
        if(mOpenHelper != null){
            mOpenHelper.close();
            mOpenHelper = null;
        }
        if(mDaoSession != null){
            mDaoSession.clear();
            mDaoSession = null;
        }
    }
}
