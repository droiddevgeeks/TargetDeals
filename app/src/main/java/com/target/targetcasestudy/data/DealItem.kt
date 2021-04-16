package com.target.targetcasestudy.data

import com.google.gson.annotations.SerializedName

/*{
  "id": 1,
  "title": "sint aliqua mollit duis ullamco",
  "aisle": "g33",
  "description": "ad laboris do ad id ipsum dolore ad magna occaecat ea eu ex nisi culpa amet id officia labore ut tempor est dolor commodo aliqua ex nisi consectetur veniam ut aliquip amet esse exercitation voluptate aute id adipisicing nostrud quis non eu dolore ipsum ut officia pariatur anim amet id ex veniam sunt sit officia non excepteur cupidatat consequat incididunt ad culpa aliqua nisi magna voluptate esse excepteur id magna amet aute enim esse enim ex esse nostrud et sint nostrud irure ex aute nisi nisi nisi minim Lorem duis officia reprehenderit eiusmod ea magna tempor est",
  "image_url": "https://picsum.photos/id/1/300/300",
  "regular_price": {
  "amount_in_cents": 4025,
  "currency_symbol": "$",
  "display_string": "$40.25"},
  "sale_price": {
  "amount_in_cents": 734,
  "currency_symbol": "$",
  "display_string": "$7.34"}
}*/

data class Products(val products: List<DealItem>)
data class DealItem(
  var id: Int,
  var title: String,
  var description: String,
  var aisle: String,
  @SerializedName("regular_price") val regularPrice: Price,
  @SerializedName("sale_price") val salePrice: Price?,
  @SerializedName("image_url") val productImage: String
)

data class Price(
  @SerializedName("display_string") val price: String,
)

