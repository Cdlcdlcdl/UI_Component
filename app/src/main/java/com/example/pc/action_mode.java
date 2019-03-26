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