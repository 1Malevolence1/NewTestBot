package activity_api

import org.json.JSONObject

data class Activity(
    private val mName: String,
    private val mAccessibility: Float,
    private val mType: String,
    private val mParticipants: Int,
    private val mPrice: Float,
    private val mLink: String,
    private val mKey: String
) {
    constructor(json: JSONObject) : this(
        json.getString("activity"),
        json.getFloat("accessibility"),
        json.getString("type"),
        json.getInt("participants"),
        json.getFloat("price"),
        json.getString("link"),
        json.getString("key")
    )

    fun <T> map(mapper: Mapper<T>) : T = mapper.map(
        mName,
        mAccessibility,
        mType,
        mParticipants,
        mPrice,
        mLink,
        mKey
    )

      interface Mapper<T> {
        fun map(
            name: String,
            accessibility: Float,
            type: String,
            participants: Int,
            price: Float,
            link: String,
            key: String
        ) : T
    }
}