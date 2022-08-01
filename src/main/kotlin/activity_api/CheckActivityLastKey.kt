package activity_api

import core.Updating
import staging.StateHandling

/**
 *  Return true if last activity key and current key is not equals
 */
class CheckActivityLastKey(
    private val mStates: StateHandling,
    private val mUpdating: Updating
) : Activity.Mapper<Boolean> {
    override fun map(
        name: String,
        accessibility: Float,
        type: String,
        participants: Int,
        price: Float,
        link: String,
        key: String
    ) = key != mStates.state(mUpdating).string("activityKey")
}