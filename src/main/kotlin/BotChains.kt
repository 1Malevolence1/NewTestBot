import activity_api.ActivityCloud
import chain.ActivityChain
import chain.StartCategoriesChain
import chain.StartGreeting
import core.BotChains
import handlers.CommandEvent
import okhttp3.internal.immutableListOf

class BotChains(
    private val mKey: String
) : BotChains {

    override fun chains() = immutableListOf(
        StartGreeting(
            mKey,
            CommandEvent("/start")
        ),
        ActivityChain(
            mKey,
            CommandEvent("/activity"),
            ActivityCloud.Base(
                mClient
            )
        ),
        StartCategoriesChain(
            mKey,
            CommandEvent("/categories")
        )
    )
}