import {registrationApi} from "../../api/RegistrationApi";
import {Registration} from "../../api/base";
import {useEntities} from "../../util/useEntities";
import {RegistrationForm} from "./RegistrationForm";
import {RegistrationCard} from "./RegistrationCard";
import {useState} from "react";


export const RegistrationPage: React.FC = () => {
    const [registrations, _, refresh] = useEntities(registrationApi)
    const [addFormShow, setAddFormShow] = useState(false)

    const onAddSubmit = (client: Registration) => {
        registrationApi.create(client).finally(refresh)
        setAddFormShow(false)
    }

    const onEdit = (id?: number, client?: Registration) => {
        if (!id || !client) return
        registrationApi.edit(id, client).finally(refresh)
    }

    const onDelete = (id?: number) => {
        if (!id) return
        registrationApi.delete(id).finally(refresh)
    }

    return (
        <div className="registration-page">
            <div className="card">
                <button className="button" onClick={() => setAddFormShow(!addFormShow)}>{`${addFormShow ? 'Закрыть' : 'Добавить'}`}</button>
                {addFormShow &&
                    <RegistrationForm onSubmit={onAddSubmit} />
                }
            </div>
            <div>
                {registrations?.map(c => <RegistrationCard key={c.id} registration={c} onEdit={onEdit} onDelete={onDelete} />)}
            </div>
        </div>
    )
}