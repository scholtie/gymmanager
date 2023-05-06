import {Link, useLoaderData} from "react-router-dom";
import {Button} from "@mui/material";
import moment from "moment/moment.js";

export async function loader({params}) {
    let trainer;
    let average;
    let [reviews] = []
    let [sessionOptions] = [];
    let [businessHours] = [];
    const trainerId = params.trainerId;
    await fetch('http://localhost:8081/trainers/' + trainerId)
        .then((res) => res.json())
        .then((data) => {
            trainer = data;
        });
    await fetch('http://localhost:8081/review/trainer/' + trainerId)
        .then((res) => res.json())
        .then((data) => {
            reviews = data;
        });
    try {
        await fetch('http://localhost:8081/sessionoptions/findByTrainer/' + trainerId).then((res) => res.json())
            .then((data) => {
                sessionOptions = data;
            });
    } catch (e) {
    }
        await fetch('http://localhost:8081/review/trainer/' + trainerId + '/average')
            .then((res) => res)
            .then((data) => {
                average = data;
            });
    await fetch('http://localhost:8081/gyms/businessHours/' + trainerId)
        .then((res) => res.json())
        .then((data) => {
            businessHours = data;
        });
    return [trainer, sessionOptions, reviews, average, businessHours];
}

export default function Trainer() {
    const data = useLoaderData();
    const trainer = data[0];
    const [sessionOptions] = [data[1]];
    const [reviews] = [data[2]];
    const averageRating = data[3];
    const businessHours = data[4];
    return (
        <div id="gym">
            <div>
                <img
                    key={trainer.imgPath}
                    src={trainer.imgPath || null}
                />
            </div>
            <div>
                <h1>
                    {trainer.firstName ? (
                        <>
                            {trainer.firstName} {trainer.lastName}
                        </>
                    ) : (
                        <i>No Name</i>
                    )}{" "}
                </h1>
                <h2><Link to = {'/gyms/' + trainer.gym.id}>{trainer.gym.name}</Link></h2>
                <p>{trainer.status}</p>
                <p>{trainer.introduction}</p>

            </div>
            <div>
                <h1>Availability</h1>
                    {businessHours.length ? (
                        <ul>
                            {businessHours.map((businessHour) => (
                                <li key={businessHour.id}>
                                    <h2>{businessHour.day}</h2>
                                    <p>From: {moment(businessHour.openTime, "HH:mm").format("hh:mm A")}</p>
                                    <p>To: {moment(businessHour.closeTime, "HH:mm").format("hh:mm A")}</p>
                                </li>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No business hours specified</i>
                        </p>
                    )}
            </div>
            <div>
                <h1>Session Options</h1>
                    {sessionOptions.length ? (
                        <ul>
                            {sessionOptions.map((sesOption) => (
                                <Button>
                                    <Link to="/book-session" state={{data: {trainer}}}>
                                        <dl>
                                            <p>Name : {sesOption.name}</p>
                                            <p>Price : {sesOption.price} Huf</p>
                                            <p>Description : {sesOption.description}</p>
                                            <p>Length : {sesOption.lengthMinutes} minutes</p>
                                        </dl>
                                    </Link>
                                </Button>
                            ))}
                        </ul>
                    ) : (
                        <p>
                            <i>No session options</i>
                        </p>
                    )}
            </div>
            <div>
                <h1>Reviews</h1>
                {reviews.length ? (
                    <ul>
                        <div>Average rating : {averageRating}</div>
                        {reviews.map((review) => (
                            <li key={review.id}>
                                <p>{review.customer.firstName}</p>
                                <p>{review.rating}</p>
                                <p>{review.comment}</p>
                            </li>
                        ))}
                    </ul>
                ) : (
                    <p>
                        <i>No reviews</i>
                    </p>
                )}
            </div>
            <div>
                <p><Button><Link to='/review/trainer' state={{data: {trainer}}}>Write a review</Link></Button></p>
            </div>
        </div>
    );
}
