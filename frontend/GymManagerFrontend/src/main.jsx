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
import Trainers from "./routes/trainers.jsx";
import BookSession, {action as bookSessionAction}  from "./routes/bookSession.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <ErrorPage />,
        children: [
            {
                path: "contacts/:contactId",
                element: <Contact />,
                loader: contactLoader,
            },
            {
                path: "contacts/:contactId/edit",
                element: <EditContact />,
                loader: contactLoader,
                action: editAction,
            },
        ],
    },
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
        errorElement: <ErrorPage />,
    },
    {
        path: "/booksession",
        element: <BookSession />,
        errorElement: <ErrorPage />,
        action: bookSessionAction,
    }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);