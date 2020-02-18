package com.display.feedimages

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View

import com.display.feedimages.adapter.FeedAdapter

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import java.util.ArrayList

import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.lessThanOrEqualTo
import org.hamcrest.core.IsNull.notNullValue


@RunWith(AndroidJUnit4::class)
class GetFactTest {

    @Rule
    var rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    /**
     * A unit test to check if downloaded data is added to the [RecyclerView].
     *
     * @throws InterruptedException
     * @throws NoSuchFieldException
     */
    @Test
    @Throws(InterruptedException::class, NoSuchFieldException::class)
    fun testFactDownloaded() {

        val activity = rule.activity

        val viewById = activity.findViewById<View>(R.id.rv_facts)

        assertThat(viewById, notNullValue())

        val listView = viewById as RecyclerView
        val adapter = listView.adapter as FeedAdapter

        assertThat(adapter, instanceOf<Any>(RecyclerView.Adapter::class.java))

        if (Utils.isNetworkAvailable(activity)) {
            Thread.sleep(WAIT)
            assertThat(adapter.itemCount, greaterThan(5))
        }
    }

    /**
     * A unit test to check if the data was not yet downloaded.
     *
     * @throws InterruptedException
     * @throws NoSuchFieldException
     */
    @Test
    @Throws(InterruptedException::class, NoSuchFieldException::class)
    fun testFactDownloadFailed() {

        val activity = rule.activity

        val viewById = activity.findViewById<View>(R.id.rv_facts)

        assertThat(viewById, notNullValue())

        val listView = viewById as RecyclerView
        val adapter = listView.adapter as FeedAdapter

        assertThat(adapter, instanceOf<Any>(RecyclerView.Adapter::class.java))

        if (Utils.isNetworkAvailable(activity)) {
            activity.loadRecyclerView(ArrayList())
            assertThat(adapter.itemCount, lessThanOrEqualTo(0))
        }
    }

    companion object {

        private val WAIT: Long = 10000
    }
}