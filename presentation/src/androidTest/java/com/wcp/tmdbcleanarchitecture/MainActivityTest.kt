package com.wcp.tmdbcleanarchitecture


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        onView(
            allOf(
                withId(R.id.action_bar),
                withParent(
                    allOf(
                        withId(R.id.action_bar_container),
                        withParent(withId(R.id.decor_content_parent))
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withText("Upcoming"),
                withParent(
                    allOf(
                        withId(R.id.action_bar),
                        withParent(withId(R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.icon),
                withParent(
                    allOf(
                        withId(R.id.navigation_upcoming), withContentDescription("Upcoming"),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.largeLabel), withText("Upcoming"),
                withParent(
                    allOf(
                        withId(R.id.labelGroup),
                        withParent(
                            allOf(
                                withId(R.id.navigation_upcoming),
                                withContentDescription("Upcoming")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.icon),
                withParent(
                    allOf(
                        withId(R.id.navigation_popular), withContentDescription("Popular"),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.smallLabel), withText("Popular"),
                withParent(
                    allOf(
                        withId(R.id.labelGroup),
                        withParent(
                            allOf(
                                withId(R.id.navigation_popular),
                                withContentDescription("Popular")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )

        onView(
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

        onView(
            allOf(
                withId(R.id.smallLabel), withText("Favorites"),
                withParent(
                    allOf(
                        withId(R.id.labelGroup),
                        withParent(
                            allOf(
                                withId(R.id.navigation_favorites),
                                withContentDescription("Favorites")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )

        onView(
            allOf(
                withId(R.id.smallLabel), withText("Favorites"),
                withParent(
                    allOf(
                        withId(R.id.labelGroup),
                        withParent(
                            allOf(
                                withId(R.id.navigation_favorites),
                                withContentDescription("Favorites")
                            )
                        )
                    )
                ),
                isDisplayed()
            )
        )
    }
}
