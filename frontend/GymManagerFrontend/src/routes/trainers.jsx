import {Link, Outlet} from "react-router-dom";
import {useEffect, useState} from "react";
import axios from "axios";

export default function Trainers() {
    const [data, setData] = useState([]);
    const getData = () => {
        axios.get('http://localhost:8081/trainers/')
            .then(res => {
                setData(res.data)
            }).catch(err => {
            console.log(err)
        })
    }
    useEffect(getData);
    return (
        <>
            <div>
                    {data.length ? (
                        <ul>
                            {data.map((trainer) => (
                                <li key={trainer.id}>
                                    <p>
                                        <img src={trainer.imgPath}/>
                                    </p>
                                    <Link to={`/trainer/${trainer.id}`}>
                                        {trainer.firstName || trainer.lastName ? (
                                            <>
                                                {trainer.firstName} {trainer.lastName}

                                            </>
                                        ) : (
                                            <i>No Name</i>
                                        )}{" "}
                                    </Link>
                                    <p>{trainer.introduction}</p>
                                    <p>{trainer.rating ? (trainer.rating) : <i>{trainer.firstName} {trainer.lastName} has no reviews yet.</i>}</p>
                                    <Link to={'/bookSession'}>
                                        Book Session
                                    </Link>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No contacts</i>
                        </p>
                    )}
            </div>
            <div id="detail">
                <Outlet />
            </div>
        </>
    );
}