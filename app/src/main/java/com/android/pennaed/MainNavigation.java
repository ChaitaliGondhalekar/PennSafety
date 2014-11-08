package com.android.pennaed;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.pennaed.contacts.ContactsFragment;
import com.android.pennaed.emergency.EmergencyFragment;
import com.android.pennaed.outOfReach.LocationTrackFragment;
import com.android.pennaed.walkTimer.WalkTimerFragment;
import com.android.pennaed.outOfReach.Notification;
import com.android.pennaed.outOfReach.SettingsActivity;


public class MainNavigation extends Activity
		implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_navigation_emergency);

		mNavigationDrawerFragment = (NavigationDrawerFragment)
				getFragmentManager().findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(
				R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.action_settings:
				Intent i = new Intent(this, SettingsActivity.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		switch (position) {
			case 0:
				fragmentManager.beginTransaction()
						.replace(R.id.container, new EmergencyFragment())
						.commit();
				break;
			case 1:
				fragmentManager.beginTransaction()
						.replace(R.id.container, new ContactsFragment())
						.commit();
				break;
			case 2:
				fragmentManager.beginTransaction()
						.replace(R.id.container, new WalkTimerFragment())
						.commit();
				break;
			case 3:
				fragmentManager.beginTransaction()
						.replace(R.id.container, new LocationTrackFragment())
						.commit();
				break;
		}
	}

	public void onSectionAttached(int number) {
		switch (number) {
			case 1:
				mTitle = getString(R.string.title_fragment_emergency_main);
				break;
			case 2:
				mTitle = getString(R.string.title_fragment_contacts_main);
				break;
			case 3:
				mTitle = getString(R.string.title_fragment_walk_timer_main);
				break;
			case 4:
				mTitle = getString(R.string.title_fragment_location_track);
				break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			restoreActionBar();
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.global, menu);
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

}
