
import com.google.gson.annotations.SerializedName


data class MeasureResponse(
    @SerializedName("house_value")
    var houseValue: Double?
)

data class MeasureRequest(
    @SerializedName("AveBedrms")
    var aveBedrms: Int?,
    @SerializedName("AveOccup")
    var aveOccup: Int?,
    @SerializedName("AveRooms")
    var aveRooms: Int?,
    @SerializedName("HouseAge")
    var houseAge: Int?,
    @SerializedName("Latitude")
    var latitude: Int?,
    @SerializedName("Longitude")
    var longitude: Int?,
    @SerializedName("MedInc")
    var medInc: Int?,
    @SerializedName("Population")
    var population: Int?
)