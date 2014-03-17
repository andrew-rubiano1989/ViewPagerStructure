package Comments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.R;

/**
 * Created by Drew on 3/15/14.
 */
public class Comment extends Fragment {

    ImageView profileIcon;
    TextView nameText, commentText, dateText;
    String name, comment, date;

    public Comment()
    {
        name = "Jillian Ho-lung";
        comment = "I'm so proud of you!";
        date = "a few seconds ago";
    }

    public Comment(String name, String comment, String date)
    {
        this.name = name;
        this.comment = comment;
        this.date = date;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comment_fragment, container, false);

        intializeComment(rootView);
        attachInformaiton();

        return rootView;
    }

    private void attachInformaiton() {
        nameText.setText(name);
        commentText.setText(comment);
        dateText.setText(date);
    }

    private void intializeComment(View rootView)
    {
        nameText = (TextView) rootView.findViewById(R.id.commentersName);
        commentText = (TextView) rootView.findViewById(R.id.commentersComment);
        dateText = (TextView) rootView.findViewById(R.id.datePosted);

        profileIcon = (ImageView) rootView.findViewById(R.id.profilePicture);
        Drawable myDrawable = getResources().getDrawable(R.drawable.profile_pic);
        Bitmap myPic = ((BitmapDrawable) myDrawable).getBitmap();
        profileIcon.setImageBitmap(getRoundedCornerBitmap(myPic, 48));
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }
}
