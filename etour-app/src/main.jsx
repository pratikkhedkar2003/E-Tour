import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { setupStore } from "./store/store.js";
import { Provider } from "react-redux";
import { BrowserRouter } from "react-router-dom";

const store = setupStore();

// const router = createBrowserRouter(
//   createRoutesFromElements(
//     <Route path={"/"} element={<App />}>
//       <Route element={<NavBar />}>
//         <Route index path="/" element={<Navigate to="/home" />} />
//         <Route index path="/contact-us" element={<ContactUs />} />
//         <Route index path="/home" element={<HomePage />} />
//         <Route path={"login"} element={<Login />} />
//         <Route path={"register"} element={<Register />} />
//         <Route path={"resetpassword"} element={<ResetPassword />} />
//         <Route path={"verify/password"} element={<VerifyPassword />} />
//         <Route path={"verify/account"} element={<VerifyAccount />} />

//         <Route element={<ProtectedRoute />}>
//           <Route element={<RestrictedRoute />}>
//             <Route path={"users"} element={<Users />} />
//           </Route>

//           <Route path={"/user"} element={<User />}>
//             <Route path={"/user"} element={<Navigate to={"/user/profile"} />} />
//             <Route path={"profile"} element={<Profile />} />
//             <Route path={"password"} element={<Password />} />
//           </Route>
//         </Route>
//       </Route>
//       <Route path={"*"} element={<NotFound />} />
//     </Route>
//   )
// );

createRoot(document.getElementById("root")).render(
  <Provider store={store}>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </Provider>
);
