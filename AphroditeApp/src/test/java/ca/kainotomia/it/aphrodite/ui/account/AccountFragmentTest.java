package ca.kainotomia.it.aphrodite.ui.account;

import static org.junit.Assert.*;


import android.accounts.Account;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import ca.kainotomia.it.aphrodite.AccountActivity;
import ca.kainotomia.it.aphrodite.R;

@RunWith(RobolectricTestRunner.class)
public class AccountFragmentTest {
    private Button about, support, settings, logout;
    private TextView username;

    @Before
    public static void startFragment( Fragment fragment )
    {
        AccountActivity activity = Robolectric.buildActivity( AccountActivity.class )
                .create()
                .start()
                .resume()
                .get();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add( fragment, null );
        fragmentTransaction.commit();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        AccountFragment fragment = new AccountFragment();
        startFragment( fragment );
        assertNotNull( fragment );
    }

    @Test
    public void buttonTest(@NonNull View view) throws Exception
    {
        AccountFragment fragment = new AccountFragment();
        about = view.findViewById(R.id.AF_Button_about);
        support = view.findViewById(R.id.AF_Button_support);
        settings = view.findViewById(R.id.AF_Button_settings);
        startFragment( fragment );
//        assertTrue(true.about);
//        assertTrue(true.support);
//        assertTrue(true.settings);
    }

    @Test
    public void usernameTest(@NonNull View view) throws Exception
    {
        AccountFragment fragment = new AccountFragment();
        username = view.findViewById(R.id.AF_TextView_username);
        startFragment( fragment );
        //assertThat(username, isTrue);
    }


}