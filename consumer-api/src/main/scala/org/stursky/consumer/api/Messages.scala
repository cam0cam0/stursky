package org.stursky.consumer.api

import play.api.libs.json.{Format, Json}

case class ConsumeRequest(phone: String)
object ConsumeRequest{
  implicit val consumeRequestFormat: Format[ConsumeRequest] = Json.format[ConsumeRequest]
}

