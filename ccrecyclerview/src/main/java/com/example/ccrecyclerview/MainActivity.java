package com.example.ccrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.ccrecyclerview.base.CCRecycleViewListener;
import com.example.commonlibrary.utils.CCLog;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private TestRecyclerAdapt adapt;
    private List persons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        persons=new ArrayList();
        for (int i=0;i<200;i++){
            Person p=new Person("名字"+i,i+"岁");
            persons.add(p);
        }
        adapt=new TestRecyclerAdapt(this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapt);
        adapt.refreshData(persons);
        adapt.setOnItemClickListener(new CCRecycleViewListener.OnItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                CCLog.e("view "+position+" click");
            }
        });
        adapt.setOnItemLongClickListener(new CCRecycleViewListener.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(View v, int position) {
                CCLog.e("view "+position+" LongClick");
            }
        });
    }
}
