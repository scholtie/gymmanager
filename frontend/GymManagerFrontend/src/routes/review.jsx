import axios from "axios";
import {Form, Link, redirect, useLoaderData, useLocation} from "react-router-dom";
import React, {useEffect, useState} from "react";
import {MenuItem, Select, TextField} from "@mui/material";
import {AdapterDateFns} from "@mui/x-date-pickers/AdapterDateFns";
import {DatePicker, DateTimePicker, LocalizationProvider} from "@mui/x-date-pickers";

export default function Review() {
    return (
        <div>
            <Link to={`/review/gym`}>
                Review a gym
            </Link>
            <p>
            <Link to={`/review/trainer`}>
                Review a trainer
            </Link>
            </p>
        </div>
    );
}
