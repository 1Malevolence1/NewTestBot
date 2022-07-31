package chain

import core.Updating
import executables.Executable
import executables.SendMessage
import handlers.BotRecognizerEvent
import handlers.UnhandledEvent
import helpers.convertToVertical
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup

class StartCategoriesChain(
    private val mKey: String,
    private val mEvent: BotRecognizerEvent
) : Chain {

    override suspend fun executableChain(updating: Updating): List<Executable> {
        return try {
            updating.map(mEvent)
            listOf(
                SendMessage(
                    "Здесь представлены все категории занятий\\.",
                    mKey,
                    mMarkup = InlineKeyboardMarkup(
                        listOf(
                            InlineButton(
                                "Education",
                                mCallbackData = "education"
                            ),
                            InlineButton(
                                "Recreational",
                                mCallbackData = "recreational"
                            ),
                            InlineButton(
                                "Social",
                                mCallbackData = "social"
                            ),
                            InlineButton(
                                "DIY",
                                mCallbackData = "diy"
                            ),
                            InlineButton(
                                "Charity",
                                mCallbackData = "charity"
                            ),
                            InlineButton(
                                "Cooking",
                                mCallbackData = "cooking"
                            ),
                            InlineButton(
                                "Relaxation",
                                mCallbackData = "relaxation"
                            ),
                            InlineButton(
                                "Music",
                                mCallbackData = "music"
                            ),
                            InlineButton(
                                "Busywork",
                                mCallbackData = "busywork"
                            ),
                        ).convertToVertical()
                    )
                )
            )
        } catch (e: UnhandledEvent) {
            emptyList()
        }
    }
}