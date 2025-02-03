import { useDispatch } from "react-redux";
import Hero from "../components/Hero";
import TourCategoryCard from "../components/tours/TourCategoryCard";
import TourLoader from "../components/tours/TourLoader";
import { tourCategoryAPI } from "../services/TourCategoryService";
import { addInitialTourCategories } from "../store/features/tour/tourCategorySlice";
import { tourSubCategoryAPI } from "../services/TourSubCategoryService";
import { addInitialTourSubCategories } from "../store/features/tour/tourSubCategorySlice";

const HomePage = () => {
  const { data, isLoading, isSuccess } = tourCategoryAPI.useFetchTourCategoriesQuery();
  const {data: subTourData, isLoading: subTourLoading, isSuccess: subTourSuccess} = tourSubCategoryAPI.useFetchAllTourSubCategoriesQuery();

  const dispatch = useDispatch();

  if (isSuccess && subTourSuccess) {
    dispatch(addInitialTourCategories(data?.data?.tourCategories));
    dispatch(addInitialTourSubCategories(subTourData?.data?.tourSubcategories));
  }

  if(isLoading && subTourLoading) {
    return (<TourLoader />);
  }

  return (
    <div className="home-page">
      <Hero />

      <section className="py-6 bg-light">
        <div className="container">
          <div className="text-center mb-5">
            <h2 className="display-5 fw-semibold mb-3 py-4">
              Tour Categories
            </h2>
            <h5 className="text-muted">Explore our tours</h5>
          </div>

          <div className="row g-4">
            {data?.data?.tourCategories?.map((tour) => (
              <TourCategoryCard tour={tour} key={tour.id} />
            ))}
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
