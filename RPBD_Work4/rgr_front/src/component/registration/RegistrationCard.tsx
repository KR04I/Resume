import {Registration} from "../../api/base";
import {RegistrationForm} from "./RegistrationForm";
import {useState} from "react";
import {Property} from "../Property";

interface Props {
    registration: Registration
    onEdit: (id?: number, visitor?: Registration) => void
    onDelete: (id?: number) => void
}

export const RegistrationCard: React.FC<Props> = ({ registration, onEdit, onDelete }) => {
    const [isEdit, setIsEdit] = useState(false)


    return (
        <div className="card registration-card">
            {isEdit ?
                <RegistrationForm registration={registration} onSubmit={(newClient) => { onEdit(registration.id, newClient); setIsEdit(false) }} />
                :
                <div className="visitor-card__main">

                    <Property title="Парковочный номер:" value={registration.parkingNumber} />
                    <Property title="Номер машины:" value={registration.carRegistrationNumber} />
                    <Property title="Дата въезда:" value={registration.dateOfEntry} />
                    <Property title="Дата выезда:" value={registration.dateOfDeparture} />

                    <Property title="Информация о посетителе:" value={registration.visitorByVisitor?.name} />
                    <Property title="Информация о комнате:" value={registration.roomByRoom?.roomNumber} />
                </div>
            }
            <div className="visitor-card__controls">
                <button className="button"
                        onClick={() => setIsEdit(!isEdit)}>{isEdit ? 'Закрыть' : 'Редактировать'}</button>
                <button className="button button_red" onClick={() => onDelete(registration.id)}>Удалить</button>
            </div>
        </div>
    )
}