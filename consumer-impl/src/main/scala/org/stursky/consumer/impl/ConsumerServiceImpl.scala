package org.stursky.consumer.impl

import com.lightbend.lagom.scaladsl.api.ServiceCall
import org.stursky.consumer.api.ConsumerService
import org.stursky.membership.api.{GetMembershipRequest, MembershipService}

import scala.concurrent.ExecutionContext

class ConsumerServiceImpl(mService: MembershipService)
  (implicit ec: ExecutionContext) extends ConsumerService {
  override def consume = ServiceCall {
    request => {
      val getMembershipRequest = GetMembershipRequest("8703120101")
      mService.getMembership.invoke(getMembershipRequest).map(_.membership_id)
    }
  }
}