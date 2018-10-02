package org.stursky.consumer.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api
.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode
.LagomDevModeComponents
import com.lightbend.lagom.scaladsl
.server.{LagomApplication, LagomApplicationContext,
  LagomApplicationLoader, LagomServer}

import org.stursky.consumer.api.ConsumerService
import org.stursky.membership.api.MembershipService

import com.softwaremill.macwire.wire
import play.api.libs.ws.ahc.AhcWSComponents

class ConsumerLoader extends LagomApplicationLoader{
  override def load(context: LagomApplicationContext):
  LagomApplication =
    new ConsumerApplication(context) with LagomDevModeComponents
    override def describeServices = List(readDescriptor[ConsumerService])
}

abstract class ConsumerApplication(context: LagomApplicationContext) extends LagomApplication(context)
  with AhcWSComponents {
    override lazy val lagomServer = LagomServer.forService(bindService[ConsumerService].to(wire[ConsumerServiceImpl]))
    lazy val membershipService = serviceClient.implement[MembershipService]
}