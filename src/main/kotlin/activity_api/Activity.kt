package activity_api

data class Activity(
    private val mName: String,
    private val mAccessibility: Float,
    private val mType: String,
    private val mParticipants: Int,
    private val mPrice: Float,
    private val mLink: String,
    private val mKey: String
) {

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