package com.wcp.tmdbcleanarchitecture


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MovieDetailTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun movieDetailTest() {
        val recyclerView = onView(
            allOf(
                withId(R.id.rv_upcoming_movies),
                withParent(
                    allOf(
                        withId(R.id.frameLayout),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))

        val recyclerView2 = onView(
            allOf(
                withId(R.id.rv_upcoming_movies),
                withParent(
                    allOf(
                        withId(R.id.frameLayout),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        recyclerView2.check(matches(isDisplayed()))

        val recyclerView3 = onView(
            allOf(
                withId(R.id.rv_upcoming_movies),
                withParent(
                    allOf(
                        withId(R.id.frameLayout),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        recyclerView3.check(matches(isDisplayed()))

        val recyclerView4 = onView(
            allOf(
                withId(R.id.rv_upcoming_movies),
                childAtPosition(
                    withId(R.id.frameLayout),
                    0
                )
            )
        )
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val imageView = onView(
            allOf(
                withId(R.id.iv_favorite_movie),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withId(R.id.iv_favorite_movie),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val imageView3 = onView(
            allOf(
                withId(R.id.iv_favorite_movie),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.ScrollView::class.java))),
                isDisplayed()
            )
        )
        imageView3.check(matches(isDisplayed()))

        val appCompatImageView = onView(
            allOf(
                withId(R.id.iv_favorite_movie),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("androidx.core.widget.NestedScrollView")),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("အပေါ်သို့ ရွှေ့ရန်"),
                childAtPosition(
                    allOf(
                        withId(R.id.action_bar),
                        childAtPosition(
                            withId(R.id.action_bar_container),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val imageView4 = onView(
            allOf(
                withId(R.id.icon),
                withParent(
                    allOf(
                        withId(R.id.navigation_favorites), withContentDescription("Favorites"),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView4.check(matches(isDisplayed()))

        val imageView5 = onView(
            allOf(
                withId(R.id.icon),
                withParent(
                    allOf(
                        withId(R.id.navigation_favorites), withContentDescription("Favorites"),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView5.check(matches(isDisplayed()))

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_favorites), withContentDescription("Favorites"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        val imageView6 = onView(
            allOf(
                withId(R.id.iv_poster),
                withParent(withParent(withId(R.id.cv_poster))),
                isDisplayed()
            )
        )
        imageView6.check(matches(isDisplayed()))

        val recyclerView5 = onView(
            allOf(
                withId(R.id.rv_favorite_movies),
                childAtPosition(
                    withId(R.id.frameLayout),
                    0
                )
            )
        )
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        val imageView7 = onView(
            allOf(
                withId(R.id.iv_poster),
                withParent(
                    allOf(
                        withId(R.id.materialCardView),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        imageView7.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
