import chain.*
import core.Bot
import core.BotChains
import handlers.CommandEvent
import handlers.OnCallbackGotten
import handlers.OnTextGotten
import okhttp3.internal.immutableListOf

class BotChains(
    private val mKey: String
) : BotChains {

    override fun chains() = immutableListOf(
        StartGreeting(
            mKey,
            CommandEvent("/start")

        )
    )
}