package chain

import core.Updating
import executables.AnswerToCallback
import executables.EditTextMessage
import executables.Executable
import executables.SendMessage
import handlers.BotRecognizerEvent
import handlers.UnhandledEvent
import helpers.convertToVertical
import keyboard_markup.InlineButton
import keyboard_markup.InlineKeyboardMarkup

class BackChain(
    private val mKey : String,
    private  val mEvent : BotRecognizerEvent


) : Chain {
    override suspend fun executableChain(updating: Updating): List<Executable> {
        return try {
            updating.map(mEvent)
            listOf(
                AnswerToCallback(mKey,
                    ""),
                EditTextMessage(mKey,
                    "Здесь представлены все категории занятий\\.",
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
        } catch (e : UnhandledEvent){
            emptyList()
        }
    }
}