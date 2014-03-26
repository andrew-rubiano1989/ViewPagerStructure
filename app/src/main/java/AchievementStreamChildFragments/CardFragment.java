package AchievementStreamChildFragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.R;

import java.util.Random;

import Comments.Comment;

/**
 * Created by Drew on 3/13/14.
 */
public class CardFragment extends Fragment {

    LinearLayout commentButton, likeButton, endorseButton, actionContainer, commentContainer, addCommentContainer, allActionContainer;
    TextView likeNumber, endorseNumber, commentNumber;
    EditText addCommentEditText;
    Button postButton;
    ImageView profilePic, commentPic, likeButtonIcon, goalLikeButtonIcon, commentButtonIcon, goalCommentIcon, endorseButtonIcon, goalEndorseIcon;
    DisplayMetrics metrics;
    Runnable asyncAddComments;
    boolean likeButtonBool, endorseBool;
    int likeNumberInt, endorseNumberInt, commentNumberInt;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.card_fragment_layout, container, false);

        intializeFragment(rootView);

        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commentContainer.getVisibility() == View.GONE)
                {
                    new addComments().execute(new Comment());

                    commentContainer.setVisibility(View.VISIBLE);
                    addCommentContainer.setVisibility(View.VISIBLE);
                    commentButton.setBackgroundColor(Color.parseColor("#ffebebeb"));
                }
                else
                {
                    commentContainer.setVisibility(View.GONE);
                    addCommentContainer.setVisibility(View.GONE);
                    commentButton.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });

        endorseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(endorseBool == false)
                {
                    endorseButtonIcon.setImageResource(R.drawable.ic_endorse_xxxhdpi_blue);
                    goalEndorseIcon.setImageResource(R.drawable.ic_endorse_xxxhdpi_blue);
                    endorseNumber.setTextColor(Color.parseColor("#0099cd"));

                    endorseNumberInt = new Integer(endorseNumber.getText().toString()) + 1;
                    endorseNumber.setText(new Integer(endorseNumberInt).toString());
                    endorseBool = true;
                }
                else
                {
                    endorseButtonIcon.setImageResource(R.drawable.ic_endorse_xxxhdpi_light_grey);
                    goalEndorseIcon.setImageResource(R.drawable.ic_endorse_xxxhdpi_light_grey);
                    endorseNumber.setTextColor(Color.parseColor("#aeaeae"));

                    endorseNumberInt = new Integer(endorseNumber.getText().toString()) - 1;
                    endorseNumber.setText(new Integer(endorseNumberInt).toString());
                    endorseBool = false;
                }
            }
        });

        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(likeButtonBool == false)
                {
                    likeButtonIcon.setImageResource(R.drawable.ic_like_xxxhdpi_blue);
                    goalLikeButtonIcon.setImageResource(R.drawable.ic_like_xxxhdpi_blue);
                    likeNumber.setTextColor(Color.parseColor("#0099cd"));

                    likeNumberInt = new Integer(likeNumber.getText().toString()) + 1;
                    likeNumber.setText(new Integer(likeNumberInt).toString());
                    likeButtonBool = true;
                }
                else
                {
                    likeButtonIcon.setImageResource(R.drawable.ic_like_xxxhdpi_light_grey);
                    goalLikeButtonIcon.setImageResource(R.drawable.ic_like_xxxhdpi_light_grey);
                    likeNumber.setTextColor(Color.parseColor("#aeaeae"));

                    likeNumberInt = new Integer(likeNumber.getText().toString()) - 1;
                    likeNumber.setText(new Integer(likeNumberInt).toString());
                    likeButtonBool = false;
                }
            }
        });

        addCommentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(addCommentEditText.length() == 0)
                    postButton.setBackgroundColor(Color.parseColor("#ffebebeb"));
                else
                    postButton.setBackgroundColor(Color.parseColor("#0099cd"));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commentContainer.getVisibility() != View.GONE && addCommentEditText.length() != 0)
                {
                    getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_comment, R.anim.leave_comment, 0, 0).add(R.id.commentContainer,
                            new Comment("Andrew Rubiano", addCommentEditText.getText().toString(), "Now")).commit();
                    addCommentEditText.setText("");
                    commentNumber.setTextColor(Color.parseColor("#0099cd"));
                    goalCommentIcon.setImageResource(R.drawable.ic_comment_xxxhdpi_blue);
                    commentButtonIcon.setImageResource(R.drawable.ic_comment_xxxhdpi_blue);

                    commentNumberInt = new Integer(commentNumber.getText().toString()) + 1;
                    commentNumber.setText(new Integer(commentNumberInt).toString());
                }
            }
        });

        return rootView;
    }

    private void intializeFragment(View rootView) {

    metrics = new DisplayMetrics();
    getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

    commentButton = (LinearLayout) rootView.findViewById(R.id.commentButton);
    likeButton = (LinearLayout) rootView.findViewById(R.id.likeButton);
    endorseButton = (LinearLayout) rootView.findViewById(R.id.endorseButton);
    postButton = (Button) rootView.findViewById(R.id.postButton);

    actionContainer = (LinearLayout) rootView.findViewById(R.id.actionContainer);
    commentContainer = (LinearLayout) rootView.findViewById(R.id.commentContainer);
    addCommentContainer = (LinearLayout) rootView.findViewById(R.id.addCommentContainer);
    allActionContainer = (LinearLayout) rootView.findViewById(R.id.allActionContainer);

    likeButtonIcon = (ImageView) rootView.findViewById(R.id.likeButtonIcon);
    goalLikeButtonIcon = (ImageView) rootView.findViewById(R.id.commentLikeIcon);
    commentButtonIcon = (ImageView) rootView.findViewById(R.id.commentCommentsIcon);
    goalCommentIcon = (ImageView) rootView.findViewById(R.id.goalCommentsIcon);
    endorseButtonIcon = (ImageView) rootView.findViewById(R.id.endorseButtonIcon);
    goalEndorseIcon = (ImageView) rootView.findViewById(R.id.endorseGoalIcon);

    likeNumber = (TextView) rootView.findViewById(R.id.commentLikeNumber);
    endorseNumber = (TextView) rootView.findViewById(R.id.commentEndorseNumber);
    commentNumber = (TextView) rootView.findViewById(R.id.commentCommentsNumber);

    addCommentEditText = (EditText) rootView.findViewById(R.id.commentEditText);

    profilePic = (ImageView) rootView.findViewById(R.id.eventProfileIcon);
    commentPic = (ImageView) rootView.findViewById(R.id.profilePicture);

    likeButtonBool = false;

    asyncAddComments = addComments();

    Drawable myDrawable = getResources().getDrawable(R.drawable.profile_pic);
    Bitmap myPic = ((BitmapDrawable) myDrawable).getBitmap();
    profilePic.setImageBitmap(getRoundedCornerBitmap(myPic, 48));
    commentPic.setImageBitmap(getRoundedCornerBitmap(myPic, 48));
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

    private class addComments extends AsyncTask<Comment, Integer, Long>
    {
        @Override
        protected Long doInBackground(Comment... comments) {
            for(Comment comment: comments)
            {
                new Thread(asyncAddComments).start();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... progress) {
        }
        @Override
        protected void onPostExecute(Long result) {
        }
    }

    public Runnable addComments()
    {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                    rollInComments();
            }
        };

        return runnable;
    }

    private void rollInComments() {
        try
        {
            for(int i = 0; i < new Random().nextInt(5); i++)
            {
                getChildFragmentManager().beginTransaction().setCustomAnimations(R.anim.enter_comment, R.anim.leave_comment, 0, 0).add(R.id.commentContainer, new Comment()).commit();
                Thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
