package com.example.commonlibrary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.commonlibrary.model.CommonContants;
import com.example.commonlibrary.utils.StringUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LaunchShowCrashActivity extends AppCompatActivity {
    TextView common_log_text;
    Button common_jump_btn;

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_show_crash);
        common_log_text = (TextView) findViewById(R.id.common_log_text);
        common_jump_btn= (Button) findViewById(R.id.common_jump_btn);
        MyTask task =new MyTask(common_log_text);
        task.execute();
        common_jump_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCrashLog();
                jumpMainActivity();
            }


        });
    }
    private void deleteCrashLog() {
        File f=new File(CommonContants.crashLogFile);
        f.delete();
    }
    private void jumpMainActivity() {

        Intent intent = new Intent(CommonContants.launchAction);
        startActivity(intent);
    }
    /**
     * 异步加载数据和展示
     */
    public class MyTask extends AsyncTask<String, Integer, String> {
        TextView tv;

        public MyTask(TextView tv) {
            this.tv = tv;
        }

        @Override
        protected String doInBackground(String... strings) {
            return getCrashLogStr();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!StringUtil.isEmpty(s))
                tv.setText(s);
            else {
                jumpMainActivity();
            }
        }


    }

    /**
     * 判断是否是log文件，有就展示
     *
     * @return
     */
    private String getCrashLogStr() {
        String filePath=CommonContants.crashLogFile;
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder("");
        if (file.exists()) {
            try {
                FileReader f = new FileReader(filePath);
                char[] logbuffer = new char[100];
                int length = 0;
                sb = new StringBuilder("");
                while ((length = f.read(logbuffer)) > 0) {
                    sb.append(logbuffer);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
