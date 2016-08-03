package com.liuyan.cn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.liuyan.cn.bean.User;
import com.liuyan.cn.greendao.gen.DBUser;

import java.util.ArrayList;
import java.util.List;

/**
 * 最新的greenDao 3 以前项目里用的都是2
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mEditText;
    private EditText mEditText2;
    private ListView mListView;

    private List<String> datas = new ArrayList<>();
    private User mUser;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mEditText = (EditText) findViewById(R.id.editText);
        mEditText2 = (EditText) findViewById(R.id.editText2);
        mListView = (ListView) findViewById(R.id.listView);

        List<User> list = DBUser.getInstance(this).queryAllUser();
        for (int i = 0; i < list.size(); i++) {
            datas.add(list.get(i).getName() + "----" + list.get(i).getAge());
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        mListView.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = mEditText.getText().toString().trim();
        String age = mEditText2.getText().toString().trim();
        mUser = new User(null, name, age);
        switch (v.getId()) {
            // 插入
            case R.id.button:
                DBUser.getInstance(this).insertUser(mUser);
                break;
            // 删除
            case R.id.button2:
                DBUser.getInstance(this).deleteByName(mEditText.getText().toString().trim());
                break;
            // 更新
            case R.id.button3:
                DBUser.getInstance(this).update(mUser);
                break;
            // 清除
            case R.id.button4:
                DBUser.getInstance(this).clear();
                break;
            default:
                break;
        }
        update();
    }

    /**
     * 我们不管是更新完按还是删除完，总得刷新一下列表吧？对不？
     */
    public void update() {
        List<User> list = DBUser.getInstance(this).queryAllUser();
        datas.clear();
        for (int i = 0; i < list.size(); i++) {
            datas.add(list.get(i).getName() + "----" + list.get(i).getAge());
        }
        adapter.notifyDataSetChanged();
    }

}
