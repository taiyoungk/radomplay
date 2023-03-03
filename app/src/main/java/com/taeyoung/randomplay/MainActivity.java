package com.taeyoung.randomplay;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private GridView gridview = null;
    private GridViewAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridview = (GridView) findViewById(R.id.gridview);
        adapter = new GridViewAdapter();

        int tae = 0;

        Random random = new Random();
        int random_num = random.nextInt(16);

        for(int i = 0; i < 16; i++) {
            tae = 0;
            if(i == random_num){
                tae = 99;
            }

            adapter.addItem(new BearItem(i, tae,"곰돌이", R.drawable.qq1));

        }

        //리스트뷰에 Adapter 설정
        gridview.setAdapter(adapter);

    }

    public void ButtonInitial(View v) {
        PackageManager packageManager = getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }

    /* 그리드뷰 어댑터 */
    class GridViewAdapter extends BaseAdapter {
        ArrayList<BearItem> items = new ArrayList<BearItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(BearItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final BearItem bearItem = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gridview_list_item, viewGroup, false);

//                TextView tv_num = (TextView) convertView.findViewById(R.id.tv_num);
                TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);

//                tv_num.setText(bearItem.getNum());
                tv_name.setText(bearItem.getName());
                iv_icon.setImageResource(bearItem.getResId());
                Log.d(TAG, "getView() - [ "+position+" ] "+bearItem.getName());

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            //각 아이템 선택 event
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, bearItem.getNum()+" 번 - "+bearItem.getName()+" 입니당! ", Toast.LENGTH_SHORT).show();
                    if(bearItem.getNum() == 99) {
                        Toast.makeText(context," 당 첨! ", Toast.LENGTH_SHORT).show();
                    }else{
                        bearItem.setResId(R.drawable.choiced);

                        gridview.setAdapter(adapter);
//                        }
                    }
                }
            });

            return convertView;  //뷰 객체 반환
        }
    }

}