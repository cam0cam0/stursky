package org.stursky.membership.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import org.stursky.membership.api._
import scala.concurrent.{ExecutionContext, Future}

class MembershipServiceImpl (implicit val ec: ExecutionContext) extends MembershipService{

  override def getMembership:
    ServiceCall[GetMembershipRequest, GetMembershipResult] = ServiceCall {
      request => Future {
        GetMembershipResult("012321232")
      }
  }
}