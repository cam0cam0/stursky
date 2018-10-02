package org.stursky.trip.impl

import com.lightbend.lagom.scaladsl.api.ServiceLocator
import com.lightbend.lagom.scaladsl.api
.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.devmode
.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.
cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication,
  LagomApplicationContext, LagomApplicationLoader, LagomServer}
import com.softwaremill.macwire.wire
import play.api.libs.ws.ahc.AhcWSComponents
import org.stursky.trip.api.TripService

class TripLoader extends LagomApplicationLoader {
  override def load(context: LagomApplicationContext):
  LagomApplication =
    new TripApplication(context) { override def serviceLocator:
    ServiceLocator = NoServiceLocator }
  override def loadDevMode(context: LagomApplicationContext):
  LagomApplication = {
    new TripApplication(context) with LagomDevModeComponents
  }
  override def describeServices =
    List(readDescriptor[TripService])
}

abstract class TripApplication(context: LagomApplicationContext) extends LagomApplication(context) with
  CassandraPersistenceComponents with AhcWSComponents {
    override lazy val lagomServer = LagomServer.forService(
      bindService[TripService].to(wire[TripServiceImpl])
    )
    override lazy val jsonSerializerRegistry = ClientSerializerRegistry

    persistentEntityRegistry.register(wire[ClientEntity])
}