# UI_Component
主界面

![](https://i.loli.net/2019/03/28/5c9cca0155916.png)

1.SimpleAdapter
```android
package com.example.pc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.graphics.Color;


public class SimpleAdapter extends Activity {

    private ListView listView;   //定义ListView对象，用来获取布局文件中的ListView控件
    private String[] name = {"Lion","Tiger","Monkey","Dog","Cat","Elephant"};  //定义一个名字数组，用来为数据源提供动物名字
    private int[] images = {R.drawable.lion,R.drawable.tiger,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};//定义一个 整形数组，用来为数据源中的头像
    private List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>(); //定义适配器对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        listView = (ListView) findViewById(R.id.listView); //获取布局文件中的ListView对象

        //1.准备好数据源，循环为listView添加数据（数据源的准备工作，这里是模拟从SQLite中查询数据）
        for(int i=0;i<6;i++){
            Map<String,Object> items = new HashMap<String, Object>(); //创建一个键值对的Map集合，用来存放名字和头像
            items.put("pic", images[i]);  //放入头像， 根据下标获取数组
            items.put("name", name[i]);      //放入名字， 根据下标获取数组
            list_map.add(items);   //把这个存放好数据的Map集合放入到list中，这就完成类数据源的准备工作
        }

        //2、创建适配器（可以使用外部类的方式、内部类方式等均可）
        android.widget.SimpleAdapter simpleAdapter = new android.widget.SimpleAdapter(
                SimpleAdapter.this,/*传入一个上下文作为参数*/
                list_map,         /*传入相对应的数据源，这个数据源不仅仅是数据而且还是和界面相耦合的混合体。*/
                R.layout.list_items, /*设置具体某个items的布局，需要是新的布局，而不是ListView控件的布局*/
                new String[]{"pic","name"}, /*传入上面定义的键值对的键名称,会自动根据传入的键找到对应的值*/
                new int[]{R.id.items_imageView1,R.id.items_textView1});/*传入items布局文件中需要指定传入的控件，这里直接上id即可*/

        //3、为listView加入适配器
        listView.setAdapter(simpleAdapter);
        //4.使用Toast显示选中的列表项信息
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast=Toast.makeText(SimpleAdapter.this,name[position],Toast.LENGTH_LONG);
                toast.show();
                for(int i=0;i<parent.getCount();i++){
                 View v=parent.getChildAt(i);
                if (position == i) {
                        v.setBackgroundColor(Color.RED);  //需要导入android.graphics.Color
                         } else {
                         v.setBackgroundColor(Color.TRANSPARENT);
                         }
                    }
            }
        } );

    };
}
```

![](https://i.loli.net/2019/03/28/5c9cca72610a7.png)

2.Alertdialog

```android
<?xml version="1.0" encoding="UTF-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:baselineAligned="false"
    android:id="@+id/view">

    <TextView
        android:id="@+id/tv_msg"
        android:text="ANOROIO APP"
        android:textSize="30dp"
        android:gravity="center"
        android:textColor="#ffffff"
        android:background="#f9aa0b"
        android:layout_width="fill_parent"
        android:layout_height="75dp" />
    <EditText
        android:textSize="20dp"
        android:id="@+id/username"
        android:layout_marginTop="15dp"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:hint="Username" />
    <EditText
        android:textSize="20dp"
        android:id="@+id/password"
        android:layout_marginTop="15dp"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:hint="Password" />

    <TableLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <TableRow
            android:background="#CCCCCC"
            android:weightSum="2"
            android:orientation="horizontal"
            android:paddingLeft="0.5dip"
            android:paddingTop="1dip"
            android:paddingRight="0.5dip">

            <Button
                android:id="@+id/btn_cancle"
                android:layout_weight="1"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:layout_marginLeft="0.5dip"
                android:layout_marginBottom="0.5dip"
                android:background="#ffffff"
                android:text="Cancel"
                android:textAllCaps="false"
                android:textColor="#000000" />

            <Button
                android:id="@+id/btn_ok"
                android:layout_weight="1"
                android:layout_height="wrap_content"
                android:layout_gravity="center_vertical"
                android:layout_marginLeft="0.5dip"
                android:layout_marginBottom="0.5dip"
                android:background="#ffffff"
                android:text="Sign in"
                android:textAllCaps="false"
                android:textColor="#000000" />
        </TableRow>
    </TableLayout>
</LinearLayout>
```

![](https://i.loli.net/2019/03/28/5c9cce6f90f98.png)

3.Xmlmenu

``` android
package com.example.pc;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class XmlMenu extends AppCompatActivity {


    private EditText txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        txt=findViewById(R.id.txt);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }
    //在JAVA文件中对资源文件进行加载
    @Override
    public boolean onCreateOptionsMenu(Menu menu)//开发选项菜单重写的方法
    {
        MenuInflater inflater = new MenuInflater(this);//菜单动态加载类
        inflater.inflate(R.menu.menu_main,menu);//调用inflate方法解析菜单文件
        super.onCreateOptionsMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mi){
        txt=findViewById(R.id.txt);
        switch (mi.getItemId()){
            case R.id.font_10:
                txt.setTextSize(20);
                break;
            case R.id.font_16:
                txt.setTextSize(32);
                break;
            case R.id.font_20:
                txt.setTextSize(40);
                break;
            case R.id.red_font:
                txt.setTextColor(Color.RED);
                break;
            case R.id.black_font:
                txt.setTextColor(Color.BLACK);
                break;
            case R.id.plain_item:
                Toast toast =Toast.makeText(XmlMenu.this,"这是普通单击项",Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }
}
```
![](https://i.loli.net/2019/03/28/5c9ccf9c7a971.png)


4.ActionMode

```
package  com.example.pc;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class action_mode extends AppCompatActivity {

    ListView listView = null;
    String[] data = {"One","Two","Three","Four","Five","Six"};
    List<Map<String,Object>> list = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        list = getData(data);
        final SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.item,
        new String[]{"text","checked"},new int[]{R.id.text,R.id.checkbox});
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            int i=0;
            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                Log.d("cd", String.valueOf(checked)+position);

                list.get(position).put("checked",checked);

                if(checked){
                    View v=listView.getChildAt(position);
                    v.setBackgroundColor(Color.GREEN);
                    i++;
                }
                else{
                    View v=listView.getChildAt(position);
                    v.setBackgroundColor(Color.WHITE);
                    i--;
                }
                mode.setTitle(i+" selected");
            }
            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.menu,menu);
                adapter.notifyDataSetChanged();
                //刷新每个item的内容
                mode.setTitle(i+" selected");
                return true;
            }
            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }
            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                Log.d("cc", "删除键");
                Iterator it = list.iterator();
                int num=0;
                while(it.hasNext()) {
                    Map map = (Map) it.next();

                    if((Boolean)map.get("checked")) {

                        View v=listView.getChildAt(num);
                        Log.d("删除项", ""+i);
                        v.setBackgroundColor(Color.WHITE);
                        it.remove();
                        i--;
                    }
                    num++;
                }
                adapter.notifyDataSetChanged();
                mode.finish();
                return true;
            }
            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {
                adapter.notifyDataSetChanged();
            }
        });
    }
    private List<Map<String,Object>> getData(String[] data) {

        List<Map<String,Object>> list = new ArrayList<>();
        for(int i = 0;i < data.length;i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("text",data[i]);
            map.put("checked",false);
            list.add(map);
        }
        return list;
    }
}
```

![](https://i.loli.net/2019/03/28/5c9cd004c6229.png)

