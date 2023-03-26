import React from "react";
import ReactDOM from "react-dom/client";
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import "./index.css";
import Root from "./routes/root";
import ErrorPage from "./error-page.jsx";
import Contact, {loader as contactLoader} from "./routes/contact.jsx";
import EditContact, {action as editAction,} from "./routes/edit.jsx";
import CallApi from "./routes/callApi.jsx";
import RegistrationForm, {action as registerAction} from "./routes/register.jsx";
import TrainerRegistrationForm, {action as registerTrainerAction} from "./routes/registerTrainer.jsx";
import Trainers, {loader as trainersLoader} from "./routes/trainers.jsx";
import BookSession, {action as bookSessionAction, loader as bookSessionLoader}  from "./routes/bookSession.jsx";
import Gyms, {loader as gymsLoader} from "./routes/gyms.jsx";
import Gym, {loader as gymLoader} from "./routes/gym.jsx";
import RegisterGym, {action as registerGymAction} from "./routes/registerGym.jsx";
import Subscribe, {action as subscribeAction, loader as subscribeLoader} from "./routes/subscribe.jsx";
import CreateSessionOption, {action as createSessionOptionAction} from "./routes/createSessionOption.jsx";
import Subscriptions, {loader as subscriptionsLoader} from "./routes/subscriptions.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <ErrorPage />,
        children: [
            {
                path: "/callapi",
                element: <CallApi />,
                errorElement: <ErrorPage />,
            },
            {
                path: "/register",
                element: <RegistrationForm />,
                action: registerAction,
                errorElement: <ErrorPage />,
            },
            {
                path: "/registerTrainer",
                element: <TrainerRegistrationForm />,
                action: registerTrainerAction,
                errorElement: <ErrorPage />,
            },
            {
                path: "/trainers",
                element: <Trainers />,
                loader: trainersLoader,
                errorElement: <ErrorPage />,
            },
            {
                path: "/booksession",
                element: <BookSession />,
                errorElement: <ErrorPage />,
                action: bookSessionAction,
                loader: bookSessionLoader,
            },
            {
                path: "/gyms",
                element: <Gyms />,
                loader: gymsLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/gyms/:gymId",
                element: <Gym />,
                loader: gymLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/registerGym",
                element: <RegisterGym />,
                action: registerGymAction,
                errorElement: <ErrorPage />
            },
            {
                path: "/subscribe",
                element: <Subscribe />,
                action: subscribeAction,
                loader: subscribeLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/createSessionOption",
                element: <CreateSessionOption />,
                action: createSessionOptionAction,
                errorElement: <ErrorPage />
            },
            {
                path: "/subscriptions",
                element: <Subscriptions />,
                loader: subscriptionsLoader,
                errorElement: <ErrorPage />
            }
        ],
    },

]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);