package org.stursky.membership.api

import play.api.libs.json.{Format, Json}

case class GetMembershipRequest(phone: String)
case class GetMembershipResult(membership_id: String)

object GetMembershipRequest{
  implicit val getMembershipRequestFormat:
    Format[GetMembershipRequest] =
    Json.format[GetMembershipRequest]
}
object GetMembershipResult{
  implicit val getMembershipResultFormat:
    Format[GetMembershipResult] = Json.format[GetMembershipResult]
}


