import { useParams } from "react-router-dom"


const TourBooking = () => {

    const { tourId } = useParams();

  return (
    <div><h1>{tourId}</h1></div>
  )
}

export default TourBooking