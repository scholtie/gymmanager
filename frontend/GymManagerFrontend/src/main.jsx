import React from "react";
import ReactDOM from "react-dom/client";
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import "./index.css";
import Root, {loader as rootLoader} from "./routes/root";
import ErrorPage from "./error-page.jsx";
import TrainerRegistrationForm, {loader as registerTrainerLoader, action as registerTrainerAction} from "./routes/auth/registerTrainer.jsx";
import Trainers, {loader as trainersLoader} from "./routes/trainer/trainers.jsx";
import BookSession, {loader as bookSessionLoader}  from "./routes/session/bookSession.jsx";
import Gyms, {loader as gymsLoader} from "./routes/gym/gyms.jsx";
import Gym, {loader as gymLoader} from "./routes/gym/gym.jsx";
import RegisterGym, {action as registerGymAction} from "./routes/auth/registerGym.jsx";
import Subscribe, {action as subscribeAction, loader as subscribeLoader} from "./routes/subscribe/subscribe.jsx";
import CreateSessionOption, {action as createSessionOptionAction} from "./routes/sessionOption/createSessionOption.jsx";
import Subscriptions, {loader as subscriptionsLoader} from "./routes/subscribe/subscriptions.jsx";
import Success from "./routes/success.jsx";
import Review from "./routes/review/review.jsx";
import ReviewTrainer, {action as reviewTrainerAction, loader as reviewTrainerLoader} from "./routes/review/reviewTrainer.jsx";
import ReviewGym, {action as reviewGymAction, loader as reviewGymLoader} from "./routes/review/reviewGym.jsx";
import Login, {action as loginAction, loader as loginLoader} from "./routes/auth/login.jsx";
import SetBusinessHours, {action as businessHoursAction, loader as businessHoursLoader} from "./routes/businessHours/setBusinessHours.jsx";
import Profile, {loader as profileLoader} from "./routes/profile.jsx";
import CustomerRegistrationForm, {action as registerCustomerAction} from "./routes/auth/registerCustomer.jsx";
import RegistrationForm, {action as registerAction, loader as registerLoader} from "./routes/auth/register.jsx";
import Logout, {loader as logoutLoader} from "./routes/auth/logout.jsx";
import BookSessionByDate, {action as bookSessionByDateAction, loader as bookSessionByDateLoader} from "./routes/session/bookSessionByDate.jsx";
import Sessions, {loader as sessionsLoader} from "./routes/session/sessions.jsx";
import Progress, {loader as progressLoader} from "./routes/progress/progress.jsx";
import CreateNumericGoal, {action as numericGoalAction} from "./routes/progress/createNumericGoal.jsx";
import CreateRepetitionGoal, {action as repetitionGoalAction} from "./routes/progress/createRepetitionGoal.jsx";
import Trainer, {loader as trainerLoader} from "./routes/trainer/trainer.jsx";
import SessionOptions, {loader as sessionOptionsLoader} from "./routes/sessionOption/sessionOptions.jsx";
import EditSessionOption, {action as editSessionOptionAction, loader as editSessionOptionLoader} from "./routes/sessionOption/editSessionOption.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        loader: rootLoader,
        errorElement: <ErrorPage />,
        children: [
            {
                path: "/register",
                element: <RegistrationForm />,
                action: registerAction,
                loader: registerLoader,
                errorElement: <ErrorPage />,
            },
            {
                path: "/register/customer",
                element: <CustomerRegistrationForm />,
                action: registerCustomerAction,
                errorElement: <ErrorPage />,
            },
            {
                path: "/register/trainer",
                element: <TrainerRegistrationForm />,
                action: registerTrainerAction,
                loader: registerTrainerLoader,
                errorElement: <ErrorPage />,
            },
            {
                path: "/trainers",
                element: <Trainers />,
                loader: trainersLoader,
                errorElement: <ErrorPage />,
            },
            {
                path: "/book-session",
                element: <BookSession />,
                errorElement: <ErrorPage />,
                loader: bookSessionLoader,
            },
            {
                path: "/book-session/:trainerId/:date",
                element: <BookSessionByDate />,
                errorElement: <ErrorPage />,
                loader: bookSessionByDateLoader,
                action: bookSessionByDateAction,
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
                path: "/register/gym",
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
            },
            {
                path: "/success",
                element: <Success />,
                errorElement: <ErrorPage />
            },
            {
                path: "/review",
                element: <Review />,
                errorElement: <ErrorPage />
            },
            {
                path: "/review/trainer",
                element: <ReviewTrainer />,
                action: reviewTrainerAction,
                loader: reviewTrainerLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/review/gym",
                element: <ReviewGym />,
                action: reviewGymAction,
                loader: reviewGymLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/login",
                element: <Login />,
                action: loginAction,
                loader: loginLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/setBusinessHours",
                element: <SetBusinessHours />,
                loader: businessHoursLoader,
                action: businessHoursAction,
                errorElement: <ErrorPage />
            },
            {
                path: "/profile",
                element: <Profile />,
                loader: profileLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/logout",
                element: <Logout />,
                loader: logoutLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/sessions",
                element: <Sessions />,
                loader: sessionsLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/progress",
                element: <Progress />,
                loader: progressLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/progress/create-numeric-goal",
                element: <CreateNumericGoal />,
                action: numericGoalAction,
                errorElement: <ErrorPage />
            },
            {
                path: "/progress/create-repetition-goal",
                element: <CreateRepetitionGoal />,
                action: repetitionGoalAction,
                errorElement: <ErrorPage />
            },
            {
                path: "/trainer/:trainerId",
                element: <Trainer />,
                loader: trainerLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/session-options",
                element: <SessionOptions />,
                loader: sessionOptionsLoader,
                errorElement: <ErrorPage />
            },
            {
                path: "/session-options/edit/:sessionOptionId",
                element: <EditSessionOption />,
                loader: editSessionOptionLoader,
                action: editSessionOptionAction,
                errorElement: <ErrorPage />
            },
        ],
    },

]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);
