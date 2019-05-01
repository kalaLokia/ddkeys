package drpg.ddkeys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.util.List;

import drpg.ddkeys.data.Connections;

import static drpg.ddkeys.data.Locations.locations;
import static drpg.ddkeys.master.master_init.advCurve;
import static drpg.ddkeys.master.master_init.adv_angle;
import static drpg.ddkeys.master.master_init.autoHealCount;
import static drpg.ddkeys.master.master_init.autoPhealCount;
import static drpg.ddkeys.master.master_init.autohealing;
import static drpg.ddkeys.master.master_init.autophealing;
import static drpg.ddkeys.master.master_init.ccL_left1;
import static drpg.ddkeys.master.master_init.ccL_left2;
import static drpg.ddkeys.master.master_init.ccL_left3;
import static drpg.ddkeys.master.master_init.ccL_left4;
import static drpg.ddkeys.master.master_init.ccL_left5;
import static drpg.ddkeys.master.master_init.ccL_left6;
import static drpg.ddkeys.master.master_init.ccL_right1;
import static drpg.ddkeys.master.master_init.ccL_right2;
import static drpg.ddkeys.master.master_init.ccL_right3;
import static drpg.ddkeys.master.master_init.ccL_right4;
import static drpg.ddkeys.master.master_init.ccL_right5;
import static drpg.ddkeys.master.master_init.ccL_right6;
import static drpg.ddkeys.master.master_init.cdTen;
import static drpg.ddkeys.master.master_init.colorCode;
import static drpg.ddkeys.master.master_init.healcount;
import static drpg.ddkeys.master.master_init.isAdvTimer;
import static drpg.ddkeys.master.master_init.mobcount;
import static drpg.ddkeys.master.master_init.moptions1;
import static drpg.ddkeys.master.master_init.moptions3;
import static drpg.ddkeys.master.master_init.moptions4;
import static drpg.ddkeys.master.master_init.moptions5;
import static drpg.ddkeys.master.master_init.moptions6;
import static drpg.ddkeys.master.master_init.myLife;
import static drpg.ddkeys.master.master_init.petLife;
import static drpg.ddkeys.master.master_init.phealcount;
import static drpg.ddkeys.master.master_init.stdTheme;
import static drpg.ddkeys.master.master_init.xpRing;
import static drpg.ddkeys.master.master_init.xp_or_ring;
import static drpg.ddkeys.master.master_init.xprate;


public class ddKeysView extends KeyboardView {

    public static String sTime = "do!";
    public static String[] connectedPlaces;
    private final float scale = getResources().getDisplayMetrics().density;
    private Connections location_id = new Connections();
    private Paint paint;
    private Paint sidespaint;
    private Paint rpaint;
    private Paint bpaint;
    private Paint outerpaint;
    private Paint innerpaint;
    private Paint outerarcpaint;
    private Paint arcpaint;
    private Paint linepaint;
    private Paint outerlinepaint;
    private Paint dangerlinepaint;
    private Paint textpaint;
    private Paint rounded_box_stroke;
    private Paint rounded_box;
    private Paint black_text;

    public ddKeysView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ddKeysView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setFakeBoldText(true);
        //text color is MODE based
        paint.setTextSize(scale * 12);
        textpaint = new Paint(Paint.ANTI_ALIAS_FLAG); //for adventure counter
        //textpaint.setTextAlign(Paint.Align.CENTER);
        textpaint.setColor(Color.parseColor("#b3cbdb")); //text color
        textpaint.setTextSize(scale * 7);

        sidespaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sidespaint.setTextAlign(Paint.Align.CENTER);
        //text color is MODE based
        sidespaint.setFakeBoldText(true);
        sidespaint.setTextSize(scale * 12);

        arcpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        arcpaint.setColor(Color.parseColor(colorCode));
        arcpaint.setStyle(Paint.Style.STROKE);
        arcpaint.setStrokeCap(Paint.Cap.ROUND);
        arcpaint.setStrokeWidth(10);
        arcpaint.setDither(true);

        rpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rpaint.setColor(Color.parseColor("#ef0404")); //red

        bpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bpaint.setColor(Color.parseColor("#356dce")); //blue

        outerpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerpaint.setStyle(Paint.Style.STROKE);
        outerpaint.setColor(Color.parseColor("#3d4754"));
        outerpaint.setStrokeWidth(10);
        innerpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //innerpaint color is based MODE

        outerarcpaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerarcpaint.setStyle(Paint.Style.STROKE);
        outerarcpaint.setColor(Color.parseColor("#384651"));
        outerarcpaint.setStrokeWidth(10);
        outerarcpaint.setStrokeJoin(Paint.Join.ROUND);    // set the join to round you want
        outerarcpaint.setStrokeCap(Paint.Cap.ROUND);
        outerarcpaint.setDither(true);

        outerlinepaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        outerlinepaint.setColor(Color.parseColor("#384651"));
        outerlinepaint.setStrokeWidth(8);
        outerlinepaint.setStrokeCap(Paint.Cap.ROUND);

        linepaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linepaint.setColor(Color.parseColor("#ffffff"));
        linepaint.setStrokeWidth(8);
        linepaint.setStrokeCap(Paint.Cap.ROUND);
        dangerlinepaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        dangerlinepaint.setColor(Color.parseColor("#ef0404"));
        dangerlinepaint.setStrokeWidth(8);
        dangerlinepaint.setStrokeCap(Paint.Cap.ROUND);

        rounded_box_stroke = new Paint(Paint.ANTI_ALIAS_FLAG);
        rounded_box_stroke.setColor(Color.WHITE);
        rounded_box_stroke.setStyle(Paint.Style.STROKE);
        rounded_box_stroke.setStrokeWidth(2);

        rounded_box = new Paint(Paint.ANTI_ALIAS_FLAG);
        rounded_box.setColor(Color.WHITE);

        black_text = new Paint(Paint.ANTI_ALIAS_FLAG);
        black_text.setColor(Color.BLACK);
        black_text.setTextSize(scale * 10);
        black_text.setTextAlign(Paint.Align.CENTER);


        //screenSize = Math.round(scale);

        if (stdTheme) {
            paint.setColor(Color.parseColor("#b3cbdb")); //text color
            sidespaint.setColor(Color.parseColor("#b3cbdb"));//text color
            innerpaint.setColor(Color.parseColor("#21272b"));
        } else {
            paint.setColor(Color.parseColor("#ffffff"));
            sidespaint.setColor(Color.parseColor("#000000"));//text color
            innerpaint.setColor(Color.parseColor("#ffffff")); //21272b
        }
    }

    @Override
    public boolean onLongPress(Keyboard.Key key) {
        switch (key.codes[0]) {
            case 888:
                if (!moptions3) {
                    return false;
                }
                getOnKeyboardActionListener().onKey(887, null);
                return true;
            case 901:
                if (!moptions4) {
                    return false;
                }
                getOnKeyboardActionListener().onKey(980, null);
                return true;

            case 905:
                if (!moptions5) {
                    return false;
                } else {
                    getOnKeyboardActionListener().onKey(402, null);
                    return true;
                }
            case 906:
                if (!moptions5) {
                    return false;
                } else {
                    getOnKeyboardActionListener().onKey(403, null);
                    return true;
                }
            case 907:
                if (!moptions5) {
                    return false;
                } else {
                    getOnKeyboardActionListener().onKey(401, null);
                    return true;
                }
            case 908:
                if (!moptions5) {
                    return false;
                } else {
                    getOnKeyboardActionListener().onKey(404, null);
                    return true;
                }
            case 909:
                getOnKeyboardActionListener().onKey(622, null);
                return true;
            case 701:
                getOnKeyboardActionListener().onKey(1701, null);
                return true;
            case 702:
                getOnKeyboardActionListener().onKey(1702, null);
                return true;
            case 703:
                getOnKeyboardActionListener().onKey(1703, null);
                return true;
            case 704:
                getOnKeyboardActionListener().onKey(1704, null);
                return true;
            case 705:
                getOnKeyboardActionListener().onKey(1705, null);
                return true;
            case 706:
                getOnKeyboardActionListener().onKey(1706, null);
                return true;
            case 707:
                getOnKeyboardActionListener().onKey(1707, null);
                return true;
            case 708:
                getOnKeyboardActionListener().onKey(1708, null);
                return true;
            case 335:
                getOnKeyboardActionListener().onKey(777, null);
                return true;

        }


        return super.onLongPress(key);
    }


    @SuppressLint("DrawAllocation")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Boolean healA = autohealing;
        Boolean phealA = autophealing;


        final float circleR = scale * 17;
        final float circler = scale * 16;

        init();

        //get all your keys and draw whatever you want
        List<Keyboard.Key> keys = getKeyboard().getKeys();

        for (Keyboard.Key key : keys) {
            float x = key.x + (key.width / 2);
            float yc = key.y + (key.height / 2);
            float y = key.y + scale * 33;
            float y2 = key.y + scale * 29;
            final float circlex = key.x + key.width + scale * 2;
            final float circley = key.y - scale * 2;
            final float textl = key.y + scale * 2;
            int cw = (key.width / 2); //140
            int ch = (key.height / 2); //176
            advCurve = new RectF(cw - (scale * 40), ch - (scale * 40), cw + (scale * 40), ch + (scale * 40));
            @SuppressLint("DrawAllocation") RectF whiteBox_strength = new RectF(key.x + (scale * 5), key.y + key.height - (scale * 20), key.x + (scale * 55), key.y + key.height - (scale * 5));
            @SuppressLint("DrawAllocation") RectF whiteBox_xp = new RectF(key.x + (scale * 5), key.y + key.height - (scale * 20), key.x + (scale * 29), key.y + key.height - (scale * 5));

            //if (key.label != null) {
            switch (key.codes[0]) {

                case 888:
                    if (moptions6) {
                        canvas.drawText(mobcount + "", key.x + (key.width / 2), key.y + ch + (scale * 45), paint);
                    }
                    canvas.drawArc(advCurve, 130, 280, false, outerarcpaint);

                    if (isAdvTimer) {
                        canvas.drawArc(advCurve, 130, adv_angle, false, arcpaint);
                    }
                    if (!cdTen) {
                        rounded_box_stroke.setColor(Color.parseColor("#356dce"));
                        black_text.setColor(Color.parseColor("#356dce"));
                    } else {
                        rounded_box_stroke.setColor(Color.RED);
                        black_text.setColor(Color.RED);
                    }
                    if (!xp_or_ring && moptions3 && xpRing) {
                        //canvas.drawRoundRect(whiteBox_xp, 17, 17, rounded_box);
                        canvas.drawRoundRect(whiteBox_xp, 17, 17, rounded_box_stroke);
                        canvas.drawText("xp", key.x + (scale * 17), key.y + key.height - (scale * 9), black_text);
                    } else if (!xp_or_ring && moptions3 && !xpRing) {
                        //canvas.drawRoundRect(whiteBox_strength, 17, 17, rounded_box);
                        canvas.drawRoundRect(whiteBox_strength, 17, 17, rounded_box_stroke);
                        canvas.drawText("strength", key.x + (scale * 30), key.y + key.height - (scale * 9), black_text);
                    }

                    if (phealA && healA) {
                        canvas.drawText("User ", key.width - scale * 70, key.y + scale * 12, textpaint);
                        canvas.drawText("Pet ", key.width - scale * 70, key.y + scale * 21, textpaint);
                        canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * 10, key.y + scale * 10, outerlinepaint);
                        canvas.drawLine(key.width - scale * 50, key.y + scale * 19, key.width - scale * 10, key.y + scale * 19, outerlinepaint);

                        if (healcount >= autoHealCount) {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * 10, key.y + scale * 10, dangerlinepaint);
                        } else {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * (10 + myLife), key.y + scale * 10, linepaint);
                        }

                        if (phealcount >= autoPhealCount) {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 19, key.width - scale * 10, key.y + scale * 19, dangerlinepaint);
                        } else {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 19, key.width - scale * (10 + petLife), key.y + scale * 19, linepaint);
                        }
                    } else if (phealA) {
                        canvas.drawText("Pet ", key.width - scale * 70, key.y + scale * 12, textpaint);
                        canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * 10, key.y + scale * 10, outerlinepaint);
                        if (phealcount >= autoPhealCount) {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * 10, key.y + scale * 10, dangerlinepaint);
                        } else {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * (10 + petLife), key.y + scale * 10, linepaint);
                        }
                    } else if (healA) {
                        canvas.drawText("User ", key.width - scale * 70, key.y + scale * 12, textpaint);
                        canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * 10, key.y + scale * 10, outerlinepaint);
                        if (healcount >= autoHealCount) {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * 10, key.y + scale * 10, dangerlinepaint);
                        } else {
                            canvas.drawLine(key.width - scale * 50, key.y + scale * 10, key.width - scale * (10 + myLife), key.y + scale * 10, linepaint);
                        }
                    }
                    break;
                case 900:
                    if (healA && (healcount >= autoHealCount)) {
                        canvas.drawCircle(key.x + (key.width / 2), key.y + (45 * scale), 4 * scale, rpaint);
                    }
                    break;
                case 901:

                    if (phealA && (phealcount >= autoPhealCount)) {
                        canvas.drawCircle(key.x + (key.width / 2), key.y + (45 * scale), 4 * scale, rpaint);
                    } else if (!xprate) {
                        canvas.drawCircle(key.x + (key.width / 2), key.y + (45 * scale), 4 * scale, bpaint);
                    }
                    break;
                case 903:
                    if (healA) {
                        if (healcount >= (autoHealCount)) {
                            canvas.drawCircle(key.x + (key.width / 2), key.y + key.height - (scale * 5), 4 * scale, rpaint);
                        } else {
                            canvas.drawCircle(key.x + (key.width / 2), key.y + key.height - (scale * 5), 4 * scale, bpaint);
                        }
                    }
                    break;
                case 904:
                    if (phealA) {
                        if (phealcount >= (autoPhealCount)) {
                            canvas.drawCircle(key.x + (key.width / 2), key.y + key.height - (scale * 5), 4 * scale, rpaint);
                        } else {
                            canvas.drawCircle(key.x + (key.width / 2), key.y + key.height - (scale * 5), 4 * scale, bpaint);
                        }
                    }
                    break;

                case 906:
                    if (!moptions1) {
                        return;
                    } else {
                        if (stdTheme) {
                            canvas.drawCircle(circlex, circley, circleR, outerpaint);
                        }
                        canvas.drawCircle(circlex, circley, circler, innerpaint);
                        canvas.drawText(sTime, circlex - 1, textl, sidespaint);
                    }
                    break;

                case 211:
                    canvas.drawText(ccL_left1, x, y2, paint);
                    break;
                case 212:
                    canvas.drawText(ccL_left2, x, y2, paint);
                    break;

                case 213:
                    canvas.drawText(ccL_left3, x, y2, paint);
                    break;

                case 214:
                    canvas.drawText(ccL_left4, x, y2, paint);
                    break;

                case 215:
                    canvas.drawText(ccL_left5, x, y2, paint);
                    break;

                case 216:
                    canvas.drawText(ccL_left6, x, y2, paint);
                    break;
                case 221:
                    canvas.drawText(ccL_right1, x, y2, paint);
                    break;
                case 222:
                    canvas.drawText(ccL_right2, x, y2, paint);
                    break;
                case 223:
                    canvas.drawText(ccL_right3, x, y2, paint);
                    break;
                case 224:
                    canvas.drawText(ccL_right4, x, y2, paint);
                    break;
                case 225:
                    canvas.drawText(ccL_right5, x, y2, paint);
                    break;
                case 226:
                    canvas.drawText(ccL_right6, x, y2, paint);
                    break;
                case 701:
                    if (locations[Connections.getNw()].length() > 16) {
                        String line1 = locations[Connections.getNw()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getNw()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getNw()], x, y, paint);
                    }
                    break;
                case 702:

                    if (locations[Connections.getN()].length() > 16) {
                        String line1 = locations[Connections.getN()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getN()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getN()], x, y, paint);
                    }
                    break;
                case 703:
                    if (locations[Connections.getNe()].length() > 16) {
                        String line1 = locations[Connections.getNe()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getNe()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getNe()], x, y, paint);
                    }
                    break;
                case 704:
                    if (locations[Connections.getW()].length() > 16) {
                        String line1 = locations[Connections.getW()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getW()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getW()], x, y, paint);
                    }
                    break;
                case 705:
                    if (locations[Connections.getE()].length() > 16) {
                        String line1 = locations[Connections.getE()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getE()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getE()], x, y, paint);
                    }
                    break;
                case 706:
                    if (locations[Connections.getSw()].length() > 16) {
                        String line1 = locations[Connections.getSw()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getSw()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getSw()], x, y, paint);
                    }
                    break;
                case 707:
                    if (locations[Connections.getS()].length() > 16) {
                        String line1 = locations[Connections.getS()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getS()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getS()], x, y, paint);
                    }
                    break;
                case 708:
                    if (locations[Connections.getSe()].length() > 16) {
                        String line1 = locations[Connections.getSe()].substring(0, 14) + " -";
                        String line2 = locations[Connections.getSe()].substring(14);
                        canvas.drawText(line1, x, y2, paint);
                        y2 += paint.descent() - paint.ascent();
                        canvas.drawText(line2, x, y2, paint);
                    } else {
                        canvas.drawText(locations[Connections.getSe()], x, y, paint);
                    }
                    break;
            }
        }

    }

}
