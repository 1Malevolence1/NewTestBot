package activity_api

import core.Updating
import staging.StateHandling

class PutActivityKeyToState(
    private val mStates: StateHandling,
    private val mUpdating: Updating
) : Activity.Mapper<Unit> {
    override fun map(
        name: String,
        accessibility: Float,
        type: String,
        participants: Int,
        price: Float,
        link: String,
        key: String
    ) {
        mStates.state(mUpdating).editor(mStates).apply {
            putString("activityKey", key)
        }.commit()
    }
}