package jodevapp.mvpkotlin.main

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import io.reactivex.observers.TestObserver
import jodevapp.mvpkotlin.MainActivity
import jodevapp.mvpkotlin.R
import jodevapp.mvpkotlin.manager.ApiManager
import jodevapp.mvpkotlin.model.Post
import jodevapp.mvpkotlin.ui.PostView
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
@MediumTest
class MainActivityMockWebServer {

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java, true, false)
    private lateinit var mockServer: MockWebServer
    private lateinit var apiManager: ApiManager
    private lateinit var postView: PostView

    @Before
    @Throws(IOException::class)
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
        apiManager = ApiManager
    }

    @Test
    fun requestSuccessPost() {
        val testObserver = TestObserver<List<Post>>()
        val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody("[\n" +
                        "    {\n" +
                        "        \"userId\": 1,\n" +
                        "        \"id\": 1,\n" +
                        "        \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                        "        \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"userId\": 1,\n" +
                        "        \"id\": 2,\n" +
                        "        \"title\": \"qui est esse\",\n" +
                        "        \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                        "    }]")
        mockServer.enqueue(mockResponse)
        apiManager.requestPost().subscribe(testObserver)
        testObserver.assertNoErrors()

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withId(R.id.lytError)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

    @Test
    fun requestErrorPost() {
        val testObserver = TestObserver<List<Post>>()
        val mockResponse = MockResponse()
                .setResponseCode(404)
                .setBody("[\n" +
                        "    {\n" +
                        "        \"userId\": 1,\n" +
                        "        \"id\": 1,\n" +
                        "        \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
                        "        \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"userId\": 1,\n" +
                        "        \"id\": 2,\n" +
                        "        \"title\": \"qui est esse\",\n" +
                        "        \"body\": \"est rerum tempore vitae\\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\\nqui aperiam non debitis possimus qui neque nisi nulla\"\n" +
                        "    }]")
        mockServer.enqueue(mockResponse)
        apiManager.requestPost().subscribe(testObserver)
        testObserver.assertNoValues()

        val intent = Intent()
        mActivityRule.launchActivity(intent)

        onView(withId(R.id.lytError)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }
}
