package activity_api

import core.Updating
import staging.StateHandling

interface ActivityChecking {

    suspend fun randomActivity(updating: Updating): Activity

    suspend fun randomByCategoryActivity(category: String, updating: Updating): Activity

    class Base(
        private val mStateHandling: StateHandling,
        private val mActivityCloud: ActivityCloud
    ) : ActivityChecking {
        override suspend fun randomActivity(updating: Updating): Activity {
            var randomActivity = mActivityCloud.randomActivity()
            while (randomActivity.map(CheckActivityLastKey(mStateHandling, updating))) {
                randomActivity = mActivityCloud.randomActivity()
            }
            randomActivity.map(PutActivityKeyToState(mStateHandling, updating))
            return randomActivity
        }


        override suspend fun randomByCategoryActivity(category: String, updating: Updating): Activity {
            var randomActivity = mActivityCloud.randomByCategoryActivity(category)
            while (randomActivity.map(CheckActivityLastKey(mStateHandling, updating))) {
                randomActivity = mActivityCloud.randomByCategoryActivity(category)
            }
            randomActivity.map(PutActivityKeyToState(mStateHandling, updating))
            return randomActivity
        }

        }

    }
