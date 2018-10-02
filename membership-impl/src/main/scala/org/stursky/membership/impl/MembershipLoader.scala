package org.stursky.membership.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.server._
import com.softwaremill.macwire._
import play.api.libs.ws.ahc.AhcWSComponents
import org.stursky.membership.api.MembershipService

class MembershipLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext):
  LagomApplication =
    new MembershipApplication(context) {
      override def serviceLocator:
        ServiceLocator = NoServiceLocator
    }

  override def loadDevMode (context: LagomApplicationContext):
  LagomApplication =
    new MembershipApplication(context) with LagomDevModeComponents

  override def describeServices = List(readDescriptor[MembershipService])
}

abstract class MembershipApplication(context: LagomApplicationContext)
  extends LagomApplication(context) with AhcWSComponents{
  override lazy val lagomServer = LagomServer.forService(
    bindService[MembershipService].to(wire[MembershipServiceImpl])
  )
}