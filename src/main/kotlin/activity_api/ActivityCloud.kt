package activity_api

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

interface ActivityCloud {

    suspend fun randomActivity() : Activity

    class Base(
        private val mClient: OkHttpClient
    ) : ActivityCloud {

        override suspend fun randomActivity(): Activity {
            val activityResponse = mClient.newCall(
                Request.Builder()
                    .url("http://www.boredapi.com/api/activity/")
                    .build()
            ).execute()
            return Activity(
                JSONObject(
                    activityResponse.body?.string()
                )
            )
        }
    }
}