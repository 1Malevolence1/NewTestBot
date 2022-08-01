package chain

import activity_api.ActivityCloud
import activity_api.ActivityToMessageText
import activity_api.PutActivityKeyToState
import core.Updating
import executables.Executable
import executables.SendMessage
import handlers.BotRecognizerEvent
import handlers.UnhandledEvent
import staging.NotFoundStateValue
import staging.StateHandling

class ActivityChain(
    private val mKey: String,
    private val mEvent: BotRecognizerEvent,
    private val mActivityProvider: ActivityCloud
) : Chain {

    override suspend fun executableChain(updating: Updating): List<Executable> {
        return try {
            updating.map(mEvent)
            val activity = mActivityProvider.randomActivity()
            listOf(
                SendMessage(
                    activity.map(ActivityToMessageText()),
                    mKey
                )
            )
        } catch (e: UnhandledEvent) {
            emptyList()
        }
    }
}