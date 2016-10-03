package tutorialwing.com.androidfacebooksharelinktutorial;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addShareLinkFragment();
	}

	private void addShareLinkFragment() {
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.facebook_share_button, new ShareLinkFragment());
		fragmentTransaction.commit();
	}
}
