package activity_api

import org.json.JSONObject


class ActivityJson : Activity.Mapper<JSONObject>{
    override fun map(
        name: String,
        accessibility: Float,
        type: String,
        participants: Int,
        price: Float,
        link: String,
        key: String
    ): JSONObject {
        val json = JSONObject()
        json.put("activity", name)
        json.put("accessibility",accessibility)
        json.put("type", type )
        json.put("participants", participants)
        json.put("price", price)
        json.put("link", link)
        json.put("key", key)
        return json
    }
}
