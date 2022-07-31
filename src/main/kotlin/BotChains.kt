import activity_api.ActivityCloud
import chain.*
import core.BotChains
import handlers.CommandEvent
import handlers.OnCallbackGotten
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
        NextChain(
            mKey,
            OnCallbackGotten(),
            ActivityCloud.Base(
                mClient
            )
        ),
        BackChain(
          mKey,
          OnCallbackGotten("back")
        ),
        StartCategoriesChain(
            mKey,
            CommandEvent("/categories")
        ),
        ParticularCategoryChain(
            mKey,
            OnCallbackGotten(),
            ActivityCloud.Base(
                mClient
            )
        )
    )
}