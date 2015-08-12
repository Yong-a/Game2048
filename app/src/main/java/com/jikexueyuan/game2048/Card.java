package com.jikexueyuan.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/1 0001.
 */
public class Card extends FrameLayout {
    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(15, 15, 0, 0);
        addView(label, lp);
        setNum(0);
    }

    private int num = 0;
    private TextView label;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }
        switch (num) {
            case 0:
                label.setBackgroundColor(0x33ffffff);
                break;
            case 2:
                label.setBackgroundResource(R.drawable.ic_launcher2);
                label.setText("");
                break;
            case 4:
                label.setBackgroundResource(R.drawable.ic_launcher4);
                label.setText("");
                break;
            case 8:
                label.setBackgroundResource(R.drawable.ic_launcher8);
                label.setText("");
                break;
            case 16:
                label.setBackgroundResource(R.drawable.ic_launcher16);
                label.setText("");
                break;
            case 32:
                label.setBackgroundResource(R.drawable.ic_launcher32);
                label.setText("");
                break;
            case 64:
                label.setBackgroundResource(R.drawable.ic_launcher64);
                label.setText("");
                break;
            case 128:
                label.setBackgroundResource(R.drawable.ic_launcher128);
                label.setText("");
                break;
            case 256:
                label.setBackgroundResource(R.drawable.ic_launcher256);
                label.setText("");
                break;
            case 512:
                label.setBackgroundResource(R.drawable.ic_launcher512);
                label.setText("");
                break;
            case 1024:
                label.setBackgroundResource(R.drawable.ic_launcher1024);
                label.setText("");
                break;
            case 2048:
                label.setBackgroundResource(R.drawable.ic_launcher2048);
                label.setText("");
                break;
            default:
                label.setBackgroundResource(R.drawable.ic_launcher4096);
                break;
        }
    }

    public boolean equals(Card o) {
        return getNum() == o.getNum();
    }
}
