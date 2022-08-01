package activity_api

import helpers.ToMarkdownSupported

class ActivityToMessageText : Activity.Mapper<String> {

    override fun map(
        name: String,
        accessibility: Float,
        type: String,
        participants: Int,
        price: Float,
        link: String,
        key: String
    ) = buildString {
        appendLine("*Activity*")
        appendLine("${ToMarkdownSupported.Base(name).convertedString()}\n")
        appendLine("Type\\: $type")

        appendLine("Participants\\: $participants")
        appendLine("Price\\: ${ToMarkdownSupported.Base(price.toString()).convertedString()}")
        if (link.isNotEmpty()) {
            appendLine("Link\\: ${ToMarkdownSupported.Base(link).convertedString()}")
        }
    }
}