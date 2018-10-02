package org.stursky.membership.api

import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}

trait MembershipService extends Service {
  def getMembership: ServiceCall[GetMembershipRequest, GetMembershipResult]

  override final def descriptor = {
    import Service._
    named("membership").withCalls(
      pathCall("/membership/get", getMembership)
    ).withAutoAcl(true)
  }


}