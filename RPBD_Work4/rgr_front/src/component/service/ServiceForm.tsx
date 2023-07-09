import {Passport, Services} from "../../api/base";
import {serviceApi} from "../../api/ServiceApi";
import {useState} from "react";
import {Property} from "../Property";

interface Props {
    service?: Services
    onSubmit: (service: Services) => void
}

export const ServiceForm: React.FC<Props> = ({ service, onSubmit }) => {

    const [services] = useState(serviceApi)

    const [name, setName] = useState(service?.name ?? '')
    const [price, setPrice] = useState(service?.price ?? '')

    const onClick = () => {
        if (name === '') return
        onSubmit({
            name,
            price,
        })
        setName('')
        setPrice('')
    }

    return (
        <div className="service-form">
            <Property title="Название услуги:"
                      value={<input type="text" value={name} onChange={e => setName(e.target.value)} />} />
            <Property title="Цена:" value={<input type="text" value={price}
                                                   onChange={e => setPrice(e.target.value)} />} />

            <button className="button button_green" onClick={onClick}>Ок</button>
        </div>
    )
}