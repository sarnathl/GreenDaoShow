package com.liuyan.cn.greendao.gen;

import android.content.Context;

import com.liuyan.cn.bean.User;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by apple on 16/8/2.下午6:47
 */
public class DBUser {
    private Context context;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserDao userDao;
    private static DBUser dbUser;

    private DBUser(Context context) {
        this.context = context;
        //建立连接
        daoMaster = new DaoMaster(DBManager.getInstance(context).getReadableDatabase());
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }

    public static DBUser getInstance(Context context) {
        if (dbUser == null) {
            synchronized (DBUser.class) {
                if (dbUser == null) {
                    dbUser = new DBUser(context);
                }
            }
        }
        return dbUser;
    }

    /**
     * 插入
     */
    public void insertUser(User user) {
        // 我们插入前先判断user是否在数据库中
        if (queryById(user.getName()) != null) {
            return;
        }
        if (user != null) {
            userDao.insert(user);
        }
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> queryAllUser() {
        QueryBuilder<User> qb = userDao.queryBuilder();
        return qb.list();
    }

    /**
     * 根据名字来删除
     *
     * @param userName
     * @return
     */
    public void deleteByName(String userName) {
        QueryBuilder<User> qb = userDao.queryBuilder();
        DeleteQuery<User> dq = qb.where(UserDao.Properties.Name.eq(userName)).buildDelete();
        dq.executeDeleteWithoutDetachingEntities();
    }

    /**
     * 清楚数据
     */
    public void clear() {
        userDao.deleteAll();
    }

    /**
     * 更新数据
     */
    public void update(User user) {
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Name.eq(user.getName()));
        List<User> list = qb.list();
        if (list == null || list.size() == 0) {
            return;
        }
        User user1 = list.get(0);
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        userDao.insertOrReplace(user1);
    }

    public User queryById(String userName) {
        QueryBuilder<User> qb = userDao.queryBuilder();
        qb.where(UserDao.Properties.Name.eq(userName));
        if (qb.list() == null || qb.list().size() == 0) {
            return null;
        }
        return qb.list().get(0);
    }


}
