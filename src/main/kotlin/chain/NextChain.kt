package chain

import activity_api.ActivityCloud
import activity_api.ActivityToMessageText
import core.Updating
import executables.AnswerToCallback
import executables.EditTextMessage
import executables.Executable
import handlers.BotRecognizerEvent
import handlers.UnhandledEvent
import helpers.convertToVertical
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup
import updating.UpdatingCallbackData

class NextChain(
    private val mKey: String,
    private val mEvent: BotRecognizerEvent,
    private val mActivityProvider: ActivityCloud

) : Chain {
    override suspend fun executableChain(updating: Updating): List<Executable> {
        return try {
            updating.map(mEvent)
            // next&music
            val data = updating.map(UpdatingCallbackData())
            if (data.contains("next")) {
                val category = data.split("&")[1]
                listOf(
                    AnswerToCallback(
                        mKey,
                        "Перехожу к следующиму занятию"
                    ),
                    EditTextMessage(
                        mKey,
                        mActivityProvider.randomByCategoryActivity(
                            category
                        ).map(ActivityToMessageText()),
                        mMarkup = InlineKeyboardMarkup(
                            listOf(
                                InlineButton(
                                    "Next",
                                    mCallbackData = "next&$category"
                                ),
                                InlineButton(
                                    "Back",
                                    mCallbackData = "back"
                                )
                            ).convertToVertical()
                        )
                    )
                )
            } else {
                emptyList()
            }

        } catch (e: UnhandledEvent) {
            listOf()
        }
    }
}