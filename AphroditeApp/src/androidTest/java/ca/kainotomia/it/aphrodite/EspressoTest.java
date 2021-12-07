package ca.kainotomia.it.aphrodite;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityTestRule<SplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(SplashScreenActivity.class);

    @Test
    public void espressoTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.FSU_nameEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Jose Antonio"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.FSU_emailEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                2),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("joseateodoro.jt@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.FSU_passEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                3),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("@Santaana1"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.FSU_confrimPassEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                4),
                        isDisplayed()));
        appCompatEditText4.perform(replaceText("@Santaana1"), closeSoftKeyboard());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.FSU_signup), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                0),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.FSU_signup), withText("REGISTER"),
                        withParent(withParent(withId(R.id.login_frag_host))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.FSU_nameEditTXT), withText("Jose Antonio"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText(""));

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.FSU_nameEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.FSU_nameEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText7.perform(click());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.FSU_nameEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                1),
                        isDisplayed()));
        appCompatEditText8.perform(replaceText("Caleb City"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.FSU_emailEditTXT), withText("joseateodoro.jt@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                2),
                        isDisplayed()));
        appCompatEditText9.perform(click());

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.FSU_emailEditTXT), withText("joseateodoro.jt@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                2),
                        isDisplayed()));
        appCompatEditText10.perform(replaceText("caleb.city@city.com"));

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.FSU_emailEditTXT), withText("caleb.city@city.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                2),
                        isDisplayed()));
        appCompatEditText11.perform(closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.FSU_signup), withText("Register"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        1),
                                0),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.FSI_emailEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText12.perform(replaceText("caleb.city@city.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.FSI_passEditTXT),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText13.perform(replaceText("@Santaana1"), closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.FSI_signin), withText("Login"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.login_frag_host),
                                        0),
                                0),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction viewGroup = onView(
                allOf(withParent(withParent(IsInstanceOf.<View>instanceOf(android.view.ViewGroup.class))),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction extendedFloatingActionButton = onView(
                allOf(withId(R.id.home_fab), withText("Add Layout"), withContentDescription("Add Layout"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                0),
                        isDisplayed()));
        extendedFloatingActionButton.perform(click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.CLP_LayoutName_User_Input_PT),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                9)));
        appCompatEditText14.perform(scrollTo(), replaceText("Test"), closeSoftKeyboard());

        ViewInteraction switchCompat = onView(
                allOf(withId(R.id.CLP_Feature_Time), withText("Time"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                10)));
        switchCompat.perform(scrollTo(), click());

        ViewInteraction switchCompat2 = onView(
                allOf(withId(R.id.CLP_Feature_Date), withText("Date"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                11)));
        switchCompat2.perform(scrollTo(), click());

        ViewInteraction switchCompat3 = onView(
                allOf(withId(R.id.CLP_Feature_Calendar), withText("Calendar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                12)));
        switchCompat3.perform(scrollTo(), click());

        ViewInteraction switchCompat4 = onView(
                allOf(withId(R.id.CLP_Feature_Weather), withText("Weather"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                13)));
        switchCompat4.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.CLP_spinner_time),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatSpinner.perform(scrollTo(), click());

        DataInteraction checkedTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(4);
        checkedTextView.perform(click());

        ViewInteraction switch_ = onView(
                allOf(withId(R.id.CLP_Feature_Time), withText("Time"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.ScrollView.class))),
                        isDisplayed()));
        switch_.check(matches(isDisplayed()));

        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.CLP_SaveButton), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                18)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.CLP_SaveButton), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                18)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.CLP_spinner_calendar),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                2)));
        appCompatSpinner2.perform(scrollTo(), click());

        DataInteraction checkedTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        checkedTextView2.perform(click());

        ViewInteraction switchCompat5 = onView(
                allOf(withId(R.id.CLP_Feature_Date), withText("Date"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                11)));
        switchCompat5.perform(scrollTo(), click());

        ViewInteraction switchCompat6 = onView(
                allOf(withId(R.id.CLP_Feature_Calendar), withText("Calendar"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                12)));
        switchCompat6.perform(scrollTo(), click());

        ViewInteraction switchCompat7 = onView(
                allOf(withId(R.id.CLP_Feature_Weather), withText("Weather"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                13)));
        switchCompat7.perform(scrollTo(), click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.CLP_SaveButton), withText("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                18)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.FHRVI_btn), withText("TEST"),
                        withParent(withParent(withId(R.id.Home_recyclerView))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_home), withContentDescription("Home"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                0),
                        isDisplayed()));
        bottomNavigationItemView.perform(click());

        ViewInteraction bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_layout), withContentDescription("Layouts"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                1),
                        isDisplayed()));
        bottomNavigationItemView2.perform(click());

        ViewInteraction bottomNavigationItemView3 = onView(
                allOf(withId(R.id.navigation_account), withContentDescription("Settings"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                2),
                        isDisplayed()));
        bottomNavigationItemView3.perform(click());

        ViewInteraction bottomNavigationItemView4 = onView(
                allOf(withId(R.id.navigation_voice), withContentDescription("Voice/LED"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.nav_view),
                                        0),
                                3),
                        isDisplayed()));
        bottomNavigationItemView4.perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.nav_view),
                        withParent(allOf(withId(R.id.container),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
