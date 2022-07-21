package com.itpeople.sample.mvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class UserResponse(

    @SerializedName("results")
	@ColumnInfo
	var results: List<ResultsItem?>? = null,

    @SerializedName("info")
	var info: Info? = null
)


data class Name(

	@SerializedName("last")
	var last: String? = null,

	@SerializedName("title")
	var title: String? = null,

	@SerializedName("first")
	var first: String? = null
)

data class Login(

	@SerializedName("sha1")
	var sha1: String? = null,

	@SerializedName("password")
	var password: String? = null,

	@SerializedName("salt")
	var salt: String? = null,

	@SerializedName("sha256")
	var sha256: String? = null,

	@SerializedName("uuid")
	var uuid: String? = null,

	@SerializedName("username")
	var username: String? = null,

	@SerializedName("md5")
	var md5: String? = null
)

@Entity(tableName = "table_results")
data class ResultsItem(
    @SerializedName("nat")
	var nat: String? = null,

    @SerializedName("gender")
	var gender: String? = null,

    @SerializedName("phone")
	var phone: String? = null,

    @SerializedName("dob")
	var dob: Dob? = null,

    @SerializedName("name")
	var name: Name? = null,

    @SerializedName("registered")
	var registered: Registered? = null,

    @SerializedName("location")
	var location: Location? = null,

    @Ignore
	@SerializedName("login")
	var login: Login? = null,

    @SerializedName("cell")
	var cell: String? = null,

    @SerializedName("email")
	var email: String? = null,

    @SerializedName("picture")
	var picture: Picture? = null
){
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "r_id")
	var r_id:Int = 0
}

data class Street(

	@SerializedName("number")
	var number: String? = null,

	@SerializedName("name")
	var name: String? = null
)

data class Picture(

	@SerializedName("thumbnail")
	var thumbnail: String? = null,

	@SerializedName("large")
	var large: String? = null,

	@SerializedName("medium")
	var medium: String? = null
)

data class Location(

    @SerializedName("country")
	var country: String? = null,

    @SerializedName("city")
	var city: String? = null,

    @SerializedName("street")
	var street: Street? = null,

    @SerializedName("timezone")
	var timezone: Timezone? = null,

    @SerializedName("postcode")
	var postcode: String? = null,

    @SerializedName("coordinates")
	var coordinates: Coordinates? = null,

    @SerializedName("state")
	var state: String? = null
)

data class Coordinates(

	@SerializedName("latitude")
	var latitude: String? = null,

	@SerializedName("longitude")
	var longitude: String? = null
)

data class Dob(

	@SerializedName("date")
	var date: String? = null,

	@SerializedName("age")
	var age: String? = null
)

data class Registered(

	@SerializedName("date")
	var date: String? = null,

	@SerializedName("age")
	var age: String? = null
)

data class Timezone(

	@SerializedName("offset")
	var offset: String? = null,

	@SerializedName("description")
	var description: String? = null
)

data class Info(

	@SerializedName("seed")
	var seed: String? = null,

	@SerializedName("page")
	var page: String? = null,

	@SerializedName("results")
	var results: String? = null,

	@SerializedName("version")
	var version: String? = null
)
