package org.stursky.trip.impl

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import org.stursky.trip.api.ReportLocation
import play.api.libs.json.{Format, Json}

sealed trait ClientCommand[R] extends ReplyType[R]
case object StartTrip extends ClientCommand[Done]
case object EndTrip extends ClientCommand[Done]
case class AddLocation(reportLocationRequest: ReportLocation) extends ClientCommand[Done]
sealed trait ClientEvent
case class TripStarted(time: Long) extends ClientEvent
case class TripEnded(time: Long) extends ClientEvent
case class LocationAdded(location: Location) extends ClientEvent
case class Location(lat: Double, lon: Double)
case class ClientState(tripInProgress: Boolean, locations: List[Location])

object Location {implicit val format: Format[Location] = Json.format[Location]}
object ClientState {implicit val format: Format[ClientState] = Json.format[ClientState]}
object TripStarted {implicit val format: Format[TripStarted] = Json.format[TripStarted]}
object TripEnded {implicit val format: Format[TripEnded] = Json.format[TripEnded]}
object LocationAdded {implicit val format: Format[LocationAdded] = Json.format[LocationAdded]}
object AddLocation {implicit val format: Format[AddLocation] = Json.format[AddLocation]}








