package test.nice.testproject;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;

import test.nice.testproject.activities.MainActivity;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openContextualActionModeOverflowMenu;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isFocusable;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.text.StringStartsWith.startsWith;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {
    private static String TAG = MainActivityTests.class.getSimpleName();

    private MainActivity mActivity;

    public MainActivityTests() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();

    }

    /**
     * Click a button and change the text of a TextView.
     */
    @SmallTest
    public void testSwapText() {
        onView(withId(R.id.exampleButton)).perform(click());
        onView(allOf(withId(R.id.exampleText), withText(R.string.example_text_after))).check(matches(isDisplayed()));
    }


    /**
     * Test a button is enabled.
     */
    @SmallTest
    public void testIsEnabled() {
        onView(withId(R.id.exampleButton)).check(matches(isEnabled()));
    }

    /**
     * Test checking a checkbox
     */
    @SmallTest
    public void testCheckingACheckBox() {
        onView(withId(R.id.enabled_checkbox)).check(matches(isNotChecked())).perform(click()).check(matches(isChecked()));
    }

    /**
     * Test checking a checkbox
     * Test if you can press go, or next within an EditText
     */
    @SmallTest
    public void testHasIME() {
        //hasImeAction(R.id.contentDescriptionText);
    }

    /**
     * Test checking a checkbox
     */
    @SmallTest
    public void testADisabledCheckbox() {
        onView(withId(R.id.disabled_checkbox)).check(matches(not(isEnabled())));
    }

    /**
     * Test a button is clickable.
     */
    @SmallTest
    public void testIsClickable() {
        onView(withId(R.id.exampleButton)).check(matches(isClickable()));
    }

    /**
     * Click on a contextual menu item from the Overflow menu.
     */
    @SmallTest
    public void testActionMenuItemClick() {
        openContextualActionModeOverflowMenu();
        onView(withText(R.string.action_settings)).perform(click());
    }

    /**
     * Test if an EditText is focusable.
     */
    @SmallTest
    public void testEditTextIsFocusable() {
        onView(withId(R.id.exampleEditText)).check(matches(isFocusable()));
    }


    /**
     * Type text and confirm that text has been typed by searching for the text
     */
    @SmallTest
    public void testTypeText() {
        String exampleText = "Here is a long piece of text to type out.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(withText(exampleText)).check(matches(isDisplayed()));
    }

    /**
     * Type text and confirm that text has been typed by searching for the text
     */
    @SmallTest
    public void testTypeTextThenClear() {
        String exampleText = "Here is a long piece of text to type out.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(withText(exampleText)).check(matches(isDisplayed()));
        onView(withId(R.id.exampleEditText)).perform(clearText());
        // Check it is empty
        onView(withId(R.id.exampleEditText)).check(matches(withText("")));
    }
    /**
     * Type text and confirm that text has been typed by searching for the text
     */
    @SmallTest
    public void testTypeTextThenReplace() {
        String exampleText = "Here is a long piece of text to type out.";
        String exampleReplaceText = "Here is a long piece of text to replace.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(withText(exampleText)).check(matches(isDisplayed()));
        onView(withId(R.id.exampleEditText)).perform(replaceText(exampleReplaceText));
        // Check it is empty
        onView(allOf(withId(R.id.exampleEditText), withText(exampleReplaceText))).check(matches(isDisplayed()));
    }
    /**
     * Type text and confirm that text has been typed by searching for the text and the ID of the textbox.
     */
    @SmallTest
    public void testTypeTextWithTextAndId() {
        String exampleText = "Here is a long piece of text to type out.";
        onView(withId(R.id.exampleEditText)).perform(typeText(exampleText));
        // Example confirming this text has been succesfully typed with just the text.
        onView(allOf(withText(exampleText), withId(R.id.exampleEditText))).check(matches(isDisplayed()));
    }

    /**
     * Focus on an EditText and then close the Soft Keyboard that is displayed.
     */
    @SmallTest
    public void testTypeTextCloseSoftKeyboard() {
        onView(withId(R.id.exampleEditText)).perform(click());
        closeSoftKeyboard();
    }


    /**
     * Test the Content Description of a TextView
     */
    @SmallTest
    public void testContentDescription() {
        String exampleContentDescription = mActivity.getResources().getString(R.string.example_content_description);
        onView(withId(R.id.contentDescriptionText)).check(matches(hasContentDescription()));
        onView(allOf(withId(R.id.contentDescriptionText), withContentDescription(exampleContentDescription))).check(matches(isDisplayed()));
    }

    /**
     * Test textView startsWith
     */
    @SmallTest
    public void testStartsWith() {
        String textStartsWith = mActivity.getResources().getString(R.string.example_content_description).substring(0, 5);
        onView(allOf(withId(R.id.contentDescriptionText), withText(startsWith(textStartsWith)))).check(matches(isDisplayed()));
    }

    /**
     * Test textView endsWith
     */
    @SmallTest
    public void testEndsWith() {
        String textEndsWith = mActivity.getResources().getString(R.string.example_content_description);
        textEndsWith = textEndsWith.substring(textEndsWith.length() - 4);
        onView(allOf(withId(R.id.contentDescriptionText), withText(endsWith(textEndsWith)))).check(matches(isDisplayed()));
    }


    /**
     * Test textView endsWith
     */
    @SmallTest
    public void testScrollToButton() {
        onView(withId(R.id.offscreen_button)).check(matches(not(isDisplayed()))).perform(scrollTo()).check(matches(isDisplayed()));
    }

    /**
     * Test swipe down.
     */
    @SmallTest
    public void testScrollDown() {
        onView(withId(R.id.scroll_view)).perform(swipeUp()/* Got to swipe up to scroll down. */);
    }

    /**
     * Test swipe down.
     */
    @SmallTest
    public void testScrollUp() {
        onView(withId(R.id.scroll_view)).perform(swipeDown()/* Got to swipe down to scroll up. */);
    }

    /**
     * Test swipe down.
     */
    @SmallTest
    public void testSelectWithHint() {
        onView(withHint(R.string.example_text_hint)).check(matches(isDisplayed()));
    }
}