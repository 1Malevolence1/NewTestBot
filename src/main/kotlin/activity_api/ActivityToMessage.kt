package activity_api

import executables.Executable
import executables.SendMessage
import helpers.ToMarkdownSupported

class ActivityToMessage(
    private val mKey: String
) : Activity.Mapper<Executable> {

    override fun map(
        name: String,
        accessibility: Float,
        type: String,
        participants: Int,
        price: Float,
        link: String,
        key: String
    ) = SendMessage(
        buildString {
            appendLine("*Activity*")
            appendLine("${ToMarkdownSupported.Base(name).convertedString()}\n")
            appendLine("Type\\: $type")
            appendLine(
                "Accessibility\\: ${
                    ToMarkdownSupported.Base(
                        accessibility.toString()
                    ).convertedString()
                }"
            )
            appendLine("Participants\\: $participants")
            appendLine("Price\\: ${ToMarkdownSupported.Base(price.toString()).convertedString()}")
            appendLine("Link\\: $link")
        },
        mKey
    )
}