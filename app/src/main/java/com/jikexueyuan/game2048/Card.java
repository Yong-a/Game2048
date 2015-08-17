package com.jikexueyuan.game2048;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/1 0001.
 */
public class Card extends FrameLayout {
    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setGravity(Gravity.CENTER);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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
        switch (num) {
            case 0:
                label.setBackgroundResource(R.drawable.ic_launcher0);
                break;
            case 2:
                label.setBackgroundResource(R.drawable.ic_launcher2);
                break;
            case 4:
                label.setBackgroundResource(R.drawable.ic_launcher4);
                break;
            case 8:
                label.setBackgroundResource(R.drawable.ic_launcher8);
                break;
            case 16:
                label.setBackgroundResource(R.drawable.ic_launcher16);
                break;
            case 32:
                label.setBackgroundResource(R.drawable.ic_launcher32);
                break;
            case 64:
                label.setBackgroundResource(R.drawable.ic_launcher64);
                break;
            case 128:
                label.setBackgroundResource(R.drawable.ic_launcher128);
                break;
            case 256:
                label.setBackgroundResource(R.drawable.ic_launcher256);
                break;
            case 512:
                label.setBackgroundResource(R.drawable.ic_launcher512);
                break;
            case 1024:
                label.setBackgroundResource(R.drawable.ic_launcher1024);
                break;
            case 2048:
                label.setBackgroundResource(R.drawable.ic_launcher2048);
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
