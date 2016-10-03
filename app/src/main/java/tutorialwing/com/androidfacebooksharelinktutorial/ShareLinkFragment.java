package tutorialwing.com.androidfacebooksharelinktutorial;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

public class ShareLinkFragment extends Fragment {

	private static String TAG = ShareLinkFragment.class.getName();

	private CallbackManager callbackManager;

	ShareDialog shareDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Initialize facebook SDK.
		FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

		// Create a callbackManager to handle the login responses.
		callbackManager = CallbackManager.Factory.create();

		shareDialog = new ShareDialog(this);

		// this part is optional
		shareDialog.registerCallback(callbackManager, callback);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_share, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		setLinkShare(view);
	}

	private void setLinkShare(View view) {
		ShareLinkContent content = new ShareLinkContent.Builder()
				.setContentTitle("Tutorialwing - Free programming tutorials")
				.setImageUrl(Uri.parse("https://scontent-sin6-1.xx.fbcdn.net/t31.0-8/13403381_247495578953089_8113745370016563192_o.png"))
				.setContentDescription("Tutorialwing is an online platform for free programming tutorials. These tutorials are designed for beginners as well as experienced programmers.")
				.setContentUrl(Uri.parse("https://www.facebook.com/tutorialwing/"))
				.setShareHashtag(new ShareHashtag.Builder()
						.setHashtag("#Tutorialwing")
						.build())
				.setQuote("Learn and share your knowledge")
				.build();

		ShareButton shareButton = (ShareButton) view.findViewById(R.id.fb_share_button);
		shareButton.setShareContent(content);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Call callbackManager.onActivityResult to pass login result to the LoginManager via callbackManager.
		callbackManager.onActivityResult(requestCode, resultCode, data);
	}


	private FacebookCallback<Sharer.Result> callback = new FacebookCallback<Sharer.Result>() {
		@Override
		public void onSuccess(Sharer.Result result) {
			Log.v(TAG, "Successfully posted");
			// Write some code to do some operations when you shared content successfully.
		}

		@Override
		public void onCancel() {
			Log.v(TAG, "Sharing cancelled");
			// Write some code to do some operations when you cancel sharing content.
		}

		@Override
		public void onError(FacebookException error) {
			Log.v(TAG, error.getMessage());
			// Write some code to do some operations when some error occurs while sharing content.
		}
	};
}