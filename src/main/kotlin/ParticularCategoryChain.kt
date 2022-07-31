import activity_api.Activity
import activity_api.ActivityCloud
import activity_api.ActivityToMessageText
import chain.Chain
import com.sun.net.httpserver.Filter
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
import java.util.spi.CalendarDataProvider

class ParticularCategoryChain(
    private val mKey : String,
    private val mEvent : BotRecognizerEvent,
    private val mActivityProvider: ActivityCloud


) : Chain {

    override suspend fun executableChain(updating: Updating): List<Executable> {
       return try {
           updating.map(mEvent)
           val data = updating.map(UpdatingCallbackData())
           listOf(
               AnswerToCallback(
                   mKey,
                   "Перехожу к категории"
               ),
               EditTextMessage(
                   mKey,
                   mActivityProvider.randomByCategoryActivity(
                       data
                   ).map(ActivityToMessageText()),
                   mMarkup = InlineKeyboardMarkup(
                       listOf(
                           InlineButton(
                               "Next",
                               mCallbackData = "next&$data"
                           ),
                           InlineButton("Back",
                           mCallbackData = "back")
                       ).convertToVertical()
                   )
               )
           )
        }
        catch (e : UnhandledEvent) {
            emptyList()
        }
   }
}