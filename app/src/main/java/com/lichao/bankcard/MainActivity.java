package com.lichao.bankcard;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.ll);
        Palette.from(BitmapFactory.decodeResource(getResources(), R.drawable.f1)).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch lightVibrantSwatch = palette.getDarkVibrantSwatch();
                if (lightVibrantSwatch == null) {
                    for (Palette.Swatch swatch : palette.getSwatches()) {
                        if (null != swatch) {
                            lightVibrantSwatch = swatch;
                            break;
                        }
                    }
                }
                //起始颜色
                int color = lightVibrantSwatch.getRgb();
                for (int i = 0; i < 9; i++) {
                    TextView textView = new TextView(MainActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    layoutParams.setMargins(0, 30, 0, 0);
                    int r = Color.red(color);
                    int g = Color.blue(color);
                    int b = Color.green(color);
                    textView.setText("颜色  r "+r+  "  b  "+b+"  g  "+g);
                    textView.setBackgroundColor(color);
                    //加深一次  0.1
                    color = colorBurn(color);
                    linearLayout.addView(textView);
                }
            }
        });
    }

    private int colorBurn(int rgb) {
        //加深颜色
        // int all= (int) (rgb*1.1f);
        //  红色
        int red = rgb>>16&0xFF;
        int green = rgb>>8&0xFF;
        int blue = rgb&0xFF;

        red = (int) Math.floor(red * (1 - 0.1));
        green = (int) Math.floor(green * (1 - 0.1));
        blue = (int) Math.floor(blue * (1 - 0.1));
        return Color.rgb(red, green, blue);
    }
}
