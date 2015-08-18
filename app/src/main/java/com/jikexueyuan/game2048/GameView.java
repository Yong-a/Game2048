package com.jikexueyuan.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/30 0030.
 */
public class GameView extends GridLayout {

    private Card[][] cardMap = new Card[4][4];
    private List<Point> emptyPoints = new ArrayList<Point>();
    private Button ok;
    private Button cancel;
    private TextView resultScore;
    private AlertDialog dialog;

    public GameView(Context context) {
        super(context);

        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initGameView();
    }


    private void initGameView() {
        setColumnCount(4);
        setBackgroundColor(0xff666633);
        setOnTouchListener(new OnTouchListener() {
            private float startX, startY, offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -5) {
                                swipeLeft();
                            } else if (offsetX > 5) {
                                swipeRight();
                            }
                        } else {
                            if (offsetY < -5) {
                                swipeUp();
                            } else if (offsetY > 5) {
                                swipeDown();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w, h) - 15) / 4;
        addCards(cardWidth, cardWidth);
        startGame();
    }

    private void addCards(int cardWidth, int cardHeight) {
        Card c;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                c = new Card(getContext());
                c.setNum(0);
                addView(c, cardWidth, cardHeight);
                cardMap[x][y] = c;
            }
        }
    }

    public void startGame() {
        MainActivity.getMainActivity().clearScore();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                cardMap[x][y].setNum(0);
            }
        }
        addRandomNum();
        addRandomNum();
        MainActivity.getMainActivity().addScore(0);
    }

    private void addRandomNum() {
        emptyPoints.clear();

        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (cardMap[x][y].getNum() <= 0) {
                    emptyPoints.add(new Point(x, y));
                }
            }
        }
        Point p = emptyPoints.remove((int) (Math.random() * emptyPoints.size()));
        cardMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
    }


    private void swipeLeft() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cardMap[x][y1].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y--;
                            merge = true;
                            MainActivity.getMainActivity().getVoice(1);
                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                            MainActivity.getMainActivity().getVoice(2);
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }

    }

    private void swipeRight() {
        boolean merge = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 3; y > 0; y--) {
                for (int y1 = y - 1; y1 >= 0; y1--) {
                    if (cardMap[x][y1].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x][y1].getNum());
                            cardMap[x][y1].setNum(0);
                            y++;
                            merge = true;
                            MainActivity.getMainActivity().getVoice(1);
                        } else if (cardMap[x][y].equals(cardMap[x][y1])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x][y1].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                            MainActivity.getMainActivity().getVoice(2);
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeUp() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cardMap[x1][y].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x--;
                            merge = true;
                            MainActivity.getMainActivity().getVoice(1);
                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                            MainActivity.getMainActivity().getVoice(2);
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void swipeDown() {
        boolean merge = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cardMap[x1][y].getNum() > 0) {
                        if (cardMap[x][y].getNum() <= 0) {
                            cardMap[x][y].setNum(cardMap[x1][y].getNum());
                            cardMap[x1][y].setNum(0);
                            x++;
                            merge = true;
                            MainActivity.getMainActivity().getVoice(1);
                        } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                            cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
                            cardMap[x1][y].setNum(0);
                            MainActivity.getMainActivity().addScore(cardMap[x][y].getNum());
                            merge = true;
                            MainActivity.getMainActivity().getVoice(2);
                        }
                        break;
                    }
                }
            }
        }
        if (merge) {
            addRandomNum();
            checkComplete();
        }
    }

    private void checkComplete() {
        boolean complete = true;
        ALL:
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (cardMap[x][y].getNum() == 0 ||
                        (y > 0 && cardMap[x][y].equals(cardMap[x][y - 1])) ||
                        (y < 3 && cardMap[x][y].equals(cardMap[x][y + 1])) ||
                        (x > 0 && cardMap[x][y].equals(cardMap[x - 1][y])) ||
                        (x < 3 && cardMap[x][y].equals(cardMap[x + 1][y]))) {
                    complete = false;
                    break ALL;
                }
            }
        }
        if (complete) {
            MainActivity.getMainActivity().getVoice(3);
            showDialog();
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.getMainActivity());
        View view = View.inflate(MainActivity.getMainActivity(), R.layout.dialog_layout, null);
        resultScore = (TextView) view.findViewById(R.id.high_Score);
        ok = (Button) view.findViewById(R.id.ok);
        cancel = (Button) view.findViewById(R.id.cancel);
        resultScore.setText("您本局得分:" + MainActivity.getMainActivity().getScore() + "分.");
        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getMainActivity().finish();
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.setView(view, 0, 0, 0, 0);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
