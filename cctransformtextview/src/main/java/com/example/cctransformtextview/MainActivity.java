package com.example.cctransformtextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
    TransformTextView tv1;
    TextView tv2;
    private TextView tv3;
    private String text="今年1月19日，陆航某团飞行员机长张浩和战友王晓冬共同驾驶的直升机在夜间训练时坠毁，双双牺牲。张浩烈士牺牲时，妻子正怀有8" +
            "个月的身孕。英雄血洒蓝天，这个孕育中的小生命牵动着无数人的心。\n" +
            "\n" +
            "　　张浩烈士牺牲后，第一七五医院主动和在家中待产的林红艳取得联系，请她到医院分娩，并免除全部费用。住院期间，林红艳得到了悉心的照顾：医疗小组的24" +
            "小时保障、“爱心产房”的舒适环境、护理小组的贴心服务……张浩烈士生前所在部队——陆航某团密切关注林红艳的身体健康状况，并指派2" +
            "名女兵在她身边悉心陪伴。得知她住院的消息，团领导带领机关人员第一时间赶赴医院，期待着替战友张浩迎接孩子的到来。";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TransformTextView) findViewById(R.id.textView1);
        tv2= (TextView) findViewById(R.id.textView2);
        tv3= (TextView) findViewById(R.id.textView3);

    }
    public void btnOnClick(View view){
        transformTextViewShow();

        DelayShowStringUtil.getInstance().show(MainActivity.this, text,1000, new DelayShowStringUtil.callback() {

            @Override
            public void startCallback(String text) {

            }

            @Override
            public void callback(String text) {
                tv2.setText(text);
            }

            @Override
            public void finshCallback(String text) {

            }
        });
        DelayShowStringUtil.getInstance().show(MainActivity.this, text,70000, new DelayShowStringUtil.callback() {

            @Override
            public void startCallback(String text) {

            }

            @Override
            public void callback(String text) {
                tv3.setText(text);
            }

            @Override
            public void finshCallback(String text) {

            }
        });
    }

    private void transformTextViewShow() {
        tv1.setSlowText("今年1月19日，陆航某团飞行员机长张浩和战友王晓冬共同驾驶的直升机在夜间训练时坠毁，双双牺牲。张浩烈士牺牲时，妻子正怀有8" +
                "个月的身孕。英雄血洒蓝天，这个孕育中的小生命牵动着无数人的心。\n" +
                "\n" +
                "　　张浩烈士牺牲后，第一七五医院主动和在家中待产的林红艳取得联系，请她到医院分娩，并免除全部费用。住院期间，林红艳得到了悉心的照顾：医疗小组的24" +
                "小时保障、“爱心产房”的舒适环境、护理小组的贴心服务……张浩烈士生前所在部队——陆航某团密切关注林红艳的身体健康状况，并指派2" +
                "名女兵在她身边悉心陪伴。得知她住院的消息，团领导带领机关人员第一时间赶赴医院，期待着替战友张浩迎接孩子的到来。");
    }
}
