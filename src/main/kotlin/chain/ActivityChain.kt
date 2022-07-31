package chain

import activity_api.ActivityCloud
import activity_api.ActivityToMessage
import core.Updating
import executables.Executable
import handlers.BotRecognizerEvent
import handlers.UnhandledEvent

class ActivityChain(
    private val mKey: String,
    private val mEvent: BotRecognizerEvent,
    private val mActivityProvider: ActivityCloud
) : Chain {

    override suspend fun executableChain(updating: Updating): List<Executable> {
        return try {
            updating.map(mEvent)
            listOf(
                mActivityProvider.randomActivity().map(
                    ActivityToMessage(mKey)
                )
            )
        } catch (e: UnhandledEvent) {
            emptyList()
        }
    }
}