import React from "react";
import ReactDOM from "react-dom/client";
import {
    createBrowserRouter,
    RouterProvider,
} from "react-router-dom";
import "./index.css";
import Root, { loader as rootLoader, action as rootAction } from "./routes/root";
import ErrorPage from "./error-page.jsx";
import Contact, {loader as contactLoader} from "./routes/contact.jsx";
import EditContact, {action as editAction,} from "./routes/edit.jsx";
import CallApi from "./routes/callApi.jsx";
import RegistrationForm, {action as registerAction} from "./routes/register.jsx";

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        errorElement: <ErrorPage />,
        loader: rootLoader,
        action: rootAction,
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
    }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
    <React.StrictMode>
        <RouterProvider router={router} />
    </React.StrictMode>
);