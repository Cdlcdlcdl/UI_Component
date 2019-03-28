# UI_Component
主界面

![](https://i.loli.net/2019/03/28/5c9cca0155916.png)

1.SimpleAdapter
'''android
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
'''

![](https://i.loli.net/2019/03/28/5c9cca72610a7.png)
