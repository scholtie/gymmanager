import {useLoaderData} from "react-router-dom";


export async function loader({params}) {
    let trainer;
    let average;
    let [reviews] = []
    let [sessionOptions] = [];
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
    try {
        await fetch('http://localhost:8081/review/gym/' + gymId + '/average')
            .then((res) => res.json())
            .then((data) => {
                average = data;
            });
    } catch (e) {
    }
    return [trainer, sessionOptions, reviews, average];
}

export default function Trainer() {
    const data = useLoaderData();
    const trainer = data[0];
    const [sessionOptions] = [data[1]];
    const [reviews] = [data[2]];
    const averageRating = data[3];
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
                <h2>{trainer.gym.name}</h2>
                <p>{trainer.status}</p>
                <p>{trainer.introduction}</p>

            </div>
            <div>
                <h1>Session Options</h1>
                <p>
                    {sessionOptions?.map((sesOption) => (
                        <dl>
                            <dt>Name : {sesOption.name}</dt>
                            <dd>Price : {sesOption.price} Huf</dd>
                            <dd>Max people : {sesOption.maxPeople}</dd>
                            <dd>Length : {sesOption.lengthMinutes} minutes</dd>
                        </dl>
                    ))}

                </p>
            </div>
            <div>
                Average rating : {averageRating}
                {reviews?.map((review) => (
                    <dl>
                        <dt>{review.rating}</dt>
                        <dd>{review.comment}</dd>
                        <dd>{review.customer.firstName}</dd>
                    </dl>
                ))}
            </div>
        </div>
    );
}
