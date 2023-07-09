import {Passport, Services} from "../../api/base";
import {useState} from "react";
import {ServiceForm} from "./ServiceForm";
import {Property} from "../Property";

interface Props {
    service: Services
    onEdit: (id?: number, service?: Services) => void
    onDelete: (id?: number) => void
}

export const ServicesCard: React.FC<Props> = ({ service, onEdit, onDelete }) => {

    const [isEdit, setIsEdit] = useState(false)

    return (
        <div className="card film-card">
            {isEdit ?
                <ServiceForm service={service} onSubmit={(newClient) => { onEdit(service.id, newClient); setIsEdit(false) }} />
                :
                <div className="service-card__main">

                    <Property title="Название услуги:" value={service.name} />
                    <Property title="Стоимость:" value={service.price} />
                </div>
            }
            <div className="service-card__controls">
                <button className="button" onClick={() => setIsEdit(!isEdit)}>{isEdit ? 'Закрыть' : 'Редактировать'}</button>
                <button className="button button_red" onClick={() => onDelete(service.id)}>Удалить</button>
            </div>
        </div>
    )
}