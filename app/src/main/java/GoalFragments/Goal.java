package GoalFragments;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.R;

import java.util.Random;

/**
 * Created by Drew on 3/14/14.
 */
public class Goal extends Fragment {

    View rootView;
    LinearLayout container;
    ImageView goalImage;
    int minHeight;

    public Goal()
    {

    }

    public Goal(int size)
    {
        minHeight = size;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        switch(minHeight){
            case 0:
                rootView = inflater.inflate(R.layout.goal_display_layout1, container, false);
                goalImage = (ImageView) rootView.findViewById(R.id.GoalViewContainer);
                goalImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.violin, 250, 250));
                break;
            case 1:
                rootView = inflater.inflate(R.layout.goal_display_layout2, container, false);
                goalImage = (ImageView) rootView.findViewById(R.id.GoalViewContainer);
                goalImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.marathon, 250, 250));
                break;
            case 2:
                rootView = inflater.inflate(R.layout.goal_display_layout3, container, false);
                goalImage = (ImageView) rootView.findViewById(R.id.GoalViewContainer);
                goalImage.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.drawable.recording_studio, 250, 250));
                break;
        }

        return rootView;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
        int reqWidth, int reqHeight) {

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resId, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(res, resId, options);
    }
}
