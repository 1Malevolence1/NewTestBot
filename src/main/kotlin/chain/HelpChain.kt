package chain

import activity_api.ActivityCloud
import core.Updating
import executables.AnswerToCallback
import executables.EditTextMessage
import executables.Executable
import executables.SendMessage
import handlers.BotRecognizerEvent
import handlers.UnhandledEvent

class HelpChain(
    private val mKey : String,
    private val mEvent : BotRecognizerEvent

) : Chain {
    override suspend fun executableChain(updating: Updating): List<Executable> {
        return try {
            updating.map(mEvent)
            listOf(
                SendMessage("" +
                        "Сейчас я расскажу, что делает эотот бот\\.\nВы вы выбираете одну одну из предложенных вам категорий, " +
                        "кторое вам ближе по душе, или же выбираете случайную\\.\nВам выдаётся задание надень, которое вы должны выполинть\\." +
                        "Если вам не нравится это задание, то вы можете сменить его на следующие\\. " +
                        "\n" +
                        "\n" +
                        "*Type* \\- показывает ту категорию, которую вы выбрали\\. \n" +
                        "\n" +
                        "*Participants* \\- показывает соклько нужно участников, чтобы выполнять задание\\." +
                        "\n" +
                        "\n" +
                        "*Price* \\- показыват на сколько затратное может быть задание\\."
                    ,mKey)
            )

        } catch (e : UnhandledEvent){
            emptyList()
        }
    }
}